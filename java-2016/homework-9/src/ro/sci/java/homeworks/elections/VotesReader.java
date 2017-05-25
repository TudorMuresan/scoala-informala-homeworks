package ro.sci.java.homeworks.elections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * VotesReader class reads all the votes from the input, transform the votes details into original text if the details readed are encoded in ASCII.
 * @author Tudor Muresan
 *
 */
public class VotesReader {
	
	private int invalidVotes;
	private int validVotes;
	private int totalVotesAttempts;
	
	/**
	 * Determines if the votes are in normal string o r ASCII then reads the details from them and saves the votes into a List.
	 * @param candidatesList The map that contains the candidates for mayor.
	 * @param candidatesVotes the string that needs to be processed.
	 * @return The list that will hold all the valid votes that will be processed for determine the votes percentages and the chosen candidate.
	 * 
	 */
	public List<Vote> readVotes(Map<String,Candidate> candidatesList,String candidatesVotes) {
		
		if(candidatesVotes !=null){
			BufferedReader br = new BufferedReader(new StringReader(candidatesVotes));
			try{
				return manageVotes(br,candidatesList);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
		}
		else{
			
			try(BufferedReader br = new BufferedReader(new FileReader("Votes.txt"))) {
				return manageVotes(br,candidatesList);
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
		
			
		
		
	
	private List<Vote> manageVotes(BufferedReader br, Map<String, Candidate> listaCandidati) throws NumberFormatException, IOException {
		List<Vote> votesList = new ArrayList<>();
		List<String> blackList = new ArrayList<>();
		List<String> idChecker = new ArrayList<>();
		
		int voteCount = 0;
		String line="";
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			String[] voteChunks;
			//checks if the text line contain ~ in this case it will be in ASCII.
			if(line.contains("~")){
				String[] chunks = line.split("~");
				for(int i=0;i<chunks.length;i++){
					Integer charact = Integer.parseInt(chunks[i]);
					int temp = charact;
					char c = (char)temp; 
					sb.append(c);
				}
				String temporaryVote = sb.toString();
				voteChunks = temporaryVote.split(",");
				sb.delete(0, sb.length());
			}
			else{
				voteChunks = line.split(",");
			}
			boolean candidateExists = false;
			
			validateVote(listaCandidati, votesList, blackList, idChecker, voteChunks, candidateExists);
			voteCount++;
		}
		
		totalVotesAttempts = voteCount;
		writeReadedDetails();
		return votesList;
		
	}

	/**
	 * Decides if a vote is valid or invalid. In case of a citizen votes more than once all their votes will be invalidate.
	 * If the vote is valid,  the vote will be confirmed.
	 * @param listaCandidati The list of candidates.
	 * @param votesList votes list.
	 * @param blackList A list where a citizen exists if they tried to vote more than once.
	 * @param idChecker This will be the value based on which the system will check for duplicate votes.
	 * @param voteChunks Splits a line of vote into three parts. Name, id and voted candidate name.
	 * @param candidateExists If the voted candidate exists or is correctly introduced.
	 */
	private void validateVote(Map<String, Candidate> listaCandidati, List<Vote> votesList, List<String> blackList,
			List<String> idChecker, String[] voteChunks, boolean candidateExists) {
		
		candidateExists = checkVoteFormat(listaCandidati, voteChunks, candidateExists);
		if(!candidateExists){
			System.out.println("Citizen "  + voteChunks[1] + " didnn't entered a valid candidee name! Vote invalidated!");
			this.invalidVotes++;
		}
		else if(blackList.size()>0 && blackList.contains(voteChunks[0])){
			System.out.println("Cetatean " + voteChunks[1] + " banat!! A incercat sa voteze pentru fiecare sticla de ulei primita!!");
			this.invalidVotes++;
		}
		else if(idChecker.size()>0 && idChecker.contains(voteChunks[0])){
			blackList.add(voteChunks[0]);
			this.validVotes--;
			idChecker.remove(idChecker.indexOf(voteChunks[0]));
			this.invalidVotes+=2;//when second vote for same citizen occurred the previous one will also be invalidated.
			System.out.println(voteChunks[1] + " has broken the rules and will be fined. All of his votes are invalidated.");
			
		}
		else{
			this.validVotes++;
			idChecker.add(voteChunks[0]);
			votesList.add(new Vote(voteChunks[0], voteChunks[1], voteChunks[2]));
		}
	}

	private boolean checkVoteFormat(Map<String, Candidate> listaCandidati, String[] voteChunks,
			boolean candidateExists) {
		for (Map.Entry<String, Candidate> entry : listaCandidati.entrySet())
		{
			if(voteChunks.length<3){
				System.out.println("EMPTY DETAILS FOR MAYOR");
				break;
			}
			else if(entry.getValue().getCandidatName().equals(voteChunks[2])){
				candidateExists = true;
			}
		}
		return candidateExists;
	}

	
	/**
	 * Getter for the invalid votes. 
	 * @return The number of the invalid votes.
	 * 
	 */
	public int getInvalidVotes(){
		return invalidVotes;
	}
	
	/**
	 * Getter for the number of the valid votes.
	 * @return The total number of the valid votes. 
	 * 
	 */
	public int getValidVotes(){
		return validVotes;
	}
	
	/**
	 * Getter for the number of the total attempts of the votes from which some votes can be invalidated due to rules violations.
	 * @return The number of the total votes attempts.
	 */
	public int getTotalVotesAttempts(){
		return totalVotesAttempts;
	}
	
	
	private void writeReadedDetails() {
		System.out.println("Total Nr. of votes attempts :  " + getTotalVotesAttempts());
		System.out.println("Invalid Nr. of votes:  " + getInvalidVotes());
		System.out.println("Valid Nr. of votes:  " + getValidVotes());
		
	}
}
