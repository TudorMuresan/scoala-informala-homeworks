package siit.java.homeworks.bankaccounts;

import java.util.Date;

public class LoanAccount extends BankAccount {

	public LoanAccount(Date accountCreationDate, float monthlyFee) {
		super(accountCreationDate, monthlyFee);
	}
	
	@Deprecated
	@Override
	public void withdrawFunds(float amount) throws InsufficientFundsException {
		throw new UnsupportedOperationException();
	}

}
