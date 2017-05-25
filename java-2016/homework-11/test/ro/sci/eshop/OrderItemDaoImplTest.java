package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderItemDaoImplTest {
	
	
	@BeforeClass
	public static void emptyTable(){
		OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();
		Connection connection = orderItemDaoImpl.getDbConnection();
		PreparedStatement ps = null;
		try{
			String sqlQuery = "delete from public.orderitem where id>?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, 0);
			ps.executeUpdate();
			System.out.println("orderitem Table Cleared!");
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
	public void insertingOrderItemIntoCustomerTable(){
		int orderItemsToInsert = 10;
		int[] orderId = {1,3,3,2,3,4,2,2,1,5};
		int[] quantity = {2,2,3,1,1,2,5,2,4,2};
		int[] productId = {2,2,3,1,1,2,5,2,4,2};
		
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		for(int i=0;i<orderItemsToInsert;i++){
			orderItemDao.createOrderItem(i+1, orderId[i], quantity[i],productId[i]);
		}
		//-----uncomment desired method from below to check whatever your soul wants.
		deleteOrderItemFromOrderTable();
		getOrderItemByIdTest();
	}
	
	

	private void deleteOrderItemFromOrderTable(){
		int orderItemIdToDelete=5;//change to delete the desired record
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		orderItemDao.deleteOrderItem(orderItemIdToDelete);
		
	}

	
	private void getOrderItemByIdTest(){
		int orderItemIdThatNeedsToBeSelected = 2;
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		orderItemDao.getOrderItemsById(orderItemIdThatNeedsToBeSelected);
	}
	
	@AfterClass
	public static void showInfo(){
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		orderItemDao.getAllOrderItems();
	}
}
