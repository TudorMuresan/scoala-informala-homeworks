package ro.sci.java.homeworks.exceptions;

import static org.junit.Assert.*;
import org.junit.Test;

public class TicketManagementTest {
	@Test
	public void createTickets(){
		TicketManagement tm = new TicketManagement(100);
		tm.setMainCategory("Category 3");
		tm.	generateTickets();
	
	}
	
	@Test
	public void whenDecidingTotalTicketsRequestngClientsNumberIsCorrect(){
		//given
		int ticketsCreated = 100;
		TicketManagement tm = new TicketManagement(ticketsCreated);
		
		//when
		tm.	generateTickets();
		//then
		assertEquals(ticketsCreated + 13, tm.getTotalClients());
	}
	
	@Test
	public void createTicketsByCategoryCorrectly(){
		//given
		int ticketsCreated = 150;
		TicketManagement tm = new TicketManagement(ticketsCreated);
		
		//when
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
		TicketManagement tm = new TicketManagement(ticketsCreated);
		
		//when
		tm.setMainCategory("Category 3");
		tm.generateTickets();
		
		int revenuesCategoryThree = (ticketsCreated *50/100) *50;
		int revenuesCategoryTwo = (ticketsCreated *35/100) *80;
		int revenuesCategoryOne = (ticketsCreated -((ticketsCreated *50/100) +(ticketsCreated *35/100))) * 120;
		
		//then
		assertEquals(revenuesCategoryOne, tm.ticketsFinancialReport.getCategoryOneBalance(),0);
		assertEquals(revenuesCategoryTwo, tm.ticketsFinancialReport.getCategoryTwoBalance(),0);
		assertEquals(revenuesCategoryThree, tm.ticketsFinancialReport.getCategoryThreeBalance(),0);
	}
	
	@Test
	public void every17clientgetsThreeTicketsIfAvailable(){
		//given
		int ticketsCreated = 19;
		TicketManagement tm = new TicketManagement(ticketsCreated);
		//when
		tm.setMainCategory("Category 3");
		tm.generateTickets();

		//then
		assertEquals(17, tm.getServedClients(),0);
	}
	
	@Test(expected = SoldoutException.class)
	public void afterSellingAllTicketsFromAllCategoryesThrowsException()throws SoldoutException{
		//given
		int ticketsCreated = 0;
		TicketManagement tm = new TicketManagement(ticketsCreated);
		
		//when
		tm.setMainCategory("");
		tm.generateTickets();
		tm.sellTicket(new Client(1), "Category 1");
	}
	
	@Test(expected = CategoryTicketsSoldoutException.class)
	public void afterSellingAllTicketsFromOneCategoryThrowsException()throws CategoryTicketsSoldoutException,SoldoutException{
		//given
		int ticketsCreated = 6;
		TicketManagement tm = new TicketManagement(ticketsCreated);
		
		//when
		//created three tickets for category 3
		tm.setMainCategory("");
		tm.generateTickets();
		tm.sellTicket(new Client(1), "Category 3");
		tm.sellTicket(new Client(2), "Category 3");
		tm.sellTicket(new Client(3), "Category 3");//after third ticket selled throws the exception
		
	}
}
