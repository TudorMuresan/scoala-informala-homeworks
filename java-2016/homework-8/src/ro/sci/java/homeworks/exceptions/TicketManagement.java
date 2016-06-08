package ro.sci.java.homeworks.exceptions;

import java.util.Map;
import java.util.TreeMap;
/**
 * The TicketManagement Class handles the sale operations for the Tickets from different categories.
 */
public class TicketManagement {
	
	private int tempClients;
	private int availableTickets;
	private String mainCategory;
	private int currentAwaitingClient =1;
	private int totalClients;
	private int clientsQueue=1;
	
	private int categoryOne;
	private int categoryTwo;
	private int categoryThree;
	
	private double category1;
	private double category2;
	private double category3;
	private double generalBalance;
	
	private Map<String,TicketSet> ticketContainer;
	
	/**
	 * Setter for the category of ticket.
	 * Needed for the Map from the class to handle the sale the tickets by it's category. 
	 * @param cat The category of tickets.
	 */
	public void setMainCategory(String cat){
		mainCategory = cat;
	}
	
	/**
	 * Setter for the clients that will request tickets.
	 * @param totClients Number of clients requesting for tickets.
	 */
	private void setTotalClients(int totClients){
		totalClients = totClients;
	}
	/**
	 * Setter for the 17'th client that will ask a number of tickets setted here.
	 * @param tmpClients 17'th client requirements.
	 */
	public void setTempClients(int tmpClients){
		tempClients = tmpClients;
	}
	
	/**
	 * Setter for the number of total tickets available.
	 * @param availableTickets Number of tickets created.
	 */
	public void setAvailableeTickets(int availableTickets){
		this.availableTickets = availableTickets;
		setTotalClients(this.availableTickets + 13);
	}
	
	/**
	 * Getter for the number of total tickets available.
	 * @return the number of the available tickets.
	 */
	public int getAvailableTickets(){
		return availableTickets;
	}
	
	/**
	 * Getter for the number of the remaining clients that will need tickets.
	 * @return number of clients awaiting for ticket.
	 */
	public int getRemainingClients() {
		return currentAwaitingClient;
	}
	/**
	 * Getter for the number of total clients available.
	 * @return the number of the total clients.
	 */
	public int getTotalClients(){
		return totalClients;
	}
	
