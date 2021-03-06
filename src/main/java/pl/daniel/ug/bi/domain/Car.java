package pl.daniel.ug.bi.domain;

public class Car {

	private String model;
	private int yearProd;
	private double distance;
	private Brand brand;
	private boolean isFree;
		
	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public Car() {
		super();
	}
	public Car(String model){
		this.model = "";
	}
	
	public Car(String model, int yearProd, double distance, Brand brand) {
		super();
		this.model = model;
		this.yearProd = yearProd;
		this.distance = distance;
		this.brand = brand;
		this.isFree = true;
	}


	public enum Brand {
		AUDI, BMW, CHEVROLET, FIAT, DACIA, FORD, ŁADA, MAZDA, TOYOTA, VW;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYearProd() {
		return yearProd;
	}

	public void setYearProd(int yearProd) {
		this.yearProd = yearProd;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Brand getBrand() {
		return brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Car copy() {
		return new Car(model,yearProd,distance,brand);
	}
}
