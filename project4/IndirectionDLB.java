package cs1501_p4;

public class IndirectionDLB{
	
	private IndirectionNode root;
	
	public IndirectionDLB(){
		root = null;
	}


//-----------------------------------------------------------------------
//add
	//not increasing size (or even keeping track) because using add to implement CarsPQ remove(), by adding a -1 at the end of the vin key

	public void add(String key, int heapIndex){
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(root == null){ //this conditional only runs for the first key added to the dlb
			IndirectionNode rootNode = new IndirectionNode(key.charAt(0)); 
			root = rootNode;
			insert(key, heapIndex, root, 1);
		}
		else{
			add(key, heapIndex, root, 0);
		}

	}//end add

	private boolean add(String key, int heapIndex, IndirectionNode curr, int keyPos){
		boolean inserted = false;
		while(curr != null && keyPos < key.length() && !inserted){  // do i need second test?? // would probably need if didnt have else -> add(key, curr.getDown(), ++keyPos);
			if(curr.getLet() == key.charAt(keyPos)){	
				if(keyPos == (key.length()-1)){ //already at full length of key, at last char
					curr.setIndex(heapIndex); //mark the index of the key
					inserted = true; //return true
				}
				else if(curr.getDown() == null){ //not at the last char in key, but no child to recursively continue search with
					insert(key, heapIndex, curr, ++keyPos); //insert a new sequence of char to store key
					inserted = true;
				}
				else{ //search for prefix
					  //continue the search down the list of children, because we are not at the last char in key
					inserted = add(key, heapIndex, curr.getDown(), keyPos+1); 
					return inserted;
				}
			}

			//NOTE: could change the inner if conditional above to return true instead of inserted = true, then wouldn't need second condition below (!inserted)
			if(curr.getRight() == null && !inserted){ //end of siblings reached, and char not found that can extend prefix
				IndirectionNode newNode = new IndirectionNode(key.charAt(keyPos));
				curr.setRight(newNode); //add a new sibling node that holds the next char in the key (the char that will extend the prefix)
				insert(key, heapIndex, newNode, keyPos+1); //insert the rest of the char to finish adding the key
				inserted = true; //return true so that while loop terminates
								//RESOLVED PROBLEM: if dont , then curr will always have a sibling (the newly added node), causing infinite loop
			}
			curr = curr.getRight(); //move down sibling list
		}
		return inserted;
	}//end add


	private void insert(String key, int heapIndex, IndirectionNode startOfInsert, int keyPos){
		IndirectionNode newNode;

		for( ; keyPos < key.length(); keyPos++){ //runs for the length of the key that is not part of the dlb yet  (ex. "hello" is sotred in dlb, at position "he", will add "llo" to the dlb)
			newNode = new IndirectionNode(key.charAt(keyPos));
			startOfInsert.setDown(newNode);
			startOfInsert = newNode; //the next node added (during the next iteration) will be the child of the newNode (that we just added)
		}
		startOfInsert.setIndex(heapIndex); //last node added is end of a valid word
	}//end insert

//-----------------------------------------------------------------------
//contains


	//return -1 if not found, or the index of location in heap if is found
	public int contains(String key){
		// System.out.println("key: " + key);
		int included = -1; 
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(root == null){ //empty
			return -1; //key not contained if dlb is emtpy
		}
		else{
			included = checkContains(key, root, 0);
		}
		
		return	included;
	}

	private int checkContains(String key, IndirectionNode curr, int keyPos){
		int foundAt = -1;
		while(curr != null && foundAt == -1){
			if(curr.getLet() == key.charAt(keyPos)){ //found char in dlb to match position in key
				if(keyPos == (key.length()-1)){ //found a match for end of key, so check if frequency shows the key is being stored
					if(curr.getIndex() != -1){ //if at end of key and curr's index marker shows a word is stored
						return curr.getIndex(); //return the index where the object that matches the desired vin is stored
					}
					else{//if curr is not the end of the valid word being searched for, the prefix is not stored as a key
						return -1; //at end of key, and the key is not stored
					}
				}
				else{ //the prefix can be extended, and we are not at the end of the key
					foundAt = checkContains(key, curr.getDown(), keyPos+1); //recurse to the child
				}
			}

			curr = curr.getRight(); //continue searching down the list of siblings
		}

		return foundAt;
	}// end checkContains


//------------------------------------------------------------------------------
//serialize
//	REMOVE THE SERIALIZE FUNCTION BEFORE SUBMITTING

	public String serialize(){
		StringBuilder travPath = new StringBuilder("");
		StringBuilder word = new StringBuilder("");

		serialize(root, travPath, word);
		System.out.println("\t\ttravPath:");
		return travPath.toString();
	}

	private void serialize(IndirectionNode curr, StringBuilder travPath, StringBuilder word){
		while(curr != null){

			word.append(curr.getLet());
			if(curr.getDown() != null){
				if(curr.getIndex() != -1){
					System.out.println("\t\t\tvin: " + word.toString() + ", index:" + curr.getIndex());
					travPath.append(word.substring(0, word.length()) + " " + curr.getIndex() + "| ");
				}
				serialize(curr.getDown(), travPath, word);
				
			}
			else{
				System.out.println("\t\t\tvin: " + word.toString() + ", index:" + curr.getIndex());
				travPath.append(word.substring(0, word.length()) + " " + curr.getIndex() + "| ");
			}
			
			word.deleteCharAt(word.length()-1);
			curr = curr.getRight();
		}
	}

}