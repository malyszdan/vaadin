package pl.daniel.ug.bi.domain;

import java.util.ArrayList;

public class User {
	private String name;
	private String pass;
	
	
	public User() {
		super();
	}

	public User(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}

	public String getName() {  // -> user has "name" property, name is propertyId
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
}
