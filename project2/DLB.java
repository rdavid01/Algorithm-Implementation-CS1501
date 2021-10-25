/*
This class will be used to store the dictionary of English words
*/

import java.util.*;
import java.io.*;

public class DLB implements Dict{

	private int size; //contains the number of keys/words stored in the DLB
	private DLBNode root;
	private DLBNode charSearchPos; //starting position for searchByChar()
	private MyStringBuilder2 charSearchWord; //keep track of what searchByChar has searched for and found (a log of all searchByChar() returns since the last reset, turned into a string)
										  //charSearchWord is the prefix/word that has been constructed through the searchByChar so far
	public DLB(){
		size = 0;
		root = null;
		charSearchPos = null;
		charSearchWord = new MyStringBuilder2("");
	}

//-----------------------------------------------------------------------
//add

	public void add(String key){
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(!(key.contains("^"))){ //dont allow a key to be inserted if it contains the terminating character
			if(root == null){ //this conditional only runs for the first key added to the DLB
				DLBNode rootNode = new DLBNode(key.charAt(0));
				root = rootNode;
				charSearchPos = new DLBNode('!'); //make root the position to start duringSearch by Char
				charSearchPos.setDown(root);
				charSearchWord = new MyStringBuilder2("");				
				insert(key, root, 1);
			}
			else{
				add(key, root, 0);
			}
		}

	}// end add

	private boolean add(String key, DLBNode curr, int keyPos){
		boolean inserted = false;

		while(curr != null && keyPos < key.length() && !inserted){  // do i need second test?? // would probably need if didnt have else -> add(key, curr.getDown(), ++keyPos);
			if(curr.getLet() == key.charAt(keyPos)){			
				if(keyPos == (key.length()-1) && curr.getDown().getLet() != '^'){ //already at full length of key, at last char  
																				  //the word we are adding is the prefix of a word that is already stored
																					//so a term node needs to be curr's child, and curr's old child will be this term node's sibling
					DLBNode termNode = new DLBNode('^'); 
					termNode.setRight(curr.getDown()); //make a new termNode, set the current node's child to be this termNode's sibling 
					curr.setDown(termNode); // make the current node's child the term node so that the word we insert becomes a valid word
											//a new word has been added, and its suffix is moved to be a sibling of the termNode
					size++;
					inserted = true; //return true
				}
				else{ //search for prefix
					  //we can continue the search by going down the list of children, but we are not at the end of the key
					inserted = add(key, curr.getDown(), ++keyPos); //search down the list of children
					return inserted;
				}
			}
			else if(curr.getRight() == null){ //end of siblings reached, and char not found
				DLBNode newNode = new DLBNode(key.charAt(keyPos));
				curr.setRight(newNode);  //extend the prefix by adding a new node to the end of the sibling list
				insert(key, newNode, ++keyPos);
				inserted = true; //return true so that while loop terminates
								//if dont , then curr will always have a sibling (the newly added node), causing infinite loop
			}		

			curr = curr.getRight(); //move to the next sibling
		}
		return inserted;
	}//end add

	private void insert(String key, DLBNode startOfInsert, int keyPos){
		DLBNode termNode = new DLBNode('^');
		DLBNode newNode;

		for( ; keyPos < key.length(); keyPos++){ //runs for the length of the of key that is not part of the DLB yet
			newNode = new DLBNode(key.charAt(keyPos));
			startOfInsert.setDown(newNode);
			startOfInsert = newNode;  //the next node added (during the next iteration) will be the child of the the newNode (that we just added during this iteration)
		}
		startOfInsert.setDown(termNode); //add a terminating node to the end of the key
		size++;
	}

//-----------------------------------------------------------------------

	public boolean contains(String	key){
		boolean included = false;
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(!(key.contains("^"))){ //dont search for a key that contains the terminating character, it should not exist and could produce incorrect results (especially during traverse, suggest)
			if(root == null){
				return false; //return false if empty
			}
			else{
				included = checkContains(key, root, 0);
			}
		}
		return	included;
	}

