package ro.sci.java.homeworks.elections;

/**
 * The Vote class holds all the informations needed for a vote to be valid.
 * @author Tudor Muresan
 *
 */
public class Vote {
	
	private String id;
	private String name;
	private String candidate;
	
	
	/**
	 * Constructor.
	 * Will hold information about the citizen that voted for a specific mayor candidate.
	 * 
	 */
	public Vote(String id, String name, String candidate){
		this.id = id;
		this.name = name;
		this.candidate = candidate;
	}

	/**
	 * Getter for the id of the citizen  
	 * @return The id of the citizen.
	 * 
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Getter for the name of the citizen  
	 * @return The name of the citizen.
	 * 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for the voted candidate name. 
	 * @return The name of the candidate voted.
	 * 
	 */
	public String getCandidate() {
		return candidate;
	}
}
