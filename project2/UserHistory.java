
import java.util.*;


//UserHistory is implemeted using a DLB
	//each node has a reference to a child, a reference to a sibling, and a frequency
	//the child references will form lists that that link to extend keys with common prefixes
	//sibling references form lists that connect different potential options to extend prefixes
	//the frequency int keeps track of how many times a key was added to the UserHistory
		//if a node has a frequency of 0, then nothing is stored
		//if a node has a frequency of >0, it has been stored that many times
public class UserHistory implements Dict{

 	private int size; //contains the number of keys/words stored in the UserHistory
	private HistNode root;
	private HistNode charSearchPos; //starting position for searchByChar()
	private StringBuilder charSearchWord; //keep track of what searchByChar has searched for and found (a log of all searchByChar() returns since the last reset, turned into a string)
										  //charSearchWord is the prefix/word that has been constructed through the searchByChar so far
	public UserHistory(){
		size = 0;
		root = null;
		charSearchPos = null;
		charSearchWord = new StringBuilder("");
	}

//-----------------------------------------------------------------------
//add

	public void add(String key){
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(root == null){ //this conditional only runs for the first key added to the UserHistory
			HistNode rootNode = new HistNode(key.charAt(0));
			root = rootNode;
			charSearchPos = new HistNode();
			charSearchPos.setDown(root); //make root the position to start during searchByChar
			charSearchWord = new StringBuilder("");				
			insert(key, root, 1);
		}
		else{
			add(key, root, 0);
		}

	}//end add

	private boolean add(String key, HistNode curr, int keyPos){
		boolean inserted = false;

		while(curr != null && keyPos < key.length() && !inserted){  // do i need second test?? // would probably need if didnt have else -> add(key, curr.getDown(), ++keyPos);
			if(curr.getLet() == key.charAt(keyPos)){	
				if(keyPos == (key.length()-1)){ //already at full length of key, at last char
					if(curr.getFrequency() == 0){ //only increase size if this was the first time such a word was added
						size++;
					}
					curr.increaseFrequency();//increase the frequency of the word added
					inserted = true; //return true
				}
				else if(curr.getDown() == null){ //not at the last char in key, but no child to recursively continue search with
					insert(key, curr, ++keyPos); //insert a new sequence of char to store key
					inserted = true;
				}
				else{ //search for prefix
					  //continue the search down the list of children, because we are not at the last char in key
					inserted = add(key, curr.getDown(), ++keyPos);
					return inserted;
				}
			}

			//NOTE: could change the inner if conditional above to return true instead of inserted = true, then wouldn't need second condition below (!inserted)
			if(curr.getRight() == null && !inserted){ //end of siblings reached, and char not found that can extend prefix
				HistNode newNode = new HistNode(key.charAt(keyPos));
				curr.setRight(newNode); //add a new sibling node that holds the next char in the key (the char that will extend the prefix)
				insert(key, newNode, ++keyPos); //insert the rest of the char to finish adding the key
				inserted = true; //return true so that while loop terminates
								//RESOLVED PROBLEM: if dont , then curr will always have a sibling (the newly added node), causing infinite loop
			}
			curr = curr.getRight(); //move down sibling list
		}
		return inserted;
	}//end add


	private void insert(String key, HistNode startOfInsert, int keyPos){
		HistNode newNode;

		for( ; keyPos < key.length(); keyPos++){ //runs for the length of the key that is not part of the userHistory yet  (ex. "hello" is sotred in userHistory, at position "he", will add "llo" to the dlb)
			newNode = new HistNode(key.charAt(keyPos));
			startOfInsert.setDown(newNode);
			startOfInsert = newNode; //the next node added (during the next iteration) will be the child of the newNode (that we just added)
		}
		startOfInsert.increaseFrequency(); //last node added is end of a valid word, increase frequency
		size++;
	}//end insert

//-----------------------------------------------------------------------
//contains

