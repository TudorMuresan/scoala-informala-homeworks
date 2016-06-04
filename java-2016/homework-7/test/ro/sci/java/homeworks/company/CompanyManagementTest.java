package ro.sci.java.homeworks.company;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.sun.istack.internal.NotNull;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CompanyManagementTest {
	
	@Test
	public void readEmployeeInputText() throws IOException{
		String fileName = "ListaAngajati.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		assertNotNull(br);
		assertNotNull(br.readLine());
	}
	
	@Test
	public void readEmployeeInputTextWhenTextDetailsAreCorrect()throws IOException{
		String firstLineMock = "Ion Nicolae    |    Junior Java Developer   |3";
		String[] chunks = firstLineMock.split("\\|");
		Integer seniorityValue = Integer.parseInt(chunks[2]);
		assertTrue(chunks.length ==3);
		assertTrue(chunks[0].length()>2);
		assertTrue(chunks[1].length()>2);
		assertTrue(seniorityValue >0);		
	}
	
	@Test(expected = IncorrectInputException.class)
	public void readEmployeeInputTextWhenDetailsAreIncorrect()throws IncorrectInputException,IOException{
		String employeeListName = "ListaAngajatiWrongValues.txt";
		CompanyManagement compTest = new CompanyManagement();
		compTest.createEmployees(employeeListName);		
	}
	
	@Test
	public void whenAllDetailsAreCorrectApplicationListsTheEmployeesWithoutParkingLot() throws IncorrectInputException,IOException{
		CompanyManagement compTest = new CompanyManagement();
		compTest.initApplication();
		assertEquals("Application Finish", compTest.getStatus());
	}
	

	
}
