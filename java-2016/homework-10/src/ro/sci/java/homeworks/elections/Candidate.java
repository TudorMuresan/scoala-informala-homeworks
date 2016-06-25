package ro.sci.java.homeworks.elections;

/**
 * Candidate class holds information for a candidate for mayor.
 * @author Tudor Muresan
 *
 */
public class Candidate {
	
	private String candidateName;
	private int votes;
	
	/**
	 * Constructor.
	 * @param name The candidate name.
	 * 
	 */
	public Candidate(String name){
		candidateName = name;
		votes =0;
	}
	
	/**
	 * This method resets the votes for every candidate.
	 * If the voting process is still in progress and the results are partial this method will reset the votes
	 *  for re-counting the votes for the next partial results or final results.
	 */
	public void resetVotes(){
		if(PollingSection.votingStatus=="voting open"){
			this.votes =0;
		}
	}
	/**
	 * Getter for the candidate name.
	 * @return Name of the candidate.
	 * 
	 */
	public String getCandidatName(){
		return candidateName;
	}
	
	/**
	 * Increment one vote when a citizen votes for this mayor.
	 */
	public void giveVote(){
		votes++;
	}
	
	/**
	 * Getter for the total votes of this candidate.
	 * @return the number of votes for this mayor.
	 * 
	 */
	public int getTotalVotes(){
		return votes;
	}
}
