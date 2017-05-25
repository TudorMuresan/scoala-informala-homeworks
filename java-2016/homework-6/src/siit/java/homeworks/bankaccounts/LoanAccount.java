package siit.java.homeworks.bankaccounts;

import java.util.Calendar;
import java.util.Date;
/**
* The LoanAccount class is a specific type of Bank Account. Offers a loan to a client.
* @author Tudor Muresan
*/
public class LoanAccount extends BankAccount {

	private float loanAmmount;
	private float installmentsPeriod;
	private float monthlyInstallments;
	private float deadLinePenalty;

	/**
	  * Constructor.
	  * @param accountCreationDate The date when the account is created.
	  * @param loanAmmount The amount of money given as a loan to the client.
	  * @param installmentsPeriod The term in which the client must pay the loan back.
	  * @param deadLinePenalty A penalty applied if the monthly payment is late.
	  * @param interest Credit interest applied on the total amount of loan.
	  */
	public LoanAccount(Date accountCreationDate, float loanAmmount, float installmentsPeriod, float deadLinePenalty, float interest) {
		super(accountCreationDate, 0);
		balance = loanAmmount;
		this.installmentsPeriod = installmentsPeriod;
		this.loanAmmount = loanAmmount;
		this.deadLinePenalty = deadLinePenalty;
	}

	@Deprecated
	@Override
	/**
	 * Cant make withdrawal operations on this type of acount.
	*/
	public void withdrawFunds(float amount) throws InsufficientFundsException {
		throw new UnsupportedOperationException();
	}

	/** The getDaysDifference method calculates the time difference in days between two dates.
	  * @param startDate The beginning of the date from where the method will count the days.
	  * @param endDate The end of the period until the method will count the days.
	  * @return totalDaysDifference The number of days between the two given dates.
	  */
	private int getDaysDifference(Date startDate, Date endDate) {
		Calendar startingMonthIndex = Calendar.getInstance();
		startingMonthIndex.setTime(startDate);
		int startingMonthStamp = startingMonthIndex.get(Calendar.MONTH) - 1;
		int startingDayStamp = startingMonthIndex.get(Calendar.DAY_OF_MONTH);
		int yearCreated = startingMonthIndex.get(Calendar.YEAR);

		Calendar balanceTime = Calendar.getInstance();
		balanceTime.setTime(endDate);
		int currentMonth = balanceTime.get(Calendar.MONTH) - 1;
		int currentDay = balanceTime.get(Calendar.DAY_OF_MONTH);
		int currentYear = balanceTime.get(Calendar.YEAR);

		int creationMonthUntillEndOfYear = (int) (12 - startingMonthStamp);

		int totalMonthsDifference = creationMonthUntillEndOfYear + (int) currentMonth + 12 * ((currentYear - yearCreated) - 1);
		int totalDaysDifference = startingDayStamp + 30 * totalMonthsDifference + currentDay;
		return totalDaysDifference;
	}

	/** The method returns the amount of money the client has to pay every month
	 *  until the decided installment period will pass .
	  * @param installmentsPeriod The period on which the loan was decided, in months.
	  * @return The amount of money that the client has to pay monthly.
	  */
	public float getMonthlyInstallments(float installmentsPeriod) {
		this.monthlyInstallments = loanAmmount / installmentsPeriod + (loanAmmount * 0.1f) / installmentsPeriod;
		return this.monthlyInstallments;
	}

	/** The method adds funds to the balance until the balance will be at least 0.
	  * After the loan the balance will be negative and needs to get to 0 in time
	  * @param lastPaymentDate The day in which the last payment was made.
	  * @param paymentDate The day in which the client wants to add funds to the account.
	  * It will add the calculated monthly installment if the client didn't pay late the installment,
	  * but if the client will be late, he will pay the monthly installment with penalties applied.
	  */
	public void payInstallment(Date lastPaymentDate, Date paymentDate) {
		if (this.getDaysDifference(lastPaymentDate, paymentDate) > 30) {
			addFunds(getMonthlyInstallments(installmentsPeriod) + getMonthlyInstallments(installmentsPeriod) * deadLinePenalty);
		} else {
			addFunds(getMonthlyInstallments(installmentsPeriod));
		}
	}
}
