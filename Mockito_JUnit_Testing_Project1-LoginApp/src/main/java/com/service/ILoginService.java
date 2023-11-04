package com.service;

public interface ILoginService 
{
	boolean login(String username,String password);
	String registerUser(String user,String role);
}//interface