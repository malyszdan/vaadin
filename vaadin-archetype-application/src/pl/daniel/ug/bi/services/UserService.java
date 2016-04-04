package pl.daniel.ug.bi.services;

import pl.daniel.ug.bi.domain.User;

public class UserService {
	
	
	/// Singleton
	
	private UserService() {
		// TODO Auto-generated constructor stub
	}
	
	private static UserService instance = new UserService();
	
	public static UserService getInstance() {
		return instance;
	}
	
	
	////
	
	
	
	
	
	
	
	public void newAccout(User user){
		throw new NoSuchMethodError();
	}

	public boolean login(User user) {
		//throw new NoSuchMethodError();
	
		return true;
	}

}
