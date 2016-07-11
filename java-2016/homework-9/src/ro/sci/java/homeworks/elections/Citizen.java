package ro.sci.java.homeworks.elections;

/**
 * Citizen class holds information needed for him to vote a candidate for mayor.
 * 
 * @author Tudor Muresan
 *
 */
public class Citizen {

	private String citizenName;
	private String citizenID;

	/**
	 * Constructor Will have the citizen name and id number.
	 * 
	 * @param name
	 *            The name of the citizen.
	 * @param citizenID
	 *            The unique id of the citizen.
	 *
	 */
	public Citizen(String name, String citizenID) {
		citizenName = name;
		this.citizenID = citizenID;
	}

	/**
	 * Getter for the citizen name.
	 * 
	 * @return The citizen name.
	 * 
	 */
	public String getCitizenName() {
		return citizenName;
	}

	/**
	 * Getter for the citizen unique ID.
	 * 
	 * @return The citizen ID.
	 * 
	 */
	public String getCitizenID() {
		return citizenID;
	}

	// TODO CODE REVIEW: From an OOP point of view this is something that the
	// voting system would do. If you would start adding actions to the Citizen
	// class, based on what a citizen can do...the class could grow to become
	// endless.
	/**
	 * The method will decide the mayor elected by this citizen.
	 * 
	 * @param mayorCandidate
	 *            The name of the mayor candidate which the citizen tries to
	 *            vote.
	 * @return The details needed for voting(Citizen name, citizen id and the
	 *         mayor elected).
	 */
	public String voteAttempt(String mayorCandidate) {
		String voteDetails = getCitizenID() + "," + citizenName + "," + mayorCandidate;
		return voteDetails;
	}
}
