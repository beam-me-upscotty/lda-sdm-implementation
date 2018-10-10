package com.ClassDiagrams.Design.Models;

public class Admin extends User
{
	private String superUserPassword;
    public Admin(int ID, String name, String email, String password, String superUserPassword,boolean loggedIn) {
        super(ID, name, email, password, loggedIn);
        this.superUserPassword =superUserPassword;
    }

	public boolean login(String username, String password, String suPassword)
	{
		if(super.checkCredentials(username, password))
		{
			if(superUserPassword.equals(suPassword))
			{
				setLoggedIn(true);
				return true;
			}
		}
		return false;
	}

}