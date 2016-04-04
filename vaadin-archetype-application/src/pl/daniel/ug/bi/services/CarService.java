package pl.daniel.ug.bi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pl.daniel.ug.bi.domain.Car;
import pl.daniel.ug.bi.domain.Car.Brand;

public class CarService {
	

	private List<Car> database = new ArrayList<Car>();
	
	/// Singleton
	private CarService() {
		database.add(new Car("",2010,200,Brand.BMW));
		database.add(new Car());
		database.add(new Car());
		database.add(new Car());
	}
	
	private static CarService instance = new CarService();
	
	public static CarService getInstance() {
		return instance;
	}
	
	
	
	public List<Car> getAllCars(){
		return database;
	}



	public void addCar(Car copy) {
		database.add(copy);
	}
}