	/**
	 * Getter for the category of tickets being handled at some point.
	 * @return the category of tickets.
	 */
	public String getMainCategory(){
		return mainCategory;
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
	 * Getter for category one tickets balance.
	 * @return the sales earned from tickets from category one.
	 */
	public double getCategoryOneBalance() {
		return category1;
	}
	
	/**
	 * Getter for category two tickets balance.
	 * @return the sales earned from tickets from category two.
	 */
	public double getCategoryTwoBalance() {
		return category2;
	}
	
	/**
	 * Getter for category three tickets balance.
	 * @return the sales earned from tickets from category three.
	 */
	public double getCategoryThreeBalance() {
		return category3;
	}
	
	/**
	 * Getter for all the sales earned from all tickets.
	 * @return the earnings from selling all the tickets.
	 */
	public double getGeneralBalance() {
		return generalBalance;
	}
	
	
	/**
	 * Here, all the tickets will be created.
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
	 * @param catThree Category othreee of tickets for the map.
	 */
	private void createMap(int catOne, int catTwo, int catThree){
		ticketContainer = new TreeMap<>();
		
		int catOnePrice = 120;
		int catTwoPrice = 80;
		int catThreePrice = 50;
		
		ticketContainer.put("Categoria 1", new TicketSet(true, catOnePrice, catOne));
		ticketContainer.put("Categoria 2",new TicketSet(true, catTwoPrice, catTwo));
		ticketContainer.put("Categoria 3",new TicketSet(true, catThreePrice, catThree));
		categoryManager(3);
		
	}
	
	/**
	 * Here, when the method will handle the exceptions if one category of tickets will reach to 0, it will try to handle the next category of tickets.
	 * If all categories were sold, than an exception will handle that and the program will stop.
	 * @param tempClients Needed if the 17'th client got only one or two tickets to get more from the next category.
	 */
	private void categoryManager(int tempClients){
		try{
			requestTickets(getMainCategory());
		}
		catch(CategoryTicketsSoldoutException e){
			System.out.println("-----------------Requesting " + getMainCategory() + " tickets--------------");
			categoryManager(tempClients);
		}
		catch(SoldoutException e){
			category1 = generalBalance;
			generalBalance += getCategoryTwoBalance() + getCategoryThreeBalance();
			System.out.println("Venue from the category 1 tickets sales is :" + category1);
			System.out.println("Venue from all the tickets sales is :" + generalBalance);
			System.out.println("Warning !!! Tickets Sold Out!!!!");
		}
	}
	
	/**
	 * The balance for each category of tickets will be handled here.
	 * @param value The current category that was sold.
	 */
	private void manageBalance(Integer value){
		switch(value){
			case 3:
				category3 = generalBalance;
				System.out.println("Venue from the category 3 tickets sales is :" + category3);
				generalBalance = 0;
				break;
			case 2:
				category2 = generalBalance;
				System.out.println("Venue from the category 2 tickets sales is :" + category2);
				generalBalance = 0;
				break;
			case 1:
				category1 = generalBalance;
				System.out.println("Venue from the category 1 tickets sales is :" + category1);
				generalBalance = 0;
				break;
				
		}
	}
	
	/**
	 * It wil get the current category of tickets that will not be available anymore and will set the next category of tickets to be handeled.
	 *@param cat the current category that will be unavailable. 
	 *@return Returns the new category.
	 */
	public String getNextCategory(String cat){
		Integer catDecide = Integer.parseInt((cat.substring(cat.length() - 1)));
		manageBalance(catDecide);
		if(catDecide>1){
			catDecide--;
		}
		return "Categoria " + catDecide.toString();
	}
	

	/**
	 * Here is where the tickets will be processed, and sold.
	 * Sells ticket by ticket to every person, at every 17'th person 3 tickets will be sold to that person.
	 * Gets called for every category of tickets. 
	 * @param category The category that this method will process.
	 * @throws SoldoutException Once the tickets will be sold, an exception will be thrown.
	 * @throws CategoryTicketsSoldoutException Once the tickets from one category will be sold, an exception will be thrown.
	 */
	public void requestTickets(String category) throws SoldoutException,CategoryTicketsSoldoutException{
		for (Map.Entry<String, TicketSet> entry : ticketContainer.entrySet())
		{
			if(entry.getKey().equals(category) && entry.getValue().getAvailability())
			{
				for(int i=currentAwaitingClient;i<=totalClients;i++){
					if(entry.getValue().getPieces()>0){
						if(i%17==0 || tempClients ==1 || tempClients ==2){
							Integer tmp = tempClients;
							while(tempClients>0){								
								if(entry.getValue().getPieces()<=0){
									tmp = tmp -tempClients;
									System.out.println(tmp.toString() + " tickets from "+ category + " were sold to the " + clientsQueue +"'th client " + 
									"at price of " + ((entry.getValue().getPrice()) * tmp) +  ", "+entry.getValue().getPieces() + 
									" tickets from this category remained!!");
									if(getAvailableTickets()==0){
										throw new SoldoutException("Toate bilete au fost vandude");
									}
									generalBalance += ((entry.getValue().getPrice()) * tmp);
									setMainCategory(getNextCategory(category));
									throw new CategoryTicketsSoldoutException("Nu mai sunt bilete la " + category);
								}
						
								entry.getValue().sellTicket();
								generalBalance += entry.getValue().getPrice();
								availableTickets--;
								tempClients--;
							}
							currentAwaitingClient++;
							clientsQueue += 1;
							tempClients = 3;
							
							System.out.println(tmp + " tickets from "+ category + " were sold to the " + (clientsQueue-1) + "'th client " +
							"at price of " + (entry.getValue().getPrice() * tmp) +  ", "+entry.getValue().getPieces() +
							" tickets from this category remained!!");
							continue;
						}
						else{
							entry.getValue().sellTicket();
							generalBalance += entry.getValue().getPrice();
							currentAwaitingClient++;
							availableTickets--;
						}
						
						
						System.out.println(category + " ticket sold to client queued " + clientsQueue + "at price of " + entry.getValue().getPrice() +
								", " + entry.getValue().getPieces() + " tickets from this category remained!!");
						clientsQueue += 1;
						if(getAvailableTickets()==0){
							throw new SoldoutException("Toate bilete au fost vandude");
						}
						
					}
					else{						
						setMainCategory(getNextCategory(category));
						throw new CategoryTicketsSoldoutException("Nu mai sunt bilete la " + category);
					}
				}
			}
		}
	}
}
