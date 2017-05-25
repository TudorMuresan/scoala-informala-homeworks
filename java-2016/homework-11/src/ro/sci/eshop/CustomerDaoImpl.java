package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * CustomerDaoImpl class handles delete/create/select database operations on the customer table from eshop database.
 */
public class CustomerDaoImpl extends AbstractModelDao implements CustomerDao{

	
	/**
	 * Deletes a customers by the inserted id.
	 * @param customerId The id inserted based on which the customer will be deleted.
	 * @return Return one if the customer was deleted successfully.
	 */
	@Override
	public int deleteCustomer(int customerId) {
		Connection conn = null;
		conn = getDbConnection();
		PreparedStatement ps = null;
		int rowsAffected=0;
		try{
			String sqlQuery = "delete from public.customer where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, customerId);
			rowsAffected = ps.executeUpdate();
			if(rowsAffected>0){
				System.out.println("Deleted customer with id :" + customerId );
			}else{
				System.out.println("Warning!!No dude found with requested id!!");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
		}
		return rowsAffected;
	}
	
	/**
	 * Creates a customer into the table.
	 * @param id The id for the new customer.
	 * @param name The name of the customer.
	 * @param email The email of the customer.
	 */
	@Override
	public void createCustomer(int id, String name, String email) {

		Connection conn = null;
		conn = getDbConnection();
		
		PreparedStatement ps = null;
		try{
			String sqlQuery = "insert into public.customer (id, name, email) values(?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.executeUpdate();
			System.out.println("Inserted " + name + " into customer table!");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
		}
		
	}

	/**
	 * Selects a customer by id from the table.
	 * @param customerId Query on the customer with this id.
	 * @return Returns value 1 if there is a customer with the required id.
	 */
	@Override
	public int getCustomerById(int customerId) {
		
		Connection conn = null;
		conn = getDbConnection();
		int customersSelected=0;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			String sqlQuery = "select * from public.customer where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			
			final String format = "%s%20s%30s\n";
			if(rs.next()){
				customersSelected++;
				System.out.format("---------------CUSTOMER BY ID--------------------\n"
			+"id         name                         email\n"+format, customerId, rs.getString("name"), rs.getString("email"));
			}
			else{
				System.out.println("No customer found with the requested id!!");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
			if(rs!=null){try{rs.close(); }catch(SQLException e){e.printStackTrace();}}
			
		}
		return customersSelected;
	}

	/**
	 * Selects all the customers from the table. 
	 */
	@Override
	public void getAllCustomers() {
		Connection conn = null;
		conn = getDbConnection();
		ResultSet rs = null;
		Statement st = null;
		try{
			String sqlQuery = "select * from public.customer";
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			final String format = "%s%20s%30s\n";
			String header = "id         name                          email";
			System.out.format("---------------ALL CUSTOMERS--------------------\n");
			System.out.println(header);
			while(rs.next()){
				System.out.format(format, rs.getInt("id"), rs.getString("name"), rs.getString("email"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(st!=null){try{st.close(); }catch(SQLException e){e.printStackTrace();}}
			if(rs!=null){try{rs.close(); }catch(SQLException e){e.printStackTrace();}}
		}
		
	}

	/**
	 * Updates customer's field values. 
	 * @param name The name of the customer which will be updated.
	 * @param email The email of the customer that will be changed if needed.
	 * @param customerId Customer id that will decide which customer will be handled for updating.
	 * @return Return the value 1 if the customer is being update successfully.
	 */
	@Override
	public int updateCustomer(String name, String email, int customerId) {
		Connection conn = null;
		conn = getDbConnection();
		int rowsAffected =0;
		PreparedStatement ps = null;
		try{
			String sqlQuery = "update public.customer set name=?,email=? where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, customerId);
			rowsAffected = ps.executeUpdate();
			//hardcoded implemented especially for Maria
			if(rowsAffected>0){
				System.out.println("Maria got married!!Changed her name and began to drink!");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(conn!=null){try{conn.close();}catch(SQLException e){e.printStackTrace();} }
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();} }
		}
		return rowsAffected;
	}
	
}
