package ro.sci.java.homeworks.elections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class PollingSectionTest {

	@Test
	public void allValidVotesAreCountedAndTheWinnerIsDeclared(){
		//given
		PollingSection pollingSection = new PollingSection();
		StringBuilder sb = new StringBuilder();
		sb.append("1982248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("198224853,Fodor aaa,Nicolae Guta" +"\n");
		sb.append("19827753,Ilie Abc,Gheorghe Funar" +"\n");
		sb.append("19822483,Ioan Abc,Florin Salam" +"\n");
		sb.append("1790606977, Fodor Abc,Chuck Norris" +"\n");
		sb.append("12248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("182248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("1982248697753,Fodor Abc,Chuck Norris" +"\n");
		sb.append("1982248693,Fodor Abc,Florin Salam" +"\n");
		sb.append("19822487753,Fodor Abc,Chuck Norris" +"\n");
		sb.append("198223,Fodor Abc,Chuck Norris" +"\n");
		sb.append("1790606486977,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("1790606486977,Fodor Abc,Nicolae Guta");
		//when
		pollingSection.generateMayorSet();
		pollingSection.setTestingVoteList("testPurpose",pollingSection.getMayorCandidates(),sb.toString());
		String candidateName = pollingSection.countVotes();
		
		//then
		assertEquals(13, pollingSection.reader.getTotalVotesAttempts());
		assertEquals(9, pollingSection.reader.getValidVotes());
		assertEquals(4, pollingSection.reader.getInvalidVotes());
		assertEquals("Nicolae Guta",candidateName);
	}
	
	@Test
	public void whenTwoCandidatesGetTheSameNumberOfVotesTieIsDecided(){
		PollingSection pollingSectionTie = new PollingSection();
		StringBuilder sb = new StringBuilder();
		sb.append("1982248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("198224853,Fodor aaa,Chuck Norris" +"\n");
		sb.append("19827753,Ilie Abc,Gheorghe Funar" +"\n");
		sb.append("19822483,Ioan Abc,Florin Salam" +"\n");
		sb.append("1790606977, Fodor Abc,Chuck Norris" +"\n");
		sb.append("12248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("182248697753,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("1982248697753,Fodor Abc,Chuck Norris" +"\n");
		sb.append("1982248693,Fodor Abc,Florin Salam" +"\n");
		sb.append("19822487753,Fodor Abc,Chuck Norris" +"\n");
		sb.append("198223,Fodor Abc,Chuck Norris" +"\n");
		sb.append("1790606486977,Fodor Abc,Nicolae Guta" +"\n");
		sb.append("1790606486977,Fodor Abc,Nicolae Guta");
		
		
		//when
		pollingSectionTie.generateMayorSet();
		pollingSectionTie.setTestingVoteList("testPurpose",pollingSectionTie.getMayorCandidates(),sb.toString());
		pollingSectionTie.countVotes();
		
		//then
		assertEquals("TieBetweenFirstTwo", pollingSectionTie.getElectionStatus());
	}
	
}
