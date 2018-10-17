package com.ClassDiagrams.Design.Models;

public class User
{
	private int ID;
	private String name, email, password;
	private boolean loggedIn;

	public User(int ID, String name, String email, String password, boolean loggedIn) {
		this.ID = ID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.loggedIn = loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean checkCredentials(String username, String password)
	{
		if(username.equals(email) && password.equals(this.password))
		{
			return true;
		}
		else
			return false;
	}
	public void logout() {
		loggedIn = false;
	}
	public int getID()
	{
		if(isLoggedIn())
			return ID;
		return -1;
	}
	public String getName()
	{
		if(isLoggedIn())
			return name;
		return "\0";
	}
	public void setName(String newName)
	{
		if(isLoggedIn())
			name = newName;
	}
	public String getEmail()
	{
		if(isLoggedIn())
			return email;
		return "\0";
	}
	public void setEmail(String newEmail)
	{
		if(isLoggedIn())
			email = newEmail;
	}
	public boolean isLoggedIn()
	{
		return loggedIn;
	}
	
	@Override
	public String toString(){
		return name+"("+email+")";
	}
}