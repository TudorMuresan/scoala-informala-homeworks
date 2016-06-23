package ro.sci.java.homeworks.elections;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * The PollingSection class reads the citizens votes for deciding the elected mayor.
 * @author Tudor Muresan.
 *
 */
public class PollingSection {
	private Map<String,Candidate> mayorCandidates = new LinkedHashMap<>();
	private List<Vote> votesList = new ArrayList<>();
	protected VotesReader reader = new VotesReader();
	private VotesWriter writer = new VotesWriter();
	private String electionStatus = new String();
	
	/** Main method of the class. 
	 * @param args Command Line arguments.
	*/
	public static void main(String[] args){
		PollingSection pollingStation = new PollingSection();
		pollingStation.generateMayorSet();
		pollingStation.generateVotingCitizens();
	}

	/**
	 * Generates the Map that will hold the mayor candidates.
	 * 
	 */
	protected void generateMayorSet() {
		Candidate mayorOne = new Candidate("Gheorghe Funar");
		Candidate mayorTwo = new Candidate("Nicolae Guta");
		Candidate mayorThree = new Candidate("Florin Salam");
		Candidate mayorFour = new Candidate("Chuck Norris");
		
		mayorCandidates.put("candidateOne", mayorOne);
		mayorCandidates.put("candidateTwo", mayorTwo);
		mayorCandidates.put("candidateThree", mayorThree);
		mayorCandidates.put("candidateFour", mayorFour);
		System.out.println(mayorCandidates);
		
	}
	