	private boolean checkContains(String key, DLBNode curr, int keyPos){
		boolean found = false;



		while(curr != null && !found){
			System.out.println("key: " + key + ", keyPos: " + keyPos + ", key.charAt(keyPos): " + key.charAt(keyPos) + ", curr.getLet(): " + curr.getLet());
			if(curr.getLet() == key.charAt(keyPos)){ //found char in DLB to match position in key
				if(keyPos == (key.length()-1)){ //found a match for the end of the key, so need to check if there is termNode to mark that it is being stored
					
					if(curr.getDown().getLet() == '^'){ //if at end of key and curr's child is a terminating character, then the key is being stored
						found = true; //while loop will not run and found will return true
					}
					else{//if curr's child is not a terminating character, the prefix is not stored as a key
						return false; //at end of key, and the key is not stored in the DLB
					}
				}
				else{ //the prefix can be extended, and we are not at the end of the key
					System.out.println("@@@@@@@@@@@@@ GOING INTO RECURSIVE CALL @@@@@@@@@@@@@@@@@@@@@@@@@");
					found = checkContains(key, curr.getDown(), keyPos + 1); //recurse to the child
																//NOTE: PROBLEM RESOLVED: had as keyPos++ (caused problem), changed to keyPos+1 (fixed problem)
																						// keyPos++ was doing an assignment operation of keyPos = keyPos+1
																						// but this is incorrect because I want keyPos to remain the same for the entirity of this recursive call (even after backtracking)
																						//that is to say that on the first level of siblings, keyPos should always be 1, on the second keyPos should always be 2, etc.
																								//ex. searching for "hlr" (but "hlr" not stored)
																								// when returnng after the first recursive call, keyPos was 2 rather than 1, so when it found 'l' later 
																								// in the list, it thought it could extend the prefix, and same with 'r' after that
					System.out.println("!!!!!!!!!!!!! RETURNING FROM THE RECURSIVE CALL !!!!!!!!!!!!!!!!!");
				}
			}

			curr = curr.getRight(); //continue down the list of siblings to search for a node to extend the prefix
		}

		return found;
	}

//-----------------------------------------------------------------------
//containsPrefix()

	public boolean containsPrefix(String key){
		boolean included = false;
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(!(key.contains("^"))){ //dont search for a key that contains the terminating character
			if(root == null){
				return false;
			}
			else{
				included = checkContainsPrefix(key, root, 0);
			}
		}
		return	included;
	}

	private boolean checkContainsPrefix(String key, DLBNode curr, int keyPos){
		boolean found = false;
		while(curr != null && !found){
			if(curr.getLet() == key.charAt(keyPos)){ //found char in DLB to match position in key
				if(keyPos == (key.length()-1)){ //found a match for the character at the end of the key we're searching for
					found = true; //while loop won't run
								  //NOTE: could also just return true
				}
				else{ //not at end of key, so need to keep searching until keyPos is at the position of the final char 
					found = checkContainsPrefix(key, curr.getDown(), keyPos+1);
				}
			}

			curr = curr.getRight(); //move down the list of siblings trying to find a match to extend the prefix we're searching for
		}

		return found;
	}

//-----------------------------------------------------------------------
//searchByChar()
	public int searchByChar(char next){
			//if root is null, then no DLB entries exist, trying to search would cause null pointer
			//charSearchPos is null when a search has been created that is not part of a valid prefix or word
					//before I added this, byCharSearch would just ignore incompatible searches and act as if we were picking up from the last search that found something
					//now it will recognize that the charSearchWord that has been built cannot be extended to a valid suggestion
			//if charSearchPos.getDown == null, NOTE: THIS CONDITION MAY NOT BE NECCESSARY SINCE IT WOULD ONLY HAPPEN IF WE ADDED ^ TO A SEARCH

		if(root != null && charSearchPos != null && charSearchPos.getDown() != null ){
			DLBNode searchNode = charSearchPos.getDown(); //searchNode is initialized with charSearchPos, so we can continue the search but still have a reference to the original starting point if a match is not found
			charSearchWord.append(next); //maintains a copy of the path we took to get to the current node (this is the word/prefix searchByChar() calls have built)
										//add the char to charSearchWord
										//still add character if not found, user still searched for it
		
			if(next != '^'){ //only search if not trying to search for terminating char as next addition to the prefix
				
				while(searchNode != null){//search through list of siblings to find match that could extend prefix
					if(searchNode.getLet() == next){ //match is found, the char stored at searchNode is the same as the char we are searching for
						
						if(searchNode.getDown() != null && searchNode.getDown().getLet() != '^'){ //searchNode.getDown should only be null if at a ^, so is first test needed since we aren't allowing searches for ^????
							
							charSearchPos = searchNode;//next search will begin at the child of the node we just found, so we update charSearchPos to the current node
							return 0; //the char being searched for is part of a valid prefix, but does not complete a valid word (because it does not have a ^ child)
									 //char exists in the siblings, but its child isn't the terminating character
									  
						}
						else{//searchNode has a terminating character as a child, so the search has found a valid word
							if(searchNode.getDown().getRight() == null){
								charSearchPos = searchNode;
								return 1; //completes a valid word but not a valid prefix
										//char exists as the end of a valid word, but it's child (a terminating char) has no siblings so it cannot be a prefix
							}
							else{ // searchNode.getRight != null,  searchNode has a terminating char as a child and can be extended to become a valid prefix to another word
								charSearchPos = searchNode;
								return 2; //completes a valid word and a valid prefix
							}
						}
					}
					searchNode = searchNode.getRight(); //move to next sibling
				}
			}
		}
		charSearchPos = null;  //set charSearchPos to null if not part of a valid prefix or word (search is bad)
			
		return	 -1; //char being searched for is not part of a valid prefix nor does it complete a valid word
	}//endSearchByChar

//-----------------------------------------------------------------------

