package pl.daniel.ug.bi.services;

import java.util.ArrayList;
import java.util.List;

import pl.daniel.ug.bi.domain.User;

public class UserService {
	
	private List<User> userList = new ArrayList<User>();
	

	
	private UserService() {
		userList.add(new User("admin", "admin", "admin@anon.pl"));
	}
	
	private static UserService instance = new UserService();
	
	public static UserService getInstance() {
		return instance;
	}
	
	
	public boolean newAccout(User user){
		if (!userList.contains(user)){
			userList.add(user);
			return true;
		}
		return false;
	}

	public void login(User user) throws BadPassEx, NoSuchUserEx{
		for(User u : userList){
			if(u.equals(user)){
				if (u.getPass().equals(user.getPass()))
					return;
				else
					throw new BadPassEx();
			}
		}
		throw new NoSuchUserEx();
	}

	
	
	public class NoSuchUserEx extends Exception{
		
	}
	
	public class BadPassEx extends Exception{
		
	}
	
}