	public boolean contains(String	key){
		boolean included = false;
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(root == null){
			return false; //false if userHistory is emtpy
		}
		else{
			included = checkContains(key, root, 0);
		}
		
		return	included;
	}

	private boolean checkContains(String key, HistNode curr, int keyPos){
		boolean found = false;
		while(curr != null && !found){
			if(curr.getLet() == key.charAt(keyPos)){ //found char in UserHistory to match position in key
				if(keyPos == (key.length()-1)){ //found a match for end of key, so check if frequency shows the key is being stored
					if(curr.getFrequency() != 0){ //if at end of key and curr's frequency shows a word is stored
						found = true;
					}
					else{//if curr is not the end of the valid word being searched for, the prefix is not stored as a key
						return false; //at end of key, and the key is not stored in the UserHistory
					}
				}
				else{ //the prefix can be extended, and we are not at the end of the key
					found = checkContains(key, curr.getDown(), keyPos+1); //recurse to the child
																//NOTE: PROBLEM RESOLVED: had as keyPos++ (caused problem), changed to keyPos+1 (fixed problem)
																	// keyPos++ was doing an assignment operation of keyPos = keyPos+1
																	// but this is incorrect because I want keyPos to remain the same for the entirity of this recursive call (even after backtracking)
																	//that is to say that on the first level of siblings, keyPos should always be 1, on the second keyPos should always be 2, etc.
																			//ex. searching for "hlr"
																			// when returnng after the first recursive call, keyPos was 2 rather than 1, so when it found 'l' later 
																			// in the list, it thought it could extend the prefix, and same with 'r' after that
				}
			}

			curr = curr.getRight(); //continue searching down the list of siblings
		}

		return found;
	}
//-----------------------------------------------------------------------
//containsPrefix

	public boolean containsPrefix(String key){
		boolean included = false;
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(root == null){
			return false;
		}
		else{
			included = checkContainsPrefix(key, root, 0);
		}
		
		return	included;
	}

	private boolean checkContainsPrefix(String key, HistNode curr, int keyPos){
		boolean found = false;
		while(curr != null && !found){
			if(curr.getLet() == key.charAt(keyPos)){ //found char in UserHistory to match position in key
				if(keyPos == (key.length()-1)){ //found a match for the last char in key (at last position in key and found a match)
											//if curr's child is not a terminating character, the prefix is not stored as a key
					found = true; //NOTE: could also just return true
				}
				else{ //not at end of key, so keep searching/extending prefix until keyPos is the same as key.length()-1 (the index of the last char in key)
					found = checkContainsPrefix(key, curr.getDown(), keyPos+1);
																		//NOTE: PROBLEM RESOLVED: see contains()
				}
			}

			curr = curr.getRight(); //search through siblings for a potential match
		}

		return found;
	}

//-----------------------------------------------------------------------
//searchByChar

