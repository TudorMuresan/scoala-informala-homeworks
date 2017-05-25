package ro.sci.java.homeworks.exceptions;
/**
 * CategoryTicketsSoldoutException class handles an exception when tickets from a specific category will be sold. 
 */
public class CategoryTicketsSoldoutException extends SoldoutException{

	private static final long serialVersionUID = 1L;
	public CategoryTicketsSoldoutException(String s){
		super(s);
	}
}
