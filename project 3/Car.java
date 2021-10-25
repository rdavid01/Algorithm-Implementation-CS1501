
public class Car implements Car_Inter{
	private String vin;
	private String make;
	private String model;
	private int price;
	private int mileage;
	private String color;

	public Car(String newVin, String newMake, String newModel, int newPrice, int newMileage, String newColor){
		this.vin = newVin;
		this.make = newMake;
		this.model = newModel;
		this.price = newPrice;
		this.mileage = newMileage;
		this.color = newColor;
	}
	
	public String getVIN(){

		return vin;
	}

	public String getMake(){

		return make;
	}

	public String getModel(){

		return model;
	}

	public int getPrice(){

		return price;
	}

	public int getMileage(){

		return mileage;
	}

	public String getColor(){

		return color;
	}

	public void setPrice(int newPrice){

		price = newPrice;
		//changes indirection queue and indirection dlb when called from carsPQ
	}

	public void setMileage(int newMileage){

		mileage = newMileage;
		//changes indirection queue and indirection dlb when called form carsPQ
	}

	public void setColor(String newColor){

		color = newColor;
	}

	public void printCar(){
		if(this != null){
			System.out.println("vin: " + this.vin);
			System.out.println("make & model: " + this.make + " " + this.model);
			System.out.println("price: " + this.price + ", mileage: " + this.mileage);
			System.out.println("color: " + this.color);
		}
		else{
			System.out.println("car is null");
		}
	}

}