	public int searchByChar(char next){
			//if root is null, then no DLB entries exist, trying to search would cause null pointer
			//charSearchPos is null when a search has been created that is not part of a valid prefix or word
					//before I added this, byCharSearch would just ignore incompatible searches and act as if we were picking up from the last search that found something
					//now it will recognize that the charSearchWord that has been built cannot be extended to a valid suggestion
			//if charSearchPos.getDown == null, then cannot extend the prefix any further

		charSearchWord.append(next); //maintains a copy of the path we took to get to the current node (this is the word/prefix searchByChar() calls have built)

		if(root != null && charSearchPos != null && charSearchPos.getDown() != null ){ //root != null must be first, otherwise will get a null
															  //both root and charSearchPos will == null if the UserHistory hasn't been added to
			HistNode searchNode = charSearchPos.getDown(); //searchNode is initialized with charSearchPos, so we can continue the search but still have a reference to the original starting point if a match is not found
			
			while(searchNode != null){//search through list of siblings to find match that could extend prefix
				if(searchNode.getLet() == next){ //match is found, the char stored at searchNode is the same as the next char we are searching for
												 
					if(searchNode.getFrequency() == 0){
						charSearchPos = searchNode;//next search will begin at the child of the node we just found, so that we can extend prefix further
						return 0; //the char being searched for is part of a valid prefix, but does not complete a valid word
								 //char exists in the siblings, but its child isn't the terminating character
									  
					}
					else{//searchNode has a terminating character as a child, so the search has found a valid word
						if(searchNode.getDown() == null){
							charSearchPos = searchNode;
							return 1; //completes a valid word but not a valid prefix
									//char exists as the end of a valid word, but it's child (a terminating char) has no siblings so it cannot be a prefix
						}
						else{ // searchNode.getDown().getRight != null,  searchNode has a terminating char as a child and can be extended to become a valid prefix to another word
							charSearchPos = searchNode;
							return 2; //completes a valid word and a valid prefix
						}
					}
				}
				searchNode = searchNode.getRight(); //move to next sibling
			}

		}	

		charSearchPos = null;	
			//NOTE: PROBLEM SOLVED
					// charSearchPos = null was inside the if conditional before, so it wasn't seeting charSearchPos to null in all the cases it needed to
					// moved it outside all conditionals and loops so that it would take effect everytime -1 is returned
					// thing to take away, if something needs to happen before a return, best to just put it right before the return statement

		return	 -1; //char being searched for is not part of a valid prefix nor does it complete a valid word
	}//end searchByChar
		

//-----------------------------------------------------------------------
//resetByChar

	public void resetByChar(){
		if(root != null){//only allow a reset ot occur if the DLB not empty, dont want to initialize to something other than null if DLB is empty
			charSearchPos = new HistNode(); //make a new HistNode that points to root, this node will never be accessed, but it is used to start a byCharSearch()
			charSearchPos.setDown(root);
			charSearchWord = new StringBuilder(""); //reset charSerachWord to be empty
		}
	}

//-----------------------------------------------------------------------
//suggest

	public ArrayList<String> suggest(){
		ArrayList<String> suggestions = new ArrayList<String>(); // 5 most frequently added keys starting from current byCharSearch position
		ArrayList<Integer> suggestionFrequencies = new ArrayList<Integer>(); //store the frequencies of each key
		if(root != null && charSearchPos != null){ //if charSearchPos were null, would need to reset to be able to search again
			StringBuilder word = new StringBuilder(charSearchWord.toString());//word starts out with the current prefix of the by char search
			
			if(charSearchPos.getFrequency() != 0){ //if the current charSearchPos stopped on a key, add it to suggestions (if the last search landed on a key)
				suggestions.add(word.substring(0, word.length()).toString()); //add the key to the end of suggestions
				suggestionFrequencies.add(charSearchPos.getFrequency()); // add the key's frequency to the corresponding index of suggestionFrequencies		
			}
			suggestFive(charSearchPos.getDown(), suggestions, suggestionFrequencies,word);//passing in charSearchPos.getDown() because charSearchPos is at the last node we found
		}

		return suggestions;
	}


