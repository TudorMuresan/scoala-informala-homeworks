package siit.java.homeworks.bankaccounts;

import java.util.Calendar;
import java.util.Date;
/**
* The BankAccount class is the base class for different types of bank accounts.
* Provides balance operations (get balance and add funds) and get months difference betweend two periods of time. 
* @author Tudor Muresan
*/
public abstract class BankAccount {

	protected float balance;
	private Date accountCreationDate;
	private float monthlyFee;

	/**
	  * Constructor.
	  * @param accountCreationDate The date when the account is created.
	  * @param monthlyFee Monthly fee for an account type, 
	  * if the account doesn't have a monthly fee, the default will be 0.
	  */
	public BankAccount(Date accountCreationDate, float monthlyFee) {
		super();
		this.accountCreationDate = accountCreationDate;
		this.monthlyFee = monthlyFee;
	}

	/** The addFunds method simply adds an amount of money to the account's balance.
	  * @param amount The amount of money that will be added to the balance.
	  */
	public void addFunds(float amount) {
		balance += amount;
	}
	/** The getMonthsDifference method calculates the time difference in months between two dates.
	  * @param startDate The beginning of the date from where the method will count the months.
	  * @param endDate The end of the period until the method will count the months.
	  * @return totalMonthsDifference The number of months between the two given dates.
	  */
	public int getMonthsDifference(Date startDate, Date endDate) {
		Calendar startingMonthIndex = Calendar.getInstance();
		startingMonthIndex.setTime(startDate);
		long startingMonthStamp = startingMonthIndex.get(Calendar.MONTH) - 1;
		int yearCreated = startingMonthIndex.get(Calendar.YEAR);

		Calendar balanceTime = Calendar.getInstance();
		balanceTime.setTime(endDate);
		long currentMonth = balanceTime.get(Calendar.MONTH) - 1;
		int currentYear = balanceTime.get(Calendar.YEAR);

		int creationMonthUntillEndOfYear = (int) (12 - startingMonthStamp);

		int totalMonthsDifference = creationMonthUntillEndOfYear + (int) currentMonth + 12 * ((currentYear - yearCreated) - 1);
		return totalMonthsDifference;

	}

	/** The withdrawFunds method simply decrease an amount of money from the account.
	  * @exception Exception Throws an exception if there are insufficient founds in the account.
	  * @throws InsufficientFundsException If insufficient funds in account.
	  * @param amount The amount of money that will be decreased from the balance.
	  */
	public void withdrawFunds(float amount) throws InsufficientFundsException {
		if (amount > balance) {
			throw new InsufficientFundsException();
		}
		balance -= amount;
	}
	
	/** The method returns the balance .
	  * @param balanceDate The date when the balance request occurs.
	  * @return execute the method getBalanceWithMonthlyFeesApplied
	  *  by passing the given date as parameter to the next method.
	  */
	public float getBalance(Date balanceDate) {
		return getBalanceWithMonthlyFeesApplied(balanceDate);
	}

	/** The getBalanceWithMonthlyFeesApplied method returns the balance with monthly fees applied.
	  * @param balanceDate The date when the balance request occurs.
	  * @return the balance with monthly fees applied at the asked date.
	  */
	private float getBalanceWithMonthlyFeesApplied(Date balanceDate) {
		int monthsInterval = getMonthsDifference(accountCreationDate, balanceDate);
		return balance - monthlyFee * monthsInterval;
	}

}
