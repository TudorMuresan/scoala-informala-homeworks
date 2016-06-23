package ro.sci.java.homeworks.elections;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * VotesWriter class gets detais about a vote and writes it into a local .txt file in ASCII.(purpose of this 
 * was for testing and personal training only)
 * @author Tudor Muresan
 *
 */
public class VotesWriter {

	/**
	 *Constructor 
	 * When instantiating this class and writing to local file it will check if the file exists. 
	 * If exists it will delete the existing file and creates an empty one for writing in it.
	 */
	public VotesWriter(){
		checkIfFileExists();		
	}
	
	private void checkIfFileExists(){
		File myFile = null;
		try{
			myFile = new File("Votes.txt");
			if(myFile.exists()){
				myFile.delete();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes vote details into a local .txt file in ASCII.
	 * @param voteDet The vote details that will be written in the file.
	 * 
	 */
	public void writeLocalFile(String voteDet){
		FileWriter fw = null;
	    BufferedWriter bw = null;
	    PrintWriter out = null;
		try {
			fw = new FileWriter("Votes.txt", true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			byte[] bytes = voteDet.getBytes("US-ASCII");
			for(int i=0;i<bytes.length;i++){
				out.print(bytes[i]);
				out.print("~");
			}
			out.println("");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			out.close();
		}
	}
}
