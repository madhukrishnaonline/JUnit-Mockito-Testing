package com.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dao.ILoginDAO;
import com.service.ILoginService;
import com.service.LoginServiceImpl;

@TestInstance(value = Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class LoginServiceTest 
{
	@Mock           //to generate Mock object
	private ILoginDAO loginDAOMock;

	//@Spy                            //to generate spy object  
	//private ILoginDAO loginDAOSpy; //Ambiguity problem, so configure manually
	
	@InjectMocks     //to inject mock or spy object to service class
	private LoginServiceImpl loginService;
	
	
	public LoginServiceTest() {
      // MockitoAnnotations.initMocks(this);  //deprecated
		
		MockitoAnnotations.openMocks(this);
	}

	/*@BeforeAll
	public void createOnce() 
	{
		loginDAO = Mockito.mock(ILoginDAO.class);// mock(-) will generate inMemory class implementing
		// ILoginDAO having null method definition for authenticate method
		loginService = new LoginServiceImpl(loginDAO);

		System.out.println("Class :: " + loginDAO.getClass() + "\nInterfaces :: "
	         			  + Arrays.toString(loginDAO.getClass().getInterfaces()));
	}// createOnce

	@AfterAll
	public void clearOnce() {
		loginDAO = null;
		loginService = null;
	}// clearOnce */

	@Test
	@Order(1)
	public void testWithValidCredentials() 
	{
		System.out.println("LoginServiceTest.testWithValidCredentials()");
     		// provide stub (Temporary Functionalities) for DAO's authenticate method
		//Mockito.when(loginDAOMock.authenticate("madhu", "mk9876")).thenReturn(1);
		             //or
		//Behavioral Driven Development  //More readability
		BDDMockito.given(loginDAOMock.authenticate("madhu","mk9876")).willReturn(1);
		
		// unit testing
		assertTrue(loginService.login("madhu", "mk9876"));
	}// testWithValidCredentials

	@Test
	@Order(2)
	public void testWithInvalidCredentials() {
		System.out.println("LoginServiceTest.testWithInvalidCredentials()");

		Mockito.when(loginDAOMock.authenticate("madhu", "mk9876")).thenReturn(1);
		assertFalse(loginService.login("madhu", "mk6789"));
	}// testWithInValidCredentials

	@Test
	@Order(3)
	public void testWithEmptyCredentials() {
		System.out.println("LoginServiceTest.testWithEmptyCredentials()");

		assertThrows(IllegalArgumentException.class, () -> loginService.login("", ""));
	}// testWithEmptyCredentials

	@Test
	@Order(4)
	public void testRegisterWithSpy() 
	{
		System.out.println("LoginServiceTest.testRegisterWithSpy()");
		
		ILoginDAO loginDAOSpy = Mockito.spy(ILoginDAO.class);//Mock on spy Object
		
		ILoginService loginService = new LoginServiceImpl(loginDAOSpy);
		
		loginService.registerUser("madhu","visitor");
		loginService.registerUser("krishna","admin");
		loginService.registerUser("mk","");
		
		Mockito.verify(loginDAOSpy,Mockito.times(0)).addUser("madhu","visitor");
		Mockito.verify(loginDAOSpy,Mockito.times(1)).addUser("krishna","admin");
		Mockito.verify(loginDAOSpy,Mockito.never()).addUser("madhu","");
	}
	
	
}// class