package ro.sci.eshop;

import java.sql.Connection;

/**
 * AbstractModelDao class requires a connection from ConnectionManager for the classes that will extend it.
 */
public abstract class AbstractModelDao{
	private ConnectionManager cm;
	
	/**
	 * The method creates a jdbc connection.  
	 * @return Connect to a database and returns the crated connection.
	 * 
	 */
	protected Connection getDbConnection(){
		cm = new ConnectionManager();
		return cm.getConnection("postgresql", "localhost",5432,"eshop","postgres",cm.getPassword());
	}
}
