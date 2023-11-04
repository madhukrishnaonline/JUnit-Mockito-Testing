package com.dao;

public interface ILoginDAO 
{
	int authenticate(String username,String password);
	int addUser(String user,String role);
}
