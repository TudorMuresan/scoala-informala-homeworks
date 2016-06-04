package ro.sci.java.homeworks.company;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
* The Company class holds information about the company. Defines it's number of parking lots, holds employees in collections.
* @author Tudor Muresan
*/
public class Company {

	private int maxParkingLots;
	private ArrayList<ParkingLot> parking;
	private Map<String ,Set<Employee>> employeesList = new LinkedHashMap<>();
	private List<Employee> listForParkings = new ArrayList<>();
	/**
	  * Constructor.
	  * @param parkingSeats The number of the parking seats belonging to the company.
	  */
	public Company(int parkingSeats){
		maxParkingLots = parkingSeats;
		parking = new ArrayList<>();
		for(int i=1;i<=maxParkingLots;i++){
			parking.add(new ParkingLot(i));
		}
	}
	
	//the collections defined to hold employees depending on their role in the company.
	private Set<Employee> managers = new TreeSet<>();
	private Set<Employee> juniorDevelopers = new TreeSet<>();
	private Set<Employee> seniorDevelopers = new TreeSet<>();
	private Set<Employee> qaEngineers = new TreeSet<>();
	private Set<Employee> humanResources = new TreeSet<>();
	private Set<Employee> NAEmployees = new TreeSet<>();
	
	/**
	 * Getter for the junior developers from the company.
	 * @return a Collection with all the junior developers from the company.
	*/
	public Set<Employee> getJuniorDevelopers(){
		return juniorDevelopers;
	}

	/**
	 * Getter for the senior developers from the company.
	 * @return a Collection with all the senior developers from the company.
	*/
	public Set<Employee> getSeniorDevelopers(){
		return seniorDevelopers;
	}

	/**
	 * Getter for the qa engineers from the company.
	 * @return a Collection with all the qa engineers from the company.
	*/
	public Set<Employee> getQAEngineers(){
		return qaEngineers;
	}

	/**
	 * Getter for the human resource department from the company.
	 * @return a Collection with all the human resource department from the company.
	*/
	public Set<Employee> getHumanResources(){
		return humanResources;
	}

	/**
	 * Getter for the employees that are not appointed in the company.
	 * @return a Collection with all the unappointed employees from the company.
	*/
	public Set<Employee> getNAEmployees(){
		return NAEmployees;
	}

	/**
	 * Getter for the managers department from the company.
	 * @return a Collection with all the managers department from the company.
	*/
	public Set<Employee> getManagers(){
		return managers;
	}

	/**
	 * Distribute all the employees accordingly to their role in the company.
	 * Finally Maps all the employees with the key being the department and the value the collection of department.
	 * @param employee The employee that the method needs to distribute.
	*/
	public void addEmployee(Employee employee){
		if(employee.getRole().contains("Manager")){
			managers.add(employee);
			employeesList.put("MANAGERS", managers);
		}else if(employee.getRole().contains("Junior")){
			juniorDevelopers.add(employee);				
			employeesList.put("JUNIOR_DEVELOPERS", juniorDevelopers);
		}else if(employee.getRole().contains("Senior")){
			seniorDevelopers.add(employee);
			employeesList.put("SENIOR_DEVELOPERS", seniorDevelopers);
		}else if(employee.getRole().contains("Human")){
			humanResources.add(employee);
			employeesList.put("HUMAN_RESOURCES", humanResources);
		}else if(employee.getRole().contains("QA")){
			qaEngineers.add(employee);
			employeesList.put("QA_ENGINEERS", qaEngineers);
		}
		else{
			NAEmployees.add(employee);
			employeesList.put("NA_Position", NAEmployees);
		}
	}

	/**
	 * Creates a list with the employee so that they can be sorted and given a parking lot.
	 * @return The created collection with the company's employees.
	*/
	public List<Employee> getParkingList(){
		for(Map.Entry<String, Set<Employee>> entry : employeesList.entrySet()) {
			for (Employee value : entry.getValue()) {
				if(!listForParkings.contains(value)){
					listForParkings.add(value);
				}
			}
		}
		return listForParkings;
	}
	
	/**
	 * Prints on the console all the employees from the company linked to the department they belong to.
	*/
	public void printEmployeeByDepartment() {
		for(Map.Entry<String, Set<Employee>> entry : employeesList.entrySet()) {
			for (Employee value : entry.getValue()) {
				System.out.println(entry.getKey() + "/" + value.getName());
			}
		}
	}
	
	/**
	 * Getter for the Mapping collection with the employees.
	 * @return employeeList The mapped collection of the employees. 
	 */
	public Map<String, Set<Employee>> getEmployeeList(){
		return employeesList;
	}
	
	/**
	 *Getter for the parking lots of the company. 
	 * @return the list with all the parking lots that the company owns.
	 */
	public ArrayList<ParkingLot> getParkingLots(){
		return parking;
	}
	
	/**
	 * Distributes the parking lots to the employees, accordingly to their seniority inside the company. 
	 */
	public void distributeParkings(){
		int parkingSeats =0;
		Collections.sort(getParkingList());
		Collections.reverse(getParkingList());
		for(Employee emp: getParkingList()){
			if(getParkingLots().size() >parkingSeats){
				parkingSeats++;
				emp.setParkingSpace(true);
			}
		}
	}
}
