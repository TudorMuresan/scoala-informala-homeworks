package ro.sci.java.homeworks.company;

/**
* The IncorrectInputException class defines some exceptions in the company management project.
* @author Tudor Muresan
*/
public class IncorrectInputException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	  * Constructor.
	  * @param s Prints the parameter message when throwed,to the console.
	  */
	public IncorrectInputException(String s){
		super(s);
	}

}
