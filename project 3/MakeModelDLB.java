public class MakeModelDLB{
	
	private HeapNode root;
	
//---------------------------------------------------------------------------
//constructor
	
	public MakeModelDLB(){
		root = null;
	}

//-----------------------------------------------------------------------
//add
	//not increasing size (or even keeping track) because using add to implement CarsPQ remove(), by adding a -1 at the end of the vin key
	//the key is the make and model of this car concatenated together
	public void add(Car carToAdd){
		if(carToAdd == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		if(carToAdd.getMake() == null || carToAdd.getModel() == null){
			throw new IllegalArgumentException("car object is not complete, make or model is null");
		}

		String key = carToAdd.getMake() + carToAdd.getModel(); //create the key that will be used to store the path to the heaps that contain this particular car

		if(root == null){ //this conditional only runs for the first key added to the dlb
			HeapNode rootNode = new HeapNode(key.charAt(0)); 
			root = rootNode;
			insert(key, carToAdd, root, 1);
		}
		else{
			add(key, carToAdd, root, 0);
		}

	}//end add

	private boolean add(String key, Car carToAdd, HeapNode curr, int keyPos){
		boolean inserted = false;
		while(curr != null && keyPos < key.length() && !inserted){  
			if(curr.getLet() == key.charAt(keyPos)){	
				if(keyPos == (key.length()-1)){ //already at full length of key, at last char
					if(curr.getPriceHeap() == null){ //if one heap is non-existent, then they both are non-existent
						curr.setPriceHeap(new HeapPQ(0)); //last node added is end of a valid word, add heaps
						curr.setMileageHeap(new HeapPQ(1));
					}
					//add the car to the heaps
					curr.getPriceHeap().add(carToAdd);
					curr.getMileageHeap().add(carToAdd);

					inserted = true; //return true
				}
				else if(curr.getDown() == null){ //not at the last char in key, but no child to recursively continue search with
					insert(key, carToAdd, curr, keyPos+1); //insert a new sequence of char to store key
					inserted = true;
				}
				else{ //search for prefix
					  //continue the search down the list of children, because we are not at the last char in key
					inserted = add(key, carToAdd, curr.getDown(), keyPos+1);  
					return inserted;
				}
			}

			if(curr.getRight() == null && !inserted){ //end of siblings reached, and char not found that can extend prefix
				HeapNode newNode = new HeapNode(key.charAt(keyPos));
				curr.setRight(newNode); //add a new sibling node that holds the next char in the key (the char that will extend the prefix)
				insert(key, carToAdd, newNode, keyPos+1); //insert the rest of the char to finish adding the key
				inserted = true; 
			}
			curr = curr.getRight(); //move down sibling list
		}
		return inserted;
	}//end add


	private void insert(String key, Car carToAdd, HeapNode startOfInsert, int keyPos){
		HeapNode newNode;

		for( ; keyPos < key.length(); keyPos++){ //runs for the length of the key that is not part of the dlb yet  (ex. "hello" is sotred in dlb, at position "he", will add "llo" to the dlb)
			newNode = new HeapNode(key.charAt(keyPos));
			startOfInsert.setDown(newNode);
			startOfInsert = newNode; //the next node added (during the next iteration) will be the child of the newNode (that we just added)
		}

		//this key was added for the first time, so must provide heap's for the node at the end of the key
		startOfInsert.setPriceHeap(new HeapPQ(0)); //last node added is end of a valid word, add heaps
		startOfInsert.setMileageHeap(new HeapPQ(1));
		//add the car to the heaps
		startOfInsert.getPriceHeap().add(carToAdd);
		startOfInsert.getMileageHeap().add(carToAdd);
	}//end insert

//-----------------------------------------------------------------------
//contains (used to get min value of a particular make and model)

	public Car getMinMakeModel(String make, String model, int queueChoice){
		Car desiredCar = null; 
		if(make == null || model == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}

		String key = make+model; //create the key that will be used to store the path to the heaps that would contain this particular car (if it exists)

		if(root == null){
			return null; //nothing to be found if dlb is emtpy, key not contianed
		}
		else{
			desiredCar = findMin(key, queueChoice, root, 0);
		}
		
		return	desiredCar;
	}

	private Car findMin(String key, int queueChoice, HeapNode curr, int keyPos){
		Car desiredCar = null;
		while(curr != null && desiredCar == null){
			if(curr.getLet() == key.charAt(keyPos)){ //found char in dlb to match position in key
				if(keyPos == (key.length()-1)){ //found a match for end of key, so check if frequency shows the key is being stored
					if(curr.getPriceHeap() != null){ //if at end of key and curr has a heap, a this is a valid key
						if(queueChoice == 0){
							return curr.getPriceHeap().heap[0]; //queueChoice is 0, return the car at the first index of the priceHeap stored at the current node
						}
						return curr.getMileageHeap().heap[0]; //queueChoice is 1, return the car at the first index of the mileageHeap stored at current node
					}
					else{//if curr is not the end of the valid word being searched for, the prefix is not stored as a key
						return null; //at end of key, and the key is not stored
					}
				}
				else{ //the prefix can be extended, and we are not at the end of the key
					desiredCar = findMin(key, queueChoice, curr.getDown(), keyPos+1); //recurse to the child
				}
			}

			curr = curr.getRight(); //continue searching down the list of siblings
		}

		return desiredCar;
	}// end checkContains



//-----------------------------------------------------------------------
//getHeap

	//return false if not found, or the true if location of heap is found
	//queueChoice specifies whether we want to retrieve the price or mileage heap for a specific make and model
	//queueChoice == 0 means we want price,  queueChoice == 1 means we want mileage
	public HeapPQ getMakeModelHeap(Car carToUpdate, int queueChoice){
		HeapPQ desiredQueue = null; 
		if(carToUpdate == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}

		String key = carToUpdate.getMake()+carToUpdate.getModel();

		if(root == null){
			return null; //nothing to be found if dlb is emtpy, key not contianed
		}
		else{
			desiredQueue = getHeap(key, queueChoice, root, 0);
		}
		
		return	desiredQueue;
	}

	private HeapPQ getHeap(String key, int queueChoice, HeapNode curr, int keyPos){
		HeapPQ desiredQueue = null;
		while(curr != null && desiredQueue == null){
			if(curr.getLet() == key.charAt(keyPos)){ //found char in dlb to match position in key
				if(keyPos == (key.length()-1)){ //found a match for end of key, so check if frequency shows the key is being stored
					if(curr.getPriceHeap() != null){ //if at end of key and curr has a heap, a this is a valid key
						if(queueChoice == 0){
							return curr.getPriceHeap(); //queueChoice is 0, return priceHeap stored at the current node
						}
						return curr.getMileageHeap(); //queueChoice is 1, return mileageHeap stored at current node
					}
					else{//if curr is not the end of the valid word being searched for, the prefix is not stored as a key
						return null; //at end of key, and the key is not stored
					}
				}
				else{ //the prefix can be extended, and we are not at the end of the key
					desiredQueue = getHeap(key, queueChoice, curr.getDown(), keyPos+1); //recurse to the child
				}
			}

			curr = curr.getRight(); //continue searching down the list of siblings
		}

		return desiredQueue;
	}// end getHeap

}