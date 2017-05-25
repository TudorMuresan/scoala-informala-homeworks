package ro.sci.java.homeworks.exceptions;
/**
 * Client class will define the state of a particular Client.
 */
public class Client {
	private int desiredTickets;
	private int queueNbr;
	
	
	/**
	 * Constructor will decide the client order.
	 * @param queueNbr Order in the waiting queue.
	 */
	public Client(int queueNbr){
		this.desiredTickets = 1;
		this.queueNbr = queueNbr;
	}
	
	/**
	 * Overloaded Constructor - When the client needs more than one ticket.
	 * The constructor will define the number of required tickets and the queue of the client in the buying process.
	 * @param desiredTickets Number of desired tickets.
	 * @param queueNbr Order in the waiting queue.
	 */
	public Client(int desiredTickets,int queueNbr){
		this.desiredTickets = desiredTickets;
		this.queueNbr = queueNbr;
	}
	
	/**
	 * Getter for the number of the desired tickets. 
	 * @return Number of required tickets.
	 */
	public int getDesiredTickets() {
		return desiredTickets;
	}

	/**
	 * Getter for the queue order of the client .
	 * @return Order in the waiting queue.
	 */
	public int getQueueNbr(){
		return queueNbr;
	}
	
	/**
	 * This method will decrement one ticket from the total of tickets requested by this client.
	 */
	public void giveTicket(){
		desiredTickets--;
	}
}
