package ro.sci.java.homeworks.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;



public class TicketManagementTest {
	@Test
	public void createTickets(){
		TicketManagement tm = new TicketManagement();
		tm.setAvailableeTickets(150);
		tm.setMainCategory("Categoria 3");
		tm.setTempClients(3);
		tm.generateTickets();
		
	}
	
	@Test
	public void whenDecidingTotalTicketsRequestngClientsNumberIsCorrect(){
		//given
		int ticketsCreated = 150;
		TicketManagement tm = new TicketManagement();
		
		//wwhen
		tm.setAvailableeTickets(ticketsCreated);
		//then
		assertEquals(ticketsCreated + 13, tm.getTotalClients());
	}
	
	@Test
	public void createTicketsByCategoryCorrectly(){
		//given
		int ticketsCreated = 150;
		TicketManagement tm = new TicketManagement();
		
		//when
		tm.setAvailableeTickets(ticketsCreated);
		tm.generateTickets();
		int createdCategoryThreeTickets = ticketsCreated * 50/100;
		int createdCategoryTwoTickets = ticketsCreated * 35/100;
		int createdCategoryOneTickets = ticketsCreated -(createdCategoryThreeTickets + createdCategoryTwoTickets);
		
		//then
		assertEquals(createdCategoryThreeTickets, tm.getCategoryThreeTickets());
		assertEquals(createdCategoryTwoTickets, tm.getCategoryTwoTickets());
		assertEquals(createdCategoryOneTickets, tm.getCategoryOneTickets());
	}
	
	@Test
	public void whenCreatingTicketsSellingAllTicketsAndPrintTheRevenues(){
		//given
		int ticketsCreated = 100;
		TicketManagement tm = new TicketManagement();
		
		//when
		tm.setAvailableeTickets(ticketsCreated);
		tm.setMainCategory("Categoria 3");
		tm.setTempClients(3);
		tm.generateTickets();
		
		int revenuesCategoryThree = (ticketsCreated *50/100) *50;
		int revenuesCategoryTwo = (ticketsCreated *35/100) *80;
		int revenuesCategoryOne = (ticketsCreated -((ticketsCreated *50/100) +(ticketsCreated *35/100))) * 120;
		
		//then
		assertEquals(revenuesCategoryOne, tm.getCategoryOneBalance(),0);
		assertEquals(revenuesCategoryTwo, tm.getCategoryTwoBalance(),0);
		assertEquals(revenuesCategoryThree, tm.getCategoryThreeBalance(),0);
	}
	
	@Test
	public void every17clientgetsThreeTicketsIfAvailable(){
		//given
		int ticketsCreated = 19;
		TicketManagement tm = new TicketManagement();
		int availableClients = ticketsCreated + 13;
		
		//when
		availableClients-= 14;
		tm.setAvailableeTickets(ticketsCreated);
		tm.setMainCategory("Categoria 3");
		tm.setTempClients(3);
		tm.generateTickets();
		
		
		
		//then
		System.out.println(tm.getRemainingClients());
		assertEquals(availableClients, tm.getRemainingClients(),0);
	}
	
	@Test(expected = SoldoutException.class)
	public void afterSellingAllTicketsFromAllCategoryesThrowsException()throws SoldoutException{
		//given
		int ticketsCreated = 100;
		TicketManagement tm = new TicketManagement();
		
		//when
		tm.setAvailableeTickets(ticketsCreated);
		tm.setMainCategory("");
		tm.setTempClients(3);
		tm.generateTickets();
		tm.requestTickets("Categoria 3");
	}
	
	@Test(expected = CategoryTicketsSoldoutException.class)
	public void afterSellingAllTicketsFromOneCategoryThrowsException()throws SoldoutException{
		//given
		int ticketsCreated = 100;
		TicketManagement tm = new TicketManagement();
		
		//when
		tm.setAvailableeTickets(ticketsCreated);
		tm.setMainCategory("");
		tm.setTempClients(3);
		tm.generateTickets();	
		tm.requestTickets("Categoria 3");
	}
	
	
}
