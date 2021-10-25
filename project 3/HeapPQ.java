
public class HeapPQ{

	// private int root;
	private int logSize; //logical size of the heap
	private int priorityField;  //designates which field determines priority
	public Car[] heap;  //the heap is implemented using an array so that we can get constant time access to each Car
	public IndirectionDLB indirectionTable; //makes the heap indexable

	//------------------------------------------------------------------------------
	//constructor

	public HeapPQ(int fieldChoice){
		//root is considered index 0
		logSize = 0;
		priorityField = fieldChoice; //0 = price, 1 = mileage
									 // use of int allows a large # of diff fields to organize by if wanted to expand this in the future
		heap = new Car[20];
		indirectionTable = new IndirectionDLB();
	}

	//------------------------------------------------------------------------------
	//add
	public void add(Car c){
		if(heap.length == logSize){
			upSize();  //increase the capacity of the heap if it is full
		}
		
		heap[logSize] = c; //add the new Car to the heap
		indirectionTable.add(c.getVIN(), logSize); //add new car to indirection table

		logSize++; //increase the counter for logical size of the heap
		swim(logSize-1); //swim() the newly added object to maintain heap property	
	}

	//-------------------------------------------------------------------------------
	//remove
	public void remove(String vin){
		int indexOfCarToRemove = this.getIndexOfCar(vin);
		remove(indexOfCarToRemove);
	}

	public void remove(int removalIndex){
		indirectionTable.add(heap[removalIndex].getVIN(), -1); //get the vin of the car we want to remove, access its path in the indirection table, mark this car's index as -1 to represent that it isn't part of the heap anymore
		if(removalIndex != logSize-1){ //only perform the following move if removalIndex is not the last index where an object is stored
			heap[removalIndex] = heap[logSize-1]; //move the object at the last index into the removal index to replace the object we want to remove
			indirectionTable.add(heap[removalIndex].getVIN(), removalIndex); //update the index of the object we placed into the removalIndex 
		}
		heap[logSize-1] = null; //change the contents of the old final index to null
		
		logSize--;
		
		if(removalIndex < logSize-1){
			sink(removalIndex); //sink the object to maintain heap property
		}
	}

	//---------------------------------------------------------------------------------
	//swim

	public void swim(int childIndex){
		int parentIndex = (childIndex-1)/2;

		if(priorityField == 0){  //priorityField == 0 means that comparing based on price
			boolean childLess = heap[parentIndex].getPrice() > heap[childIndex].getPrice(); //determines if parent is higher priority than child
																	 // true if parent higher priority, false if child higher priority
			while(childIndex > 0 && childLess){
				//swap the objects at the parent and child indicies
				Car temp = heap[parentIndex];
				heap[parentIndex] = heap[childIndex];
				heap[childIndex] = temp;
				//update the indicies of the swapped objects in the indirection table
				indirectionTable.add(heap[parentIndex].getVIN(), parentIndex);
				indirectionTable.add(heap[childIndex].getVIN(), childIndex);

				childIndex = parentIndex; //update childIndex, child is where parent used to be
				parentIndex = (childIndex-1)/2; //update parentIndex, new parent is at childIndex/2
				childLess = heap[parentIndex].getPrice() > heap[childIndex].getPrice(); //update childLess
			}
		}
		else if(priorityField == 1){ //priorityField == 1 means that comparing based on mileage
			boolean childLess = heap[parentIndex].getMileage() > heap[childIndex].getMileage(); //determines if parent is higher priority than child
																	 // true if parent higher priority, false if child higher priority
			while(childIndex > 0 && childLess){
				//swap the objects at the parent and child indicies
				Car temp = heap[parentIndex];
				heap[parentIndex] = heap[childIndex];
				heap[childIndex] = temp;
				//update the indicies of the swapped objects in the indirection table
				indirectionTable.add(heap[parentIndex].getVIN(), parentIndex);
				indirectionTable.add(heap[childIndex].getVIN(), childIndex);

				childIndex = parentIndex; //update childIndex, child is where parent used to be (moved the Car object we are calling swim() on to this position)
				parentIndex = (childIndex-1)/2; //update parentIndex, new parent is at childIndex/2
				childLess = heap[parentIndex].getMileage() > heap[childIndex].getMileage(); //update childLess
			}
		}

	}

	//-------------------------------------------------------------------------------
	//sink

