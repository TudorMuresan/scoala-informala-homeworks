package ro.sci.java.homeworks.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
* The CompanyManagement handles employees and sends them to the Company class that will further handle the employee.
* @author Tudor Muresan
*/
public class CompanyManagement {
	private Company myCompany;
	private String status="";
	
	/** Main method of the class. 
	 * @param args Command Line arguments.
	*/
	public static void main(String[] args){
		
		CompanyManagement cm = new CompanyManagement();
		cm.initApplication();
	}
	
	/** The starting point of the application.
	 * Here will be all the commands - get information about employees by departments, by the case if they own a parking lot or not.
	*/
	public void initApplication(){
		myCompany = new Company(5);
		status = "Starting Application";
		String listOfEmployees = "ListaAngajati.txt";
		try{
			createEmployees(listOfEmployees);
		}
		catch(IncorrectInputException e){
			e.printStackTrace();
		}
		
		///--->emp details commands
		//myCompany.printEmployeeByDepartment();
		myCompany.distributeParkings();
		getEmmployeesWithoutParking(false);//if parameter is true-->lists all the employees with parking lots
											 //else lists the employees without parking lot.
		//<---
	}
	
	/** 
	 * Reads a list of employees from the IO input and handles them further. 
	 * @param stringList The filename that needs to be handled.
	 * @exception IncorrectInputException incorrect values exception.
	*/
	public void createEmployees(String stringList) throws IncorrectInputException{
		try(BufferedReader br = new BufferedReader(new FileReader(stringList))) {
			String line="";
			int header =0;
			while ((line = br.readLine()) != null) {
				if(header>0){
					String[] chunks = line.split("\\|");
					int seniority = Integer.parseInt(chunks[2].trim());
					if(seniority<=0 || seniority>50 || seniority!=(int)seniority){
						throw new IncorrectInputException("Inorrect seniority value for the employee."
								+ "\nMust be positive integer between 1 and 50");
					}
					
					Employee emp = new Employee();
					emp.setName(chunks[0]);
					emp.setRole(chunks[1]);
					emp.setSeniority(seniority);
					addEmployee(emp);
				}
				header++;
				
			 }
		} 
		catch(NumberFormatException e){
			e.printStackTrace();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** 
	 * Getter for the status of the application.
	 * The status will inform when the application starts and when it will finish the process.
	 * @return status The status of the process.
	*/
	public String getStatus(){
		return status;
	}
	
	/** 
	 * Adds employees to the company that will further handle them properly.
	 * @param emp Employee to add to the company.
	*/
	private void addEmployee(Employee emp){
		myCompany.addEmployee(emp);
	}
	
	/** 
	 * Prints to the console all the company's employees that don't own a parking lot or that own a parking lot.
	 * @param If true, it will print all the employees that have a parking lot, 
	 * if false it will print the employees without a parking lot.
	*/
	private void getEmmployeesWithoutParking(boolean bool) {
		for(Employee emp : myCompany.getParkingList()){
			if(!bool && !emp.getParkingSpace()){
				System.out.println(emp.getName() + " --> " + emp.getRole() + " " + emp.getSeniority() + " years of seniority "+ " hasn't got a parking lot");
			}
			else if(bool && emp.getParkingSpace()){
				System.out.println(emp.getName() + " --> " + emp.getRole() + " " + emp.getSeniority() + " years of seniority "+ " has a parking lot");
			}
		}
		status = "Application Finish";
	}
}
