package siit.java.homeworks.bankaccounts;

import java.util.Date;
/**
* The CreditAccount class is a specific type of Bank Account. Offers a type of credit to a client.
* @author Tudor Muresan
*/
public class CreditAccount extends BankAccount {

	private float credit;
	private float withdrawalCommision;
	private float payBackPenalty;
	/**
	  * Constructor.
	  * @param accountCreationDate The date when the account is created.
	  * @param credit Credit amount of the CreditAccount.
	  * @param withdrawalCommision The commission applied 
	  * at a withdrawal operation at this type of account.
	  * @param payBackPenalty Is a penalty applied if the payment is late.
	  */
	public CreditAccount(Date accountCreationDate, float credit, float withdrawalCommision, float payBackPenalty) {
		super(accountCreationDate, 0);
		this.credit = credit;
		balance = credit;
		this.withdrawalCommision = withdrawalCommision;
		this.payBackPenalty = payBackPenalty;
	}

	@Override
	/** The withdrawFunds method simply decrease an amount of money from the account with a withdrawal commission applied.
	  * @exception Exception Throws an exception if there are insufficient founds in the account.
	  * @throws InsufficientFundsException If insufficient funds in account.
	  * @param amount The amount of money that will be decreased from the balance.
	  */
	public void withdrawFunds(float amount) throws InsufficientFundsException {
		super.withdrawFunds(amount + amount * withdrawalCommision);
	}

	@Override
	/** The method returns the balance .
	  * @param balanceDate The date when the balance request occurs.
	  * @return runs the method applyPenalties that will add the necessary penalties.
	  */
	public float getBalance(Date balanceDate) {
		// Ideally we should calculate the credit penalty for each and every
		// month, but for the sake of simplicity we will only compute it once.
		float balance = super.getBalance(balanceDate);
		return applyPenalties(balance);
	}
	/** The applyPenalties method returns the balance with penalties applied.
	  * @param balance The date when the balance request occurs.
	  * @return the balance with penalties applied at a given date.
	  */
	private float applyPenalties(float balance) {
		if (credit <= balance) {
			return balance;
		} else {
			return balance - (credit - balance) * payBackPenalty;
		}
	}

}
