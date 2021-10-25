import java.io.*;
import java.util.*;
import java.util.NoSuchElementException; 


public class CarsPQ implements CarsPQ_Inter{

	HeapPQ priceHeap;
	HeapPQ mileageHeap;
	MakeModelDLB makeModelFilter;

	//------------------------------------------------------------------------------------
	//constructor

	//remove this constructor with no arguments before final submission, it is for testing purposes only!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public CarsPQ(){
		//build cars using file

		priceHeap = new HeapPQ(0);
		mileageHeap = new HeapPQ(1);
		makeModelFilter = new MakeModelDLB();
	}

	public CarsPQ(String fileName){
		//build cars using file

		priceHeap = new HeapPQ(0);  //an indexable heap to keep track of the prices of all cars
		mileageHeap = new HeapPQ(1); //an indexable heap to keep track of the mileages of all cars
		makeModelFilter = new MakeModelDLB(); //a structure that organizes cars by their make and models
											     //this is a dlb, and at the end of a path, heaps are stored which keep track of the prices/mileages of cars with a specific make/model combination

		addCars(fileName); //reads the file passed as an argument, and creates Car objects based on the contents
	}

	//------------------------------------------------------------------------------------
	//add

	public void add(Car c) throws IllegalStateException{ 
		if(priceHeap.getIndexOfCar(c.getVIN()) != -1){  //if index stored at end of c's VIN's path in the indirection table is anything other than -1, a car with this vin is already being stored
			throw new IllegalStateException();  //throw an exception if there is already a Car with this VIN
		}

		//add this car to the priceHeap and the mileageHeap
		priceHeap.add(c);
		mileageHeap.add(c);
		//add this car to the make/model specific heap
		makeModelFilter.add(c);
	}

	//-----------------------------------------------------------------------------------
	//get

	public Car get(String vin) throws NoSuchElementException{

		int indexOfCar = priceHeap.getIndexOfCar(vin); //get the index where the car with this vin is located in priceHeap

		if(indexOfCar == -1){
			throw new NoSuchElementException(); //thorw exception if no such car (with this vin) exists
		}
		
		return priceHeap.heap[indexOfCar]; //return the Car at the specified index of priceHeap
	}

	//-----------------------------------------------------------------------------------
	//updatePrice

	public void updatePrice(String vin, int newPrice) throws NoSuchElementException{
		int indexOfCar = priceHeap.getIndexOfCar(vin); //index where car with the specified vin is located in priceHeap

		if(indexOfCar == -1){
			throw new NoSuchElementException(); //thorw exception if there is no car with the specified vin
		}

		Car carToUpdate = priceHeap.heap[indexOfCar];  //reference to the Car object we want to update
		int oldPrice = carToUpdate.getPrice(); //holds the oldPrice of the car, before updated

		HeapPQ makeModelHeap = makeModelFilter.getMakeModelHeap(carToUpdate, 0); //get the specific make,model heap that needs to be updated
		int indexMakeModel = makeModelHeap.getIndexOfCar(vin); //get the index where the specific car is located withing this heap

		carToUpdate.setPrice(newPrice); //update the car's price

		if(newPrice < oldPrice){ //if the newPrice is lower than the old price, its priority has increased
			priceHeap.swim(indexOfCar); // may have to swim to increase prioirty in the priceHeap
			makeModelHeap.swim(indexMakeModel);
		} 
		else if(newPrice > oldPrice){ //if newPrice is > old price, its priority has decreased
			priceHeap.sink(indexOfCar);//may have to sink to decrease priority in the priceHeap
			makeModelHeap.sink(indexMakeModel);
		}
		//else, newPrice is same as oldPrice, do nothing
	}

	//-------------------------------------------------------------------------------------
	//updateMileage

	public void updateMileage(String vin, int newMileage) throws NoSuchElementException{
		int indexOfCar = mileageHeap.getIndexOfCar(vin); //index where car with specified vin is located in mileageHeap
		
		if(indexOfCar == -1){
			throw new NoSuchElementException(); //thorw exception if there is no car with the specified vin
		}

		Car carToUpdate = mileageHeap.heap[indexOfCar]; //reference to the Car object we want to update
		int oldMileage = carToUpdate.getMileage(); //holds the old mileage of the Car, before updated

		HeapPQ makeModelHeap = makeModelFilter.getMakeModelHeap(carToUpdate, 1); //get the specific make,model heap that needs to be updated
		int indexMakeModel = makeModelHeap.getIndexOfCar(vin); //get the index where the specific car is located within this heap


		carToUpdate.setMileage(newMileage); //update the car's mileage

		if(newMileage < oldMileage){ //if newMileage is lower than oldMileage, its priority has incrased
			mileageHeap.swim(indexOfCar);  //may have to swim to increase priority in mileageHeap
			makeModelHeap.swim(indexMakeModel);
		}
		else if(newMileage > oldMileage){ //if newMileage is > oldMileage, its priority has decreased
			mileageHeap.sink(indexOfCar); //may have to sink to decrease priority in the mileageHeap
			makeModelHeap.sink(indexMakeModel);
		}

	}

	//------------------------------------------------------------------------------------
	//updateColor

	public void updateColor(String vin, String newColor) throws NoSuchElementException{
		Car carToUpdate = get(vin);

		//NOTE: could probably remove this, because the exception will already be thrown by the call to get() at the beginning of this function
		if(priceHeap.getIndexOfCar(vin) == -1){
			throw new NoSuchElementException(); //thorw exception if there is no car with the specified vin
		}

		carToUpdate.setColor(newColor); //update the color 
	}

	//-------------------------------------------------------------------------------------
	//remove

	public void remove(String vin) throws NoSuchElementException{
		if(priceHeap.getIndexOfCar(vin) == -1){
			throw new NoSuchElementException(); //thorw exception if there is no car with the specified vin
		}
		Car carToRemove = get(vin); //get the car to remove, so that can remove from the specific make/model heap

		//get the specific make/model heaps where the car will be stored
		HeapPQ makeModelPriceHeap = makeModelFilter.getMakeModelHeap(carToRemove, 0);
		HeapPQ makeModelMileageHeap = makeModelFilter.getMakeModelHeap(carToRemove, 1);

		//remove from regular price and mileage heaps (the ones that store all the cars)
		priceHeap.remove(vin);
		mileageHeap.remove(vin);
		//remove from make/model specific heaps
		makeModelPriceHeap.remove(vin);
		makeModelMileageHeap.remove(vin);
		

	}

	//-----------------------------------------------------------------------------------
	//getLowPrice

	public Car getLowPrice(){
		//make sure size >0
		if(priceHeap.getLogSize() > 0){
			return priceHeap.heap[0]; //the highest priority item (lowest price) is stored at the first index of priceHeap
		}

		return null; //return null if data structure is empty
	}


	public Car getLowPrice(String make, String model){

		return makeModelFilter.getMinMakeModel(make, model, 0);  //returns the highest priority (lowest priced) car of a specific make/model
																 //returns null if the data structure is empty or there is no car of this make/model
	}


	//------------------------------------------------------------------------------------
	//getLowMileage

	public Car getLowMileage(){
		if(mileageHeap.getLogSize() > 0){
			return mileageHeap.heap[0]; //the highest priority car (lowest mileage) is stored at the first index of mileageHeap
		}

		return null; //return null if data structure is empty
	}

	public Car getLowMileage(String make, String model){

		return makeModelFilter.getMinMakeModel(make, model, 1); //returns the highest prioirty (lowest mileage) car of a specific make/model
																//returns null if the data structre is empty or there is no car of this make/model
	}

	//------------------------------------------------------------------------------------
	//addCars

	private void addCars(String carObjectsFile){
		try{
			Scanner carReader = new Scanner(new File(carObjectsFile)); //created a scanner to read through the file
			carReader.useDelimiter("[:\\s]+"); //delimiter is now set to either ':' or wihtespace '\\s'
			while(carReader.hasNext()){ //while there is another thing to read in the file
				
				//gather inputs to build a car object (these variables will contain the car's fields if the line is not a comment)
				String carVin = carReader.next(); //carVin will either hold a new car's vin, or it will signify that the current line is a comment
				if(carVin.charAt(0) == '#'){  //line is a comment, skip reading it
					carReader.nextLine(); //move the scanner to the beginning of the next line
					continue; //restart loop with scanner at the next line
				}
				else{
					String carMake = carReader.next();
					String carModel = carReader.next();
					int carPrice = carReader.nextInt();
					int carMileage = carReader.nextInt();
					String carColor = carReader.next();
				
					//create a new car using the data read from the file
					Car newCar = new Car(carVin, carMake, carModel, carPrice, carMileage, carColor);
					this.add(newCar); //add newCar to the heap priority queue
				}
			}
			carReader.close(); //close the scanner
		}
		catch(FileNotFoundException e){
			System.out.println("file not found -> " + carObjectsFile);
		}
	}

}