	public void resetByChar(){
		if(root != null){//only allow a reset ot occur if the DLB not empty, dont want to initialize to something other than null if DLB is empty
			charSearchPos = new DLBNode('!'); //make a new DLBNode that points to root, this node will never be accessed, but it is used to start a byCharSearch
			charSearchPos.setDown(root);
			charSearchWord = new MyStringBuilder2(""); //reset the charSearchWord to be empty
		}
	}

//-----------------------------------------------------------------------

	public ArrayList<String> suggest(){
		ArrayList<String> suggestions = new ArrayList<String>(); //will store the next 5 words from current byCharSearch position
		if(root != null && charSearchPos != null){ //if charSearchPos were null, would need to reset to be able to search again
			MyStringBuilder2 word = new MyStringBuilder2(charSearchWord.toString());//word starts out with the current prefix of the by char search
																					//PROBLEM RESOLVED:needed to declare the stringbuilder word inside an if because charSearchWord could be null
																						//this if is technically not needed anymore because initializing charSearchWord to be empty (not null) in the constructor
			if(charSearchPos.getLet() == '^'){ 
				suggestions.add(word.substring(0, word.length()-1).toString()); //if last search lead to a word that is stored as a key, then we want to include the current charSearchWord as a suggestion (because the search matches a key exactly)
			}
			suggest(charSearchPos.getDown(), suggestions, word);// passing in charSearchPos because charSearchPos is at the child of the last node we found
			
		}

		
		return suggestions;
	}

	public ArrayList<String> suggest(DLBNode curr, ArrayList<String> suggestions, MyStringBuilder2 word){
		while(curr != null && suggestions.size() < 5){ //fill arraylist up to a size of 5
													   //runs until arrayList's size is 5 or we have passed the end of a recursive call's sibling list

			word.append(curr.getLet()); //add curr's let so that word matches the path suggest has taken
			if(curr.getDown() != null){
				suggest(curr.getDown(), suggestions, word); //recurse down the list of children if possible
				
			}
			else{
				suggestions.add(word.substring(0, word.length()-1).toString()); //add the key stored at this position to the arrayList
																				//using length()-1 because don't want to include the ^ terminating character
			}
			
			word.deleteCharAt(word.length()-1); //remove the character just added before moving to it's sibling
			curr = curr.getRight();  //move rightward thorugh the list of siblings
		}

		return suggestions;
	}

	

//-----------------------------------------------------------------------

	public ArrayList<String> traverse(){
		MyStringBuilder2 word = new MyStringBuilder2(""); //holds the prefix/word that will be added to the travPath arrayList
		ArrayList<String> travPath = new ArrayList<String>();

		traverse(root, travPath, word);

		return travPath;
	}

	public void traverse(DLBNode curr, ArrayList<String> travPath, MyStringBuilder2 word){
		while(curr != null){ 

			word.append(curr.getLet()); //add curr's char so that word matches the prefix/word the traversal is currently at
			if(curr.getDown() != null){
				traverse(curr.getDown(), travPath, word); //recurse down list of children
				
			}
			else{
				travPath.add(word.substring(0, word.length()-1).toString()); //add the word to the travPath without the terminating char
			}
			
			word.deleteCharAt(word.length()-1); //remove the char that was just added before moving to its sibling
			curr = curr.getRight(); //move across list of siblings
		}
	}

//-----------------------------------------------------------------------

	//returns the number of unique keys stored in the DLB
	public int count(){
		
		return size;
	}




//=======================================================================
//=======================================================================

	//similar to traversal except it prints out the keys using a stringbuilder
	//also serialize prints out each key on its own line before printing out the path it took through the DLB
	public String serialize(){

		
		MyStringBuilder2 travPath = new MyStringBuilder2("");
		MyStringBuilder2 word = new MyStringBuilder2("");

		serialize(root, travPath, word);
		System.out.println("\t\ttravPath:");
		return travPath.toString();
	}

	private void serialize(DLBNode curr, MyStringBuilder2 travPath, MyStringBuilder2 word){
		while(curr != null){

			
			word.append(curr.getLet());
			
			if(curr.getDown() != null){
				serialize(curr.getDown(), travPath, word);
				
			}
			else{
				System.out.println("\t\t\t" + word.toString());
				travPath.append(word.substring(0, word.length()-1) + " ");
			}
			
			word.deleteCharAt(word.length()-1);
			
			curr = curr.getRight();

		}
	}

	//returns the current state of the byCharSearch
	public String getCharSearchWord(){
		return charSearchWord.toString();
	}

	//added after submission for testing purposes
	public void fillDLB(String dictionary){
		try{
			Scanner keyReader = new Scanner(new File(dictionary)); //created a scanner to read through the file
			while(keyReader.hasNext()){ //while there is another thing to read in the file
					
				String storedWord = keyReader.nextLine(); //read the next line in the file
				this.add(storedWord); //add this line from file to the dlb
			}
			keyReader.close(); //close the scanner
		}
		catch(FileNotFoundException e){
			System.out.println("dictionary file not found -> " + dictionary);
		}
	}

}//end DLB class