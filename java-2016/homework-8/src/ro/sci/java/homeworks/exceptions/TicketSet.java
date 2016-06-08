package ro.sci.java.homeworks.exceptions;
/**
 * The TicketSet Class represents the ticket class that holds information about it's availability, 
 * price and a number that will inform the number of the tickets from a specific category.
 */
public class TicketSet {
	private double price;
	private int pieces;
	private boolean availablility;
	/**
	 * Constructor
	 * @param availability the ticket will be available when created, 
	 * it will change it's availability once the category that belongs to will be selled.
	 * @param price The price for a ticket.
	 * @param pcs The number of the tickets.
	 */
	public TicketSet(boolean availability, double price, int pcs){
		if(price >0){
			this.price = price;
		}
		else{
			System.err.println("Ticket must have the price a positive value!!");
		}
		this.availablility = availability;
		this.pieces = pcs;
		this.price = price;
	}
	
	/**
	 * Getter for the ticket's price.
	 * @return The price of the created ticket. 
	 */
	public double getPrice(){
		return price;
	}
	
	/**
	 * Getter for the ticket's availability.
	 * @return The availability of the created ticket. 
	 */
	public boolean getAvailability(){
		return availablility;
	}
	
	/**
	 * Getter for the ticket instance pieces.
	 * @return The pieces of tickets from this instance. 
	 */
	public int getPieces(){
		return pieces;
	}
	
	/**
	 * When selling a ticket, the pieces of tickets from this instance will be decremented by 1.
	 * When there will be no more tickets for sale, the instance will set it's availability to false;
	 */
	public void sellTicket(){
		pieces--;
		if(pieces==0){
			availablility = false;
		}
	}
}
