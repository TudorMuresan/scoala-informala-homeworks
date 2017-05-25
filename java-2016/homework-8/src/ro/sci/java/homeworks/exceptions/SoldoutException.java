package ro.sci.java.homeworks.exceptions;
/**
 * SoldouException class handles an exception when all the tickets wil be sold. 
 */
public class SoldoutException extends Exception{
	private static final long serialVersionUID = 1L;
	public SoldoutException(String s){
		super(s);
	}

}
