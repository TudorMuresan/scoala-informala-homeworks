package siit.java.homeworks.bankaccounts;

import java.util.Date;
/**
* The RunningAccount class is a specific type of Bank Account.
*  The account is used for the clients to get the salary and to make withdrawals and adds with no comission.
* @author Tudor Muresan
*/
public class RunningAccount extends BankAccount {
	/**
	  * Constructor.
	  * @param accountCreationDate The date when the account is created.
	  * @param monthlyFee Monthly fee for an account type, 
	  * if the account doesn't have a monthly fee, the default will be 0.
	  */
	public RunningAccount(Date accountCreationDate, float monthlyFee) {
		super(accountCreationDate, monthlyFee);
	}

}
