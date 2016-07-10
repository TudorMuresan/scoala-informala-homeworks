package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductDaoImplTest {
	
	
	@BeforeClass
	public static void emptyTable(){
		ProductDaoImpl productDaoImpl = new ProductDaoImpl();
		Connection connection = productDaoImpl.getDbConnection();
		PreparedStatement ps = null;
		try{
			String sqlQuery = "delete from public.product where id>?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, 0);
			ps.executeUpdate();
			System.out.println("product Table Cleared!");
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
	public void insertingProductIntoProductTable(){
		int orderItemsToInsert = 10;
		String[] names = {"Spirt","Carnat","Paste","Sapun","Pedale","Caramele","Maioneza","Iaurt","Oua","Crema"};
		String[] description = {"Spirt Mona 250ml","Carnat Jandarm Cristim","Paste Baneasa 500g","Sapun Duru 150g","Pedale Pegas cu leduri","Caramele cu gust de sarmale","Maioneza Simpla 250 g","Iaurt Napolact 500ml","Oua Mari Caldute","Crema de smantana250g"};
		
		ProductDao productDao = new ProductDaoImpl();
		for(int i=0;i<orderItemsToInsert;i++){
			productDao.createProduct(i+1, names[i],description[i]);
		}
		//-----uncomment desired method from below to check whatever your soul wants.
		deleteProductByIdFromProductTable();
		getProductById();
	}
	
	

	private void deleteProductByIdFromProductTable(){
		int productIdToDelete=2;//change to delete the desired record
		ProductDao productDao = new ProductDaoImpl();
		productDao.deleteProduct(productIdToDelete);
		
	}

	
	private void getProductById(){
		int productIdThatNeedsToBeSelected = 4;
		ProductDao productDao = new ProductDaoImpl();
		productDao.getProductById(productIdThatNeedsToBeSelected);
	}
	
	@AfterClass
	public static void showInfo(){
		ProductDao productDao = new ProductDaoImpl();
		productDao.getAllProducts();
	}
}
