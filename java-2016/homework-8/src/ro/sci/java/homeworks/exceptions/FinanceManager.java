package ro.sci.java.homeworks.exceptions;

public class FinanceManager {
	private double categoryOneRevenue;
	private double categoryTwoRevenue;
	private double categoryThreeRevenue;
	
	/**
	 * Calculates the revenue for tickets by category.
	 * @param The amount of earnings collected when finishing the sales for every tickets category.
	 */
	public void calculateCategoryRevenue(String category, double amount){
		switch (category) {
		case "Category 1":
			categoryOneRevenue = amount;
			break;
		case "Category 2":
			categoryTwoRevenue = amount;
			break;
		case "Category 3":
			categoryThreeRevenue = amount;
			break;
		}
	}
	
	/**
	 * Getter for category one tickets balance.
	 * @return the sales earned from tickets from category one.
	 */
	public double getCategoryOneBalance() {
		return categoryOneRevenue;
	}
	
	/**
	 * Getter for category two tickets balance.
	 * @return the sales earned from tickets from category two.
	 */
	public double getCategoryTwoBalance() {
		return categoryTwoRevenue;
	}
	
	/**
	 * Getter for category three tickets balance.
	 * @return the sales earned from tickets from category three.
	 */
	public double getCategoryThreeBalance() {
		return categoryThreeRevenue;
	}
}
