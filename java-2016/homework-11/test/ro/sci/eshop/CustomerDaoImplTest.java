package ro.sci.eshop;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerDaoImplTest {
	
	
	@BeforeClass
	public static void emptyTable(){
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		Connection connection = customerDaoImpl.getDbConnection();
		PreparedStatement ps = null;
		try{
			String sqlQuery = "delete from public.customer where id>?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, 0);
			ps.executeUpdate();
			System.out.println("customer Table Cleared!");
			insertingCustomerIntoCustomerTable();
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private static void insertingCustomerIntoCustomerTable(){
		int clientsToInsert = 6;
		String[] customersName = {"Fasolica Furtunel","Petrica Dospalau","Gigi Spirt","Maria Cioban","Raj Mentosan"};
		String[] emails = {"Fasolica@gmail.com","PetricaD@gmail.com","GigiAlcool@hotmail.com","MariaCioban@gmail.com","TechnologyMad@yahoo.com"};
		CustomerDao customerDao = new CustomerDaoImpl();
		for(int i=0;i<clientsToInsert-1;i++){
			customerDao.createCustomer(i+1, customersName[i], emails[i]);
		}
}
	
	
	
	@Test
	public void deleteCustomerFromCustomerTable(){
		int customerIdToDelete=5;//change to delete the desired record
		CustomerDao customerDao = new CustomerDaoImpl();
		int customersDeleted = customerDao.deleteCustomer(customerIdToDelete);
		assertEquals(1, customersDeleted);
	}
	
	@Test
	public void updateCustomerRecord(){
		int customerIdToUpdate=4;
		//the girl got married
		String modifiedName ="Maria Ciurdar";
		String modifiedEmail = "mariaciurdar@gmail.com";
		CustomerDao customerDao = new CustomerDaoImpl();
		int customersUpdated = customerDao.updateCustomer(modifiedName,modifiedEmail,customerIdToUpdate);
		assertEquals(1, customersUpdated);
	}
	
	@Test
	public void getCustoemrByIdTest(){
		int customerIdThatNeedsToBeSelected = 2;
		CustomerDao customerDao = new CustomerDaoImpl();
		int customersSelected = customerDao.getCustomerById(customerIdThatNeedsToBeSelected);
		assertEquals(1, customersSelected);
	}
	
	@AfterClass
	public static void showInfo(){
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		customerDaoImpl.getAllCustomers();
	}
}
