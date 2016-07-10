package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * OrderItemDaoImpl class handles delete/create/select database operations on the orderitem table from eshop database.
 */
public class OrderItemDaoImpl extends AbstractModelDao implements OrderItemDao{
		
	/**
	 * Deletes an order item by the inserted id.
	 * @param orderItemId The id inserted based on which the order item will be deleted.
	 */
	@Override
	public void deleteOrderItem(int orderItemId) {
		Connection conn = null;
		conn = getDbConnection();
		PreparedStatement ps = null;
		try{
			String sqlQuery = "delete from public.orderitem where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderItemId);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected>0){
				System.out.println("Deleted orderitem with id :" + orderItemId);
			}else{
				System.out.println("Warning!!No orderitem found with requested id!!");
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
	 * Creates an order item into the table.
	 * @param id The id for the new order item.
	 * @param orderId The order id for this order item.
	 * @param quantity The quantity of items.
	 * @param idProduct The id of product for this order.
	 */
	@Override
	public void createOrderItem(int id, int orderId, int quantity, int idProduct) {
		Connection conn = null;
		conn = getDbConnection();
		
		PreparedStatement ps = null;
		try{
			String sqlQuery = "insert into public.orderitem (id, id_order, quantity, id_product) values(?,?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ps.setInt(2, orderId);
			ps.setInt(3, quantity);
			ps.setInt(4, idProduct);
			ps.executeUpdate();
			System.out.println("Inserted item into orderitem table!");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
		}
		
		
	}

	/**
	 * Selects an order item by id from the table.
	 * @param orderItemId Query on the order item with this id.
	 */
	@Override
	public void getOrderItemsById(int orderItemId) {
		Connection conn = null;
		conn = getDbConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			String sqlQuery = "select * from public.orderitem where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, orderItemId);
			rs = ps.executeQuery();
			final String format = "%s%20s%30s\n";
			if(rs.next()){
				System.out.format("-------------------ALL ITEMS------------------------\n"+
			"ID               Id_order                      quantity\n" + format, orderItemId, rs.getInt("id_order"),rs.getInt("quantity"));
			}
			else{
				System.out.println("No order item found with the requested id!!");
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
	 * Selects all the order items from the table. 
	 */
	@Override
	public void getAllOrderItems() {
		Connection conn = null;
		conn = getDbConnection();
		ResultSet rs = null;
		Statement st = null;
		try{
			String sqlQuery = "select * from public.orderitem";
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			final String format = "%s%20s%30s\n";
			System.out.format("----------------------ALL ORDERS-------------------\n");
			String header = "ID               Id_order                      quantity";
			System.out.println(header);
			while(rs.next()){
				System.out.format(format, rs.getInt("id"), rs.getInt("id_order"),rs.getInt("quantity"));
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
}
