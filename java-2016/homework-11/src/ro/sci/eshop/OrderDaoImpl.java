package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * OrderDaoImpl class handles delete/create/select database operations on the order table from eshop database.
 */
public class OrderDaoImpl extends AbstractModelDao implements OrderDao{

	/**
	 * Deletes an order by the inserted id.
	 * @param orderId The id inserted based on which the order will be deleted.
	 */
	@Override
	public void deleteOrder(int orderId) {
		Connection conn = null;
		conn = getDbConnection();
		PreparedStatement ps = null;
		try{
			String sqlQuery = "delete from public.order where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderId);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected>0){
				System.out.println("Deleted order with id :" + orderId );
			}else{
				System.out.println("Warning!!No order found with requested id!!");
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
		}
		
	}

	/**
	 * Creates a new order into the table.
	 * @param id The id for the new order.
	 * @param price The price of the order.
	 * @param idCustomer The id of customer for this order.
	 */
	@Override
	public void createOrder(int id, double price, int idCustomer) {
		Connection conn = null;
		conn = getDbConnection();
		
		PreparedStatement ps = null;
		try{
			String sqlQuery = "insert into public.order (id, value, date, confirmed, id_customer,status) values(?,?,current_date,?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ps.setDouble(2, price);
			ps.setBoolean(3, true);
			ps.setInt(4, idCustomer);
			ps.setString(5, "NEW");
			ps.executeUpdate();
			System.out.println("Inserted order worthing " + price + " into order table!");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
		}
		
		
	}

	/**
	 * Selects an order by id from the table.
	 * @param orderId Query on the order with this id.
	 */
	@Override
	public void getOrderById(int orderId) {
		Connection conn = null;
		conn = getDbConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			String sqlQuery = "select * from public.order where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			final String format = "%s%20s%30s%25s\n";
			if(rs.next()){
				System.out.format("-----------------------------------------ALL ORDERS----------------------------------\n"
			+"ID               Value                      Date                        Status\n"+format, orderId, rs.getString("value"), rs.getDate("date"),rs.getString("status"));
			}
			else{
				System.out.println("No order found with the requested id!!");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
			if(rs!=null){try{rs.close(); }catch(SQLException e){e.printStackTrace();}}
			
		}
		
	}

	/**
	 * Selects all the orders from the table. 
	 */
	@Override
	public void getAllOrders() {
		Connection conn = null;
		conn = getDbConnection();
		ResultSet rs = null;
		Statement st = null;
		try{
			String sqlQuery = "select * from public.order";
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			final String format = "%s%20s%30s%25s\n";
			System.out.format("-----------------------------------------ALL ORDERS----------------------------------\n");
			String header = "ID               Value                      Date                        Status";
			System.out.println(header);
			while(rs.next()){
				System.out.format(format, rs.getInt("id"), rs.getString("value"), rs.getDate("date"),rs.getString("status"));
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
	 * Updates order's status field. 
	 * @param orderId The id of the order which will be updated into COMPLETE after processing it.
	 */
	@Override
	public void updateOrder(int orderId) {
		Connection conn = null;
		conn = getDbConnection();
		
		PreparedStatement ps = null;
		try{
			String sqlQuery = "update public.order set status=? where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, "COMPLETE");
			ps.setInt(2, orderId);
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected>0){
				System.out.println("Order Processed and Completed!");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(conn!=null){try{conn.close();}catch(SQLException e){e.printStackTrace();} }
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();} }
		}	
	}
}
