package ro.sci.eshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ProductDaoimpl class handles delete/create/select database operations on the product table from eshop database.
 */
public class ProductDaoImpl extends AbstractModelDao implements ProductDao{
		
	/**
	 * Deletes an product by the inserted id.
	 * @param productId The id inserted based on which the product will be deleted.
	 */
	@Override
	public void deleteProduct(int productId) {
		Connection conn = null;
		conn = getDbConnection();
		PreparedStatement ps = null;
		try{
			String sqlQuery = "delete from public.product where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, productId);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected>0){
				System.out.println("Deleted product with id :" + productId);
			}else{
				System.out.println("Warning!!No product found with requested id!!");
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
	 * Creates a new product into the table.
	 * @param id The id for the new product.
	 * @param name The name of the product.
	 * @param description The description of the product.
	 */
	@Override
	public void createProduct(int id, String name, String description) {
		Connection conn = null;
		conn = getDbConnection();
		
		PreparedStatement ps = null;
		try{
			String sqlQuery = "insert into public.product (id, name, description) values(?,?,?)";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, description);
			ps.executeUpdate();
			System.out.println("Inserted product into product table!");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null){try{conn.close(); }catch(SQLException e){e.printStackTrace();}}
			if(ps!=null){try{ps.close(); }catch(SQLException e){e.printStackTrace();}}
		}
		
		
	}

	/**
	 * Selects an product by id from the table.
	 * @param productId Query on the product with this id.
	 */
	@Override
	public void getProductById(int productId) {
		Connection conn = null;
		conn = getDbConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			String sqlQuery = "select * from public.product where id=?";
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			final String format = "%s%20s%30s\n";
			if(rs.next()){
				System.out.format("-------------------ALL Products------------------------\n"+
			"ID               Name               Description\n" + format, productId, rs.getString("name"),rs.getString("description"));
			}
			else{
				System.out.println("No product found with the requested id!!");
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
	 * Selects all the produtcts from the table. 
	 */
	@Override
	public void getAllProducts() {
		Connection conn = null;
		conn = getDbConnection();
		ResultSet rs = null;
		Statement st = null;
		try{
			String sqlQuery = "select * from public.product";
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			final String format = "%s%20s%30s\n";
			System.out.format("----------------------ALL Products-------------------\n");
			String header = "ID               Name             Description";
			System.out.println(header);
			while(rs.next()){
				System.out.format(format, rs.getInt("id"), rs.getString("name"),rs.getString("description"));
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
