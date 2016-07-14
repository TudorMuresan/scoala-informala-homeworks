package ro.sci.java.homeworks.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The TicketManagement Class handles the sale operations for the Tickets from different categories.
 */
public class TicketManagement {
	
	private int availableTickets;
	private int totalClients;
	private double ticketSellingRevenue;
	private double totalTicketSellingRevenue;
	private String mainCategory;
	private int categoryOne;
	private int categoryTwo;
	private int categoryThree;
	private int servedClients;
	private Map<String,TicketSet> ticketContainer;
	private List<Client> clientsList;
	protected FinanceManager ticketsFinancialReport = new FinanceManager();
	
	
	/**
	 * Constructor
	 * The constructor will decide the total number of available tickets for the event.
	 * The total clients  value will be hardcoded with the value of total tickets plus 13;
	 * @param availableTickets Number of tickets available for selling.
	*/
	public TicketManagement(int availableTickets){
		this.availableTickets = availableTickets;
		this.totalClients = availableTickets + 13;
		generateClients(totalClients);
	}
	
	
	/**
	 * Getter for total served client.
	 * @return the number of clients that were served.
	 */
	public int getServedClients(){
		for(Client client:clientsList){
			if(client.getDesiredTickets()<1){
				servedClients++;
			}
		}
		return servedClients;
	}
	
	/**
	 * Getter for created category one tickets.
	 * @return the number of tickets from category one.
	 */
	public int getCategoryOneTickets(){
		return categoryOne;
	}
	
	/**
	 * Getter for created category two tickets.
	 * @return the number of tickets from category two.
	 */
	public int getCategoryTwoTickets(){
		return categoryTwo;
	}
	
	/**
	 * Getter for created category three tickets.
	 * @return the number of tickets from category three.
	 */
	public int getCategoryThreeTickets(){
		return categoryThree;
	}
	
	/**
	 * Getter for the number of tickets available;
	 * @return The current number of tickets that are available for selling.
	 */
	public int getAvailableTickets(){
		return availableTickets;
	}
	
	/**
	 * Getter for the number of tickets available;
	 * @return The current number of tickets that are available for selling.
	 */
	public int getTotalClients(){
		return totalClients;
	}
	
	/**
	 * Getter for the main category that will be handled at a moment of time. 
	 * @return The current category of ticket.
	 */
	public String getMainCategory(){
		return mainCategory;
	}
	
	/**
	 * Setter for the ticket category.
	 * @param cat The category that will this class will handle.
	 * 
	 */
	public void setMainCategory(String cat){
		mainCategory = cat;
	}

	/**
	 * This will decrement one ticket from the total number of tickets every time a ticket will be selled.
	 * 
	 */
	public void sellOneTicket(){
		availableTickets--;
	}
	
	/**
	 * The method will generate the number of tickets that will be available for every category. 
	 */
	public void generateTickets(){
		if(availableTickets %2 ==0){
			categoryThree = (availableTickets/2);
		}
		else{
			categoryThree = (availableTickets + 1)/2;
		}
		categoryTwo = (int) (availableTickets*0.35);
		categoryOne = availableTickets -(categoryThree + categoryTwo);
		
		createMap(categoryOne,categoryTwo,categoryThree);
	}
	
	
	/**
	 * Here, the map will be populated with the generated tickets.
	 * @param catOne Category one of tickets for the map.
	 * @param catTwo Category two of tickets for the map.
	 * @param catThree Category three of tickets for the map.
	 */
	private void createMap(int catOne, int catTwo, int catThree){
		ticketContainer = new TreeMap<>();
		
		int catOnePrice = 120;
		int catTwoPrice = 80;
		int catThreePrice = 50;
		
		ticketContainer.put("Category 1", new TicketSet(true, catOnePrice, catOne));
		ticketContainer.put("Category 2",new TicketSet(true, catTwoPrice, catTwo));
		ticketContainer.put("Category 3",new TicketSet(true, catThreePrice, catThree));
		
		categoryManager();
		
	}
	
	
	/**
	 * This method creates a list with all the clients with their queue order and their desired tickets.
	 * @param noOfClients Number of clients that wish to buy ticket/s;
	 * @return The final list with the clients.
	 */
	public List<Client> generateClients(int noOfClients){
		int revNbr =0;
		if(noOfClients>0){
			clientsList = new ArrayList<>();
		}
		while(noOfClients>0){
			revNbr ++;
			if(revNbr %17==0){
				clientsList.add(new Client(3,revNbr));
			}else{
				clientsList.add(new Client(revNbr));
			}
			noOfClients--;
		}
		return clientsList;
	}
	
