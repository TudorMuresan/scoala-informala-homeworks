package siit.java.homeworks.bankaccounts;

import java.util.Date;

public class CreditAccount extends BankAccount {

	private float credit;
	private float withdrawalCommision;
	private float payBackPenalty;

	public CreditAccount(Date accountCreationDate, float credit, float withdrawalCommision, float payBackPenalty) {
		super(accountCreationDate, 0);
		this.credit = credit;
		balance = credit;
		this.withdrawalCommision = withdrawalCommision;
		this.payBackPenalty = payBackPenalty;
	}

	@Override
	public void withdrawFunds(float amount) throws InsufficientFundsException {
		super.withdrawFunds(amount + amount * withdrawalCommision);
	}

	@Override
	public float getBalance(Date balanceDate) {
		// Ideally we should calculate the credit penalty for each and every
		// month, but for the sake of simplicity we will only compute it once.
		float balance = super.getBalance(balanceDate);
		return applyPenalties(balance);
	}

	private float applyPenalties(float balance) {
		if (credit <= balance) {
			return balance;
		} else {
			return balance - (credit - balance) * payBackPenalty;
		}
	}

}
