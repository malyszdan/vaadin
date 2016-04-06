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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