	public ArrayList<String> suggestFive(HistNode curr, ArrayList<String> suggestions, ArrayList<Integer> suggestionFrequencies, StringBuilder word){
		while(curr != null){ //wont run if the curr is a null reference
			word.append(curr.getLet());
			if(curr.getFrequency() != 0){ //key is stored at this node, check if should add key to the arraylist
				boolean inserted = false; //keeps track of whether the key is added to the arrayList
										  //inserted set to false everytime on a node whose frequency is > 0
				for(int i = 0; i < suggestionFrequencies.size(); i++){ //iterates across all items in the arraylist, uses suggestionFrequencies becasue want to compare by frequency

					if(curr.getFrequency() > suggestionFrequencies.get(i) && !inserted){// if the current key's frequency is in the top 5 most frequent out of the words found so far, add it

						suggestions.add(i, word.substring(0, word.length()).toString()); //add the key to the correct position of suggestions
						suggestionFrequencies.add(i, curr.getFrequency()); // add the keys frequency to the corresponding index of suggestionFrequencies
						inserted = true; //	don't need to see if there is room to add at the end of the list (the conditional after the for loop) since we just added the key
					}

					if(suggestions.size() > 5){ //remove the final item from each arraylist if there are more than 5 items 
							suggestions.remove(suggestions.size()-1); //delete the item at the 6th (final) index
							suggestionFrequencies.remove(suggestionFrequencies.size()-1); //delete the item at the 6th (final) index
					}
				}
				
				if(!inserted && suggestions.size() < 5){// looked thorugh all the keys in the arraylist (using the for loop above) and did not add the key, so insert at end if there is room
					suggestions.add(word.substring(0, word.length()).toString()); //add the key to the end of suggestions
					suggestionFrequencies.add(curr.getFrequency()); // add the keys frequency to the corresponding index of suggestionFrequencies		
				}
			}

			if(curr.getDown() != null){ //if curr has a child, move down the list of children
											//This is in an if (and not an else) because since we are using 
											//frequencies rather terminating chars to determine if a key is stored,
											//then we must find all keys stored in a list of children
				suggestFive(curr.getDown(), suggestions, suggestionFrequencies, word);
			}
			
			word.deleteCharAt(word.length()-1);// remove the character we just added before looking at curr's sibling
			curr = curr.getRight(); //move right through the list of siblings
		}

		return suggestions;
	}

//-----------------------------------------------------------------------
//traverse

	public ArrayList<String> traverse(){ 
		StringBuilder word = new StringBuilder(""); //holds the prefix/word that will be added to the travPath arraylist
		ArrayList<String> travPath = new ArrayList<String>();

		traverse(root, travPath, word);

		return travPath;
	}//end traverse

	public void traverse(HistNode curr, ArrayList<String> travPath, StringBuilder word){
		while(curr != null){

			word.append(curr.getLet()); //add curr's char so that word matches the prefix/word the traversal is currently at
			if(curr.getFrequency() != 0){
					travPath.add(word.substring(0, word.length()).toString()); //add the word to the travPath
																			   //need to do this before recursing because a list of children can store multiple keys
			}
			if(curr.getDown() != null){
				traverse(curr.getDown(), travPath, word); //recurse down the list of children
				
			}
			
			word.deleteCharAt(word.length()-1); //remove the char that was just added before moving to curr's sibling
			curr = curr.getRight(); //move across the list of siblings
		}
	}//end traverse


//-----------------------------------------------------------------------
//count

	//returns the number of unique keys stored in the DLB
	public int count(){
		
		return size;
	}



//===============================================================================
//below are methods that aren't specified in the interface but I created because I found them helpful

	//similar to traversal except it prints out the keys and their frequencies using a stringbuilder
	//also serialize prints out each key on its own line before printing out the path it took through the DLB
	
	public String serialize(){
		StringBuilder travPath = new StringBuilder("");
		StringBuilder word = new StringBuilder("");

		serialize(root, travPath, word);
		System.out.println("\t\ttravPath:");
		return travPath.toString();
	}

	private void serialize(HistNode curr, StringBuilder travPath, StringBuilder word){
		while(curr != null){

			word.append(curr.getLet());
			if(curr.getDown() != null){
				if(curr.getFrequency() != 0){
					System.out.println("\t\t\t" + word.toString() + " " + curr.getFrequency());
					travPath.append(word.substring(0, word.length()) + " " + curr.getFrequency() + "| ");
				}
				serialize(curr.getDown(), travPath, word);
				
			}
			else{
				System.out.println("\t\t\t" + word.toString() + " " + curr.getFrequency());
				travPath.append(word.substring(0, word.length()) + " " + curr.getFrequency() + "| ");
			}
			
			word.deleteCharAt(word.length()-1);
			curr = curr.getRight();
		}
	}

	//returns the current state of the byCharSearch
	public String getCharSearchWord(){
		return charSearchWord.toString();
	}

	//returns a reference to a UserHistory's root
	public HistNode getRoot(){
		return this.root;
	}

}//end UserHistory