	public void sink(int parentIndex){
		int leftChildIndex = (2*parentIndex)+1;
		int rightChildIndex = leftChildIndex+1;
		boolean comparatorLess; //determines if parent is higher priority than child
							   // true if parent higher priority, false if child higher priority
		boolean lcLess; //determines if leftChild has higher priority than rightChild
						//true if left higher priority, false if right higher priority
		int comparator; //either left or right child, whichever is higher priority
					  //the parent needs to be swaped with the higher priority child
					  //comparator is compared to the parent to see if need to swap
			
		if(priorityField == 0){  //comparing based on price
			while(rightChildIndex < logSize){  //starting array at index 0, so don't want it to be equal to logSize
											  //as long as rightChildIndex is less than logSize, gaurantee that both children wont be null
				comparator = rightChildIndex; //by default, the right child is the comparator, only make left child the comparator if it is higher priority than the right child
				//determine which of the two children have higher prioirty
				lcLess = heap[leftChildIndex].getPrice() < heap[rightChildIndex].getPrice();
				if(lcLess){
					comparator = leftChildIndex; //left child is higher priority, make that the comparator
				}
				//compare the higher prioirty child to the parent
				comparatorLess = heap[parentIndex].getPrice() > heap[comparator].getPrice();
				if(!comparatorLess){ //the higher priority child has a lower priority than its parent
					break;  //break out of the loop if the parent is already higher prioirty
				}

				//swap the objects at the parent and child indicies
				Car temp = heap[parentIndex];
				heap[parentIndex] = heap[comparator];
				heap[comparator] = temp;
				//update the indicies of the swapped objects in the indirection table
				indirectionTable.add(heap[parentIndex].getVIN(), parentIndex);
				indirectionTable.add(heap[comparator].getVIN(), comparator);


				parentIndex = comparator; //update parentIndex,  (moved the Car we are calling sink() on to this position)
				leftChildIndex = (2*parentIndex)+1; //update left child index 
				rightChildIndex = leftChildIndex+1; //update right child index

			}

			//NOTE: PROBLEM FIXED: needed to add this extra conditional for cases where children are null, also changed the above while() condition from using leftChildIndex to rightChildIndex
			//NOTE: could create a boolean and activate it within the while above so that wont also enter this if statement, 
			      //but doesn't matter because shouldn't be able to go inside the second if statement if the sink() was successful in the first place
			if(leftChildIndex < logSize && heap[leftChildIndex] != null){ //case for if left child != null, but right child == null
																		  //need to make sure leftChild index is not out of bounds
																			 //will thorow ArrayIndexOutOfBounds if do not account for the fact that the array may not have upSized
																			 //(in such a case the array would not be long enough to include a cell at position leftChildIndex)

				if(heap[leftChildIndex].getPrice() < heap[parentIndex].getPrice()){ //must test to see if need to swap parent and left child
																					//there will never be a case where left child == null, but right child != null
																						//because heap is like a complete tree, filled in from left to right on each level
					//swap the parent and the left child if there is a heap priority inversion
					Car temp = heap[parentIndex];
					heap[parentIndex] = heap[leftChildIndex];
					heap[leftChildIndex] = temp;
					//update the indicies of the swapped objects in the indirection table
					indirectionTable.add(heap[parentIndex].getVIN(), parentIndex);
					indirectionTable.add(heap[leftChildIndex].getVIN(), leftChildIndex);
				}
			}
		}
		else if(priorityField == 1){ //comparing based on mileage
			while(rightChildIndex < logSize){ //starting at index 0, so dont want to run if equal
				comparator = rightChildIndex;
				//determine which of the two children have higher prioirty
				lcLess = heap[leftChildIndex].getMileage() < heap[rightChildIndex].getMileage();
				if(lcLess){
					comparator = leftChildIndex; //left child is higher priority, make that the comparator
				}
				//compare the higher prioirty child to the parent
				comparatorLess = heap[parentIndex].getMileage() > heap[comparator].getMileage();
				if(!comparatorLess){
					break;  //break out of the loop if the parent is already higher prioirty
				}

				//swap the objects at the parent and child indicies
				Car temp = heap[parentIndex];
				heap[parentIndex] = heap[comparator];
				heap[comparator] = temp;
				//update the indicies of the swapped objects in the indirection table
				indirectionTable.add(heap[parentIndex].getVIN(), parentIndex);
				indirectionTable.add(heap[comparator].getVIN(), comparator);


				parentIndex = comparator; //update parentIndex,  (moved the Car we are calling sink() on to this position)
				leftChildIndex = (2*parentIndex)+1; //update left child index 
				rightChildIndex = leftChildIndex+1; //update right child index

			}
			if(leftChildIndex < logSize && heap[leftChildIndex] != null){ //case for if left child != null, but right child == null
				if(heap[leftChildIndex].getMileage() < heap[parentIndex].getMileage()){ //must test to see if need to swap parent and left child
																					      //there will never be a case where left child == null, but right child != null
																						  //because heap is like a complete tree, filled in from left to right on each level
					//swap the parent and the left child if there is a heap priority inversion
					Car temp = heap[parentIndex];
					heap[parentIndex] = heap[leftChildIndex];
					heap[leftChildIndex] = temp;
					//update the indicies of the swapped objects in the indirection table
					indirectionTable.add(heap[parentIndex].getVIN(), parentIndex);
					indirectionTable.add(heap[leftChildIndex].getVIN(), leftChildIndex);
				}
			}
		}
	}

	//--------------------------------------------------------------------------------
	//getIndexOfCar

	public int getIndexOfCar(String vin){
		return indirectionTable.contains(vin);
	}


	//------------------------------------------------------------------------------
	//upsize

	// makes a new heap (biggerHeap) that is double the size of the old heap
	// copies the entries from old MyDeque into biggerHeap, while maintaining the same relative order
	private void upSize(){  
		Car[] biggerHeap = new Car[heap.length * 2]; // creates a new deque twice the length of the full deque

		
		for(int i=0; i < logSize; i++){   // copying entries from old heap into biggerHeap
			biggerHeap[i] = heap[i];
		}

		heap = biggerHeap;  
	}

	//-------------------------------------------------------------------------------
	//getLogSize

	public int getLogSize(){
		return logSize;
	}

}