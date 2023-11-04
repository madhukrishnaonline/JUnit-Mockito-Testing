package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@SuppressWarnings("unchecked")
public class MockvsSpyTest 
{
	@Test
	public void testMockAndSpy()
	{
		List<String> listMock = Mockito.mock(ArrayList.class);//totally mock class/object
		List<String> listSpy = Mockito.spy(ArrayList.class);//partially/half mock class/object
		
		listMock.add("Madhu");//no method functionality null object = 0
		Mockito.when(listMock.size()).thenReturn(1);//providing Stubbing on mock object = 1
		Mockito.when(listMock.toString()).thenReturn("madhu");//providing stubbing on mock object = madhu
		listSpy.add("Krishna");//whenever invoked method is not there in spy object it'll uses real object = 1
		Mockito.when(listSpy.size()).thenReturn(10);//stubbing on spy object = 10
		
		System.out.println("Mock Object :: "+listMock+"-"+listMock.size()
		                  +"\nSpy Object :: "+listSpy+"-"+listSpy.size());
	}//method
	
}//class