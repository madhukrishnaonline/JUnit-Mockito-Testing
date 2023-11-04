package com.service;

import com.dao.ILoginDAO;

public class LoginServiceImpl implements ILoginService 
{	
	private ILoginDAO loginDAO;

	public LoginServiceImpl(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}// constructor

	public boolean login(String username, String password) {
		if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("Empty Credentials");
		} // if
		int count = loginDAO.authenticate(username, password);
		System.out.println("count :: " + count);
		if (count == 0) {
			return false;
		} // if
		else {
			return true;
		} // else
	}// login

	@Override
	public String registerUser(String user, String role) {
		if (!role.equalsIgnoreCase("") && !role.equalsIgnoreCase("visitor")) {
			loginDAO.addUser(user, role);
			return "user Added";
		} // if
		else {
			return "user not Added";
		} // else
	}// method

}// class