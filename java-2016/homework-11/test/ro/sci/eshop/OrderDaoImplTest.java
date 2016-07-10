package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderDaoImplTest {
	
	
	@BeforeClass
	public static void emptyTable(){
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		Connection connection = orderDaoImpl.getDbConnection();
		PreparedStatement ps = null;
		try{
			String sqlQuery = "delete from public.order where id>?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, 0);
			ps.executeUpdate();
			System.out.println("order Table Cleared!");
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
	
	
	
	@Test
	public void insertingOrderIntoOrderTable(){
		int ordersToInsert = 10;
		double[] orderPrice = {30.0,30.0,32.5,17.0,3.0,10.0,15.5,14.0,20.0,40.0};
		int[] idOfCustomer = {2,2,3,1,1,2,5,2,4,2};
		
		
		OrderDao orderDao = new OrderDaoImpl();
		for(int i=0;i<ordersToInsert;i++){
			orderDao.createOrder(i+1, orderPrice[i], idOfCustomer[i]);
		}
		//-----uncomment desired method from below to check whatever your soul wants.
		deleteOrderFromOrderTable();
		updateOrderRecord();
		getOrderByIdTest();
	}
	
	

	private void deleteOrderFromOrderTable(){
		int orderIdToDelete=5;//change to delete the desired record
		OrderDao orderDao = new OrderDaoImpl();
		orderDao.deleteOrder(orderIdToDelete);
		
	}
	
	
	
	private void updateOrderRecord(){
		int orderIdToUpdate=4;
		//the product is selled/processed
		OrderDao orderDao = new OrderDaoImpl();
		orderDao.updateOrder(orderIdToUpdate);
		
	}
	
	private void getOrderByIdTest(){
		int orderIdThatNeedsToBeSelected = 2;
		OrderDao orderDao = new OrderDaoImpl();
		orderDao.getOrderById(orderIdThatNeedsToBeSelected);
	}
	
	@AfterClass
	public static void showInfo(){
		OrderDao orderDaoImpl = new OrderDaoImpl();
		orderDaoImpl.getAllOrders();
	}
}
