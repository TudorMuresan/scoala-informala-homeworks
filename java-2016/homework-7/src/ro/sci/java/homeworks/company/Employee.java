package ro.sci.java.homeworks.company;
/**
* The Employee class describes the states and the behavior of an object of type Employee.
* @author Tudor Muresan
*/
public class Employee implements Comparable<Employee>{
	private String name;
	private int seniority;
	private String role;
	private boolean parkingSpace;

	/**
	 * Setter for the role of an employee.
	 * @param role Employees role.
	 */
	public void setRole(String role){
		this.role = role;
	}
	
	/**
	 * Setter for the complete name of an employee.
	 * @param name Employees name.
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Setter for the seniority of an employee in a company.
	 * @param seniority Employees seniority.
	 */
	public void setSeniority(int seniority){
		this.seniority = seniority;
	}
	
	/**
	 * Getter for the role of an employee.
	 * @return name Employees name.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Setter for the parking diponibility of an employee.
	 * @param hasParking Change employees status to true when he will get a parking lot.
	 */
	public void setParkingSpace(boolean hasParking){
		parkingSpace = hasParking;
	}
	
	/**
	 * Getter for the employee parking disponibility.
	 * @return parkingSpace Returns the state of the parking place for an employee. Returns true if employee has a parking place or false otherwise.
	 */
	public boolean getParkingSpace(){
		return parkingSpace;
	}
	
	/**
	 * Getter for the role of an employee.
	 * @return role Employees role.
	 */
	public String getRole(){
		return role;
	}
	
	/**
	 * Getter for the seniority of an employee.
	 * @return seniority Employees seniority.
	 */
	public int getSeniority(){
		return seniority;
	}
	
	/**
	 * Prints all the details about this employee.
	 * @return Information about the employee.
	 */
	public String printEmployeeDetails(){
		return "The employee name is " + name + ", with the role of " + role + " and the seniority of " + seniority;
	}

	
	@Override
	/**
	 * Determines the criteria for comparing this employee with another employee.
	 * @param emp Criteria for comparing different employees.
	 * @return the result of the comparison based on the determined criteria.
	 */
	public int compareTo(Employee emp) {
		if(this.seniority < emp.getSeniority()){
			return -1;
		}
		else if(this.seniority > emp.getSeniority()){
			return 1;
		}
		else if(getRole().contains("Manager")){
			return 1;
		}
		else if(!getRole().contains("Manager")){
			return -1;
		}
		return 0;
	}
}
