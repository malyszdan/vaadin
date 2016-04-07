package pl.daniel.ug.bi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pl.daniel.ug.bi.domain.Car;
import pl.daniel.ug.bi.domain.Car.Brand;

public class CarService {
	

	private List<Car> database = new ArrayList<Car>();
	

	private CarService() {
		database.add(new Car("E32",2010, 20000, Brand.BMW));
		database.add(new Car("FOCUS", 2000, 150000, Brand.FORD));
		database.add(new Car("FIESTA", 2004, 120430, Brand.FORD));
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



	public void remove(Car car) {
		database.remove(car);
	}
}
