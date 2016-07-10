package ro.sci.eshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connection Manager creates a connection to a database after loading the driver.
 */

public class ConnectionManager {

	/**
	 * Gets an original password to complete the connection credentials.
	 * @return The password for the database user.
	 */
	public String getPassword(){
		return "gogosar";
	}
	
	/**
	 * Constructor 
	 * Loads the driver into the memory for crating the connection.
	 */
	public ConnectionManager(){
		loadDriver();
	}
	/**
	 * Creates a connection using an url.
	 * @param type The driver type.
	 * @param host The host name.
	 * @param port The port no.
	 * @param dbName Database name.
	 * @param user User name.
	 * @param pw The password.
	 * @return The new created connection. 
	 */
	public Connection getConnection(String type, String host, int port, String dbName, String user, String pw){
		Connection connection = null;
		DriverManager.setLoginTimeout(60);
		try{
			String url = new StringBuilder().append("jdbc:").append(type) 
					.append("://").append(host).append(":").append(port).append("/").append(dbName).append("?user=")
					.append(user).append("&password=").append(pw).toString();
					
			//System.out.println("URL:" + url);
			connection = DriverManager.getConnection(url);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
	
	private void loadDriver() {
		try{
			Class.forName("org.postgresql.Driver").newInstance();
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
}
