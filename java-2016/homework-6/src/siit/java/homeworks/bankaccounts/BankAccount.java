package siit.java.homeworks.bankaccounts;

import java.util.Calendar;
import java.util.Date;

public abstract class BankAccount {

	protected float balance;
	private Date accountCreationDate;
	private float monthlyFee;

	public BankAccount(Date accountCreationDate, float monthlyFee) {
		super();
		this.accountCreationDate = accountCreationDate;
		this.monthlyFee = monthlyFee;
	}

	public void addFunds(float amount) {
		balance += amount;
	}

	public void withdrawFunds(float amount) throws InsufficientFundsException {
		if (amount > balance) {
			throw new InsufficientFundsException();
		}
		balance -= amount;
	}

	public float getBalance(Date balanceDate) {
		return getBalanceWithMonthlyFeesApplied(balanceDate);
	}

	private float getBalanceWithMonthlyFeesApplied(Date balanceDate) {
		// We assume that the account dates are always from the same year to not
		// overcomplicate the number of months
		Calendar accountCreationTime = Calendar.getInstance();
		accountCreationTime.setTime(accountCreationDate);
		// JANUARY is 0
		int accountCreationMonth = accountCreationTime.get(Calendar.MONTH);
		Calendar balanceTime = Calendar.getInstance();
		balanceTime.setTime(balanceDate);
		int currentMonth = balanceTime.get(Calendar.MONTH);
		return balance - monthlyFee * (currentMonth - accountCreationMonth);
	}

}
