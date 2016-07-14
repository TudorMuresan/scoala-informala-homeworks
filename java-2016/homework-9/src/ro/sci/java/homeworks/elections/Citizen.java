package ro.sci.java.homeworks.elections;


/**
 * Citizen class holds information needed for him to vote a candidate for mayor.
 * @author Tudor Muresan
 *
 */
public class Citizen {
	
	private String citizenName;
	private String citizenID;
	
	/**
	 * Constructor
	 * Will have the citizen name and id number.
	 * @param name The name of the citizen.
	 * @param citizenID The unique id of the citizen.
	 *
	 */
	public Citizen(String name, String citizenID){
		citizenName = name;
		this.citizenID = citizenID;
	}
	
	/**
	 *  Getter for the citizen name.
	 *  @return The citizen name.
	 * 
	 */
	public String getCitizenName(){
		return citizenName;
	}
	
	/**
	 *  Getter for the citizen unique ID.
	 *  @return The citizen ID.
	 * 
	 */
	public String getCitizenID(){
		return citizenID;
	}
}