	/**
	 * Getter for the status of the elections. 
	 * @return The status of the elections-can result in a tie between two candidates or a victory of a candidate.
	 */
	public String getElectionStatus() {
		return electionStatus;
	}
	private void generateVotingCitizens(){
		Citizen citizenOne = new Citizen("Fodor Abc", "1982248697753");
		writer.writeLocalFile(citizenOne.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenTwo = new Citizen("Pop Abc", "1790606486977");
		writer.writeLocalFile(citizenTwo.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenThree = new Citizen("Muresan Abc", "1922206439724");
		writer.writeLocalFile(citizenThree.voteAttempt(mayorCandidates.get("candidateOne").getCandidatName()));
		Citizen citizenFour = new Citizen("Vlad Abc", "1950307908653");
		writer.writeLocalFile(citizenFour.voteAttempt(mayorCandidates.get("candidateThree").getCandidatName()));
		Citizen citizenFive = new Citizen("Mihaela Abc", "2911809188255");
		writer.writeLocalFile(citizenFive.voteAttempt(mayorCandidates.get("candidateThree").getCandidatName()));
		Citizen citizenSix = new Citizen("Cristi Abc", "1992248697753");
		writer.writeLocalFile(citizenSix.voteAttempt(mayorCandidates.get("candidateFour").getCandidatName()));
		Citizen citizenSeven = new Citizen("Popescu Abc", "1882209237733");
		writer.writeLocalFile(citizenSeven.voteAttempt(mayorCandidates.get("candidateFour").getCandidatName()));
		Citizen citizenEight = new Citizen("Melinda Abc", "2842246369899");
		writer.writeLocalFile(citizenEight.voteAttempt(mayorCandidates.get("candidateThree").getCandidatName()));
		Citizen citizenNine = new Citizen("Mihai Abc", "1941208697766");
		writer.writeLocalFile(citizenNine.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenTen = new Citizen("Marius Abc", "1951101694778");
		writer.writeLocalFile(citizenTen.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenEleven = new Citizen("Adela Abc", "2991212784999");
		writer.writeLocalFile(citizenEleven.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenTwelve = new Citizen("Flaviu Abc", "1810404611811");
		writer.writeLocalFile(citizenTwelve.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenThirteen = new Citizen("Septi Abc", "1780808177900");
		writer.writeLocalFile(citizenThirteen.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenFourteen = new Citizen("Liviu Abc", "1770909692753");
		writer.writeLocalFile(citizenFourteen.voteAttempt(mayorCandidates.get("candidateTwo").getCandidatName()));
		Citizen citizenSixteen = new Citizen("Pintea Abc", "1680514697335");
		writer.writeLocalFile(citizenSixteen.voteAttempt(mayorCandidates.get("candidateFour").getCandidatName()));
		Citizen citizenSeventeen = new Citizen("Adrian Abc", "1660711692155");
		writer.writeLocalFile(citizenSeventeen.voteAttempt(mayorCandidates.get("candidateFour").getCandidatName()));
		Citizen citizenEighteen = new Citizen("Victor Abc", "1920303582284");
		writer.writeLocalFile(citizenEighteen.voteAttempt(mayorCandidates.get("candidateFour").getCandidatName()));
		Citizen citizenNineteen = new Citizen("Darius Abc", "1921401125988");
		writer.writeLocalFile(citizenNineteen.voteAttempt(mayorCandidates.get("candidateFour").getCandidatName()));
		Citizen citizenTwenty = new Citizen("Denisa Abc", "2950801877122");
		writer.writeLocalFile(citizenTwenty.voteAttempt(mayorCandidates.get("candidateOne").getCandidatName()));
		Citizen citizenTwentyOne = new Citizen("Mayor Vandam", "2950801833122");
		writer.writeLocalFile(citizenTwenty.voteAttempt(mayorCandidates.get("candidateOne").getCandidatName()));
		writer.writeLocalFile(citizenTwenty.voteAttempt(mayorCandidates.get("candidateOne").getCandidatName()));
		
		
		
		writer.writeLocalFile(citizenTwentyOne.voteAttempt("Piedone"));
		try{
			votesList = reader.readVotes(getMayorCandidates(),null);
		}
		catch(Exception e){
			
		}
		
		countVotes();
	}
	
	/**
	 * Updates the list of votes by reading the votes through the VotesReader class.
	 * @param testingCondition This parameter will be verified for unit tests.
	 * @param mayorList The map of the mayor candidates.
	 * @param sb The votes that will be processed and verified for validattion.
	 * 
	 */
	protected void setTestingVoteList(String testingCondition,Map<String,Candidate> mayorList,String sb){
		if(testingCondition.equals("testPurpose")){
			try{
				votesList = reader.readVotes(getMayorCandidates(),sb);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Getter for the list of the votes. 
	 * @return The list of votes.
	 */
	public List<Vote> getVoteList(){
		return votesList;
	}
	
	/**
	 * Getter for the map that contains the mayor candidates. 
	 * @return The mayor map that contains the candidates. 
	 * 
	 */
	public Map<String,Candidate> getMayorCandidates(){
		return mayorCandidates;
	}
	
	private BigDecimal calculatePercentage(int mayorVotes){
		BigDecimal tempCalc = new BigDecimal(mayorVotes*100);	
		BigDecimal tempDivisor = new BigDecimal(getVoteList().size());
		BigDecimal amount  = tempCalc.divide(tempDivisor, 2, RoundingMode.CEILING);
		return amount;
	}

	/**
	 * The method will count the votes to determine the percentages of every candidate and determine the winner elected by the citizens.
	 * @return The candidate that gathered the most votes.
	 * 
	 */
	public String countVotes() {
		int mostVotes = 0;
		String candidateMostVotes = "";
		int counterForSecondMayor =0;
		int secondMostVotes = 0;//this will hold the number of the votes for the second candidate by number of votes.
		String secondCandidate = "";//this will hold the name second candidate by number of votes.
		
		for (Map.Entry<String, Candidate> entry : getMayorCandidates().entrySet())
		{
			for(int i=0;i<votesList.size();i++){
				if(entry.getValue().getCandidatName().equals(votesList.get(i).getCandidate())){
					
					entry.getValue().giveVote();
				}
			}
			if(counterForSecondMayor<mayorCandidates.size()){
				secondMostVotes = entry.getValue().getTotalVotes();
				secondCandidate = mayorCandidates.get(entry.getKey()).getCandidatName();
			}
			if(entry.getValue().getTotalVotes() >mostVotes){
				mostVotes = entry.getValue().getTotalVotes();
				candidateMostVotes = mayorCandidates.get(entry.getKey()).getCandidatName();
			}
			counterForSecondMayor++;
			System.out.println("Mayor " + mayorCandidates.get(entry.getKey()).getCandidatName() + " obtained " + entry.getValue().getTotalVotes() + " votes!");
			System.out.println("Mayor " + mayorCandidates.get(entry.getKey()).getCandidatName() +  " obtained percentage " + calculatePercentage(entry.getValue().getTotalVotes())+ "%");			
		}
		if(secondMostVotes == mostVotes){
			System.out.println("The elections resulted with a tie between " + candidateMostVotes + " and " + secondCandidate + " new elections will be organised!!!" );
			setFinalSituation("TieBetweenFirstTwo");
		}
		else{
			setFinalSituation("CandidateElected");
			System.out.println("Elected mayor: " + candidateMostVotes + (candidateMostVotes.equals("Nicolae Guta") ? " --> Insert manele into bus stations" : ""));
		}
		return candidateMostVotes;
	}

	/**
	 * Determines the status of the elections.
	 * Can result in a tie between two candidates of a victory gained by one candidate.
	 * @param string The result of the elections.
	 */
	private void setFinalSituation(String string) {
		electionStatus  = string;
		
	}
}
