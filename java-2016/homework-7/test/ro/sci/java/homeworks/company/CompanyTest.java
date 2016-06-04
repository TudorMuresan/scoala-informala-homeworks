package ro.sci.java.homeworks.company;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class CompanyTest {

	@Test
	public void companyWithParkingSeats() {
		int parkingSeats = 5;
		Company comp = new Company(parkingSeats);
		assertEquals(5, comp.getParkingLots().size());
	}

	@Test
	public void whenAddingEmployeesPuttingThemInRightCollection() {
		// given
		Set<Employee> juniorDevelopers = new TreeSet<>();
		String name = "Ion Mihalache";
		String role = "Junior Java Developer";
		int seniority = 3;
		Company cm = mock(Company.class);
		Employee employeeSample = new Employee();
		employeeSample.setName(name);
		employeeSample.setRole(role);
		employeeSample.setSeniority(seniority);
		// when
		cm.addEmployee(employeeSample);
		when(cm.getJuniorDevelopers()).thenReturn(juniorDevelopers);
		// then
		assertEquals(cm.getJuniorDevelopers(), juniorDevelopers);

	}

	@Test
	public void whenAddingEmployeesPuttingThemInDifferentCollections() {
		// given
		Set<Employee> juniorDevelopers = new TreeSet<>();
		String name = "Ion Mihalache";
		String role = "Junior Java Developer";
		int seniority = 3;

		Set<Employee> seniorDevelopers = new TreeSet<>();
		String seniorName = " Mihalache";
		String seniorRole = "Senior JavaDeveloper";
		int seniorSeniority = 5;

		Set<Employee> managers = new TreeSet<>();
		String managerName = "Vasile Pop";
		String managerRole = "Project Manager";
		int managerSeniority = 8;

		Company cm = mock(Company.class);
		Employee employeeSample = new Employee();
		employeeSample.setName(name);
		employeeSample.setRole(role);
		employeeSample.setSeniority(seniority);

		Employee employeeSample2 = new Employee();
		employeeSample2.setName(seniorName);
		employeeSample2.setRole(seniorRole);
		employeeSample2.setSeniority(seniorSeniority);

		Employee employeeSample3 = new Employee();
		employeeSample3.setName(managerName);
		employeeSample3.setRole(managerRole);
		employeeSample3.setSeniority(managerSeniority);
		// when
		cm.addEmployee(employeeSample);
		cm.addEmployee(employeeSample2);
		cm.addEmployee(employeeSample3);
		when(cm.getJuniorDevelopers()).thenReturn(juniorDevelopers);
		when(cm.getSeniorDevelopers()).thenReturn(seniorDevelopers);
		when(cm.getManagers()).thenReturn(managers);
		// then
		assertEquals(cm.getJuniorDevelopers(), juniorDevelopers);
		assertEquals(cm.getSeniorDevelopers(), seniorDevelopers);
		assertEquals(cm.getManagers(), managers);

	}

	@Test
	public void distributeParkingLotsToEmployeeWithLongerSeniority() {
		int parkingSeats = 1;
		String name = "Ion Mihalache";
		String role = "Junior Java Developer";
		int fewSeniority = 3;

		String seniorName = " Mihalache";
		String seniorRole = "Senior JavaDeveloper";
		int longSeniorSeniority = 5;

		Company cm = new Company(parkingSeats);
		Employee employeeSample = new Employee();
		employeeSample.setName(name);
		employeeSample.setRole(role);
		employeeSample.setSeniority(fewSeniority);

		Employee employeeSample2 = new Employee();
		employeeSample2.setName(seniorName);
		employeeSample2.setRole(seniorRole);
		employeeSample2.setSeniority(longSeniorSeniority);
		cm.addEmployee(employeeSample);
		cm.addEmployee(employeeSample2);

		// when
		cm.distributeParkings();

		// then
		assertEquals(employeeSample.getParkingSpace(), false);
		assertEquals(employeeSample2.getParkingSpace(), true);
	}

	@Test
	public void whenThreeDepartmentsPopulatedTheNewMapWillHaveTheSizeEqualToTheNumberOfNonEmpyDepartments() {
		// given
		String name = "Ion Mihalache";
		String role = "Junior Java Developer";
		int seniority = 3;

		String seniorName = " Mihalache";
		String seniorRole = "Senior JavaDeveloper";
		int seniorSeniority = 5;

		String managerName = "Vasile Pop";
		String managerRole = "Project Manager";
		int managerSeniority = 8;

		Company cm = new Company(5);
		Employee employeeSample = new Employee();
		employeeSample.setName(name);
		employeeSample.setRole(role);
		employeeSample.setSeniority(seniority);

		Employee employeeSample2 = new Employee();
		employeeSample2.setName(seniorName);
		employeeSample2.setRole(seniorRole);
		employeeSample2.setSeniority(seniorSeniority);

		Employee employeeSample3 = new Employee();
		employeeSample3.setName(managerName);
		employeeSample3.setRole(managerRole);
		employeeSample3.setSeniority(managerSeniority);

		// when
		cm.addEmployee(employeeSample);
		cm.addEmployee(employeeSample2);
		cm.addEmployee(employeeSample3);

		// then
		assertEquals(3.0, cm.getEmployeeList().size(), 0);
	}
	
}