	/**
	 * Here, when the method will handle the exceptions if one category of tickets will reach to 0, it will try to handle the next category of tickets.
	 * If all categories were sold, than an exception will handle that and the program will stop.
	 */
	private void categoryManager(){
		try{
			requestTickets(getMainCategory());
		}
		catch(CategoryTicketsSoldoutException e){
			totalTicketSellingRevenue += ticketSellingRevenue;
			ticketSellingRevenue =0.0;
			System.out.println("-----------------Requesting " + getMainCategory() + " tickets--------------");
			categoryManager();
		}
		catch(SoldoutException e){
			totalTicketSellingRevenue += ticketSellingRevenue;
			System.err.println("Warning !!! All the tickets were sold!!!!");
			System.out.println("Total earnings from ticket selling is " + totalTicketSellingRevenue);
		}
	}
	
	
	
	/**
	 * It will get the current category of tickets that will not be available anymore and will set the next category of tickets to be handeled.
	 *@param cat the current category that will be unavailable. 
	 *@return Returns the new category.
	 */
	public String getNextCategory(String cat){
		Integer catDecide = Integer.parseInt((cat.substring(cat.length() - 1)));
		if(catDecide>1){
			catDecide--;
		}
		return "Category " + catDecide.toString();
	}
	

	/**
	 * This method will search for clients that are waiting for buying a ticket.
	 * Sells ticket by ticket to every person.
	 * @param category The category of tickets that this method will process.
	 * @throws SoldoutException Once the tickets will be sold, an exception will be thrown.
	 * @throws CategoryTicketsSoldoutException Once the tickets from one category will be sold, an exception will be thrown.
	 */
	public void requestTickets(String category) throws CategoryTicketsSoldoutException,SoldoutException{
		for(int i=0;i<clientsList.size();i++){
			while(clientsList.get(i).getDesiredTickets()>0){
				clientsList.get(i).giveTicket();
				sellTicket(clientsList.get(i),category);
			}
		}
	}

	
	/**
	 * This method will check for availability of ticket and sell them to the client. 
	 * @param client The client that will receive the ticket.
	 * @param category The ticket category that will be sold to the client.
	 * @throws SoldoutException Once the tickets will be sold, an exception will be thrown.
	 * @throws CategoryTicketsSoldoutException Once the tickets from one category will be sold, an exception will be thrown.
	 */
	protected void sellTicket(Client client,String category) throws CategoryTicketsSoldoutException,SoldoutException {
		for (Map.Entry<String, TicketSet> entry : ticketContainer.entrySet())
		{
			if(entry.getKey().equals(category)){
				sellOneTicket();
				ticketSellingRevenue+=entry.getValue().getPrice();
				entry.getValue().sellTicket();
				System.out.println(entry.getKey()+ " ticket sold to client queued " + client.getQueueNbr() + 
						" at price of " + entry.getValue().getPrice() +
						", " + entry.getValue().getPieces() + " tickets from this category remained!!");
				
				if(getAvailableTickets()<1){
					ticketsFinancialReport.calculateCategoryRevenue(category, ticketSellingRevenue);
					System.out.println("Earnings from selling " + category + " tickets " + ticketSellingRevenue);
					throw new SoldoutException("Toate bilete au fost vandude");
				}
				if(!entry.getValue().getAvailability()){
					setMainCategory(getNextCategory(category));
					ticketsFinancialReport.calculateCategoryRevenue(category, ticketSellingRevenue);
					System.out.println("Earnings from selling " + category + " tickets " + ticketSellingRevenue);
					throw new CategoryTicketsSoldoutException("Nu mai sunt bilete la " + category);
				}
			}
		}
	}
}
