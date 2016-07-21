package ro.sci.java.homeworks.elections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

public class VotesReaderTest {

	
	@Test
	public void whenPeopleVotesAreCompleteAndTheyRespectRules(){
		//given
		StringBuilder sb = new StringBuilder();
		sb.append("1982248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("198224853,Fodor aaa,Nicolae Guta" +"\n");
		sb.append("19827753,Ilie Abc,Gheorghe Funar" +"\n");
		sb.append("19822483,Ioan Abc,Florin Salam");
		//when
		VotesManager votesReader = new VotesManager();
		votesReader.readVotes(getCandidatesList(),sb.toString());
		
		//then
		assertEquals(votesReader.getTotalVotesAttempts(), 4);
		assertEquals(votesReader.getValidVotes(), 4);
	}
	
	@Test
	public void whenSomeOneTryesToVoteTwiceBothOfHisVotesAreInvalidated(){
		//given
		StringBuilder sb = new StringBuilder();
		sb.append("1982248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("198224853,Fodor aaa,Nicolae Guta" +"\n");
		sb.append("19827753,Ilie Abc,Gheorghe Funar" +"\n");
		sb.append("19822483,Ioan Abc,Florin Salam" +"\n");
		sb.append("1790606486977,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("1790606486977,Fodor Abc,Chuck Norris");
		//when
		VotesManager votesReader = new VotesManager();
		votesReader.readVotes(getCandidatesList(),sb.toString());
		
		//then
		assertEquals(votesReader.getTotalVotesAttempts(), 6);
		assertEquals(votesReader.getValidVotes(), 4);
		assertEquals(votesReader.getInvalidVotes(), 2);
	}
	
	@Test
	public void whenSomeoneDidntVoteCorrectOrDidntVoteAtAllVoteWillBeInvalid(){
		//given
		StringBuilder sb = new StringBuilder();
		sb.append("1982248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("198224853,Fodor aaa,Nicolae Guta" +"\n");
		sb.append("19827753,Ilie Abc,Gheorghe Funar" +"\n");
		sb.append("19822483,Ioan Abc,Florin Salam" +"\n");
		sb.append("1790606977, Fodor Abc,Chuck Norris" +"\n");
		sb.append("12248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("1790606486977,Fodor Abc,");
		
		//when
		VotesManager votesReader = new VotesManager();
		votesReader.readVotes(getCandidatesList(),sb.toString());
		
		//then
		assertEquals(votesReader.getTotalVotesAttempts(), 7);
		assertEquals(votesReader.getValidVotes(), 6);
		assertEquals(votesReader.getInvalidVotes(), 1);
	}
	
	private static Map<String,Candidate> getCandidatesList(){
		Map<String,Candidate> mayorCandidates = new LinkedHashMap<>();
		Candidate mayorOne = new Candidate("Gheorghe Funar");
		Candidate mayorTwo = new Candidate("Nicolae Guta");
		Candidate mayorThree = new Candidate("Florin Salam");
		Candidate mayorFour = new Candidate("Chuck Norris");
		
		mayorCandidates.put("candidateOne", mayorOne);
		mayorCandidates.put("candidateTwo", mayorTwo);
		mayorCandidates.put("candidateThree", mayorThree);
		mayorCandidates.put("candidateFour", mayorFour);
		return mayorCandidates;
	}
}
