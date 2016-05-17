package siit.java.homeworks.bankaccounts;

import java.util.Date;
/**
* The Savings Account class is a specific type of Bank Account.
*  The account is used for the clients to save money on it.
* @author Tudor Muresan
*/
public class SavingsAccount extends BankAccount{
	/**
	  * Constructor.
	  * @param accountCreationDate The date when the account is created.
	   * @param monthlyFee Monthly fee for this account type, decided by the bank
	  * if the account doesn't have a monthly fee, the default will be 0.
	  * @param withdrawalCommision The commission applied 
	  * at a withdrawal operation at this type of account.
	  * @param interestPerYear The account will generate interest every year.
	  */
	private float withdrawalCommision;
	private float interestPerYear;
	private float savingsBalance;
	public SavingsAccount(Date accountCreationDate, float monthlyFee,float withdrawalCommision,float interestPerYear) {
		super(accountCreationDate, monthlyFee);
		balance = savingsBalance;
		this.withdrawalCommision = withdrawalCommision;
		this.interestPerYear = interestPerYear;
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
	/** The getCalclatedBalance method will calculate the balance at a given period of time
	 *  with monthly fees applied and interest generated every year.
	  * @param beginComparisonDate The calculation will start from this date.
	  * @param balanceDate The date until the calculation will be done.
	  * @return the balance with monthly fees applied and interest generated once at 12 months 
	  * by running the method getBalanceWithInterestAndMonthlyFee.
	  */
	public float getCalculatedBalance(Date beginComparisonDate,Date balanceDate) {
		return getBalanceWithInterestAndMonthlyFee(beginComparisonDate,balanceDate);
	}
	
	/** This will give the client information about the balance of his account at a given period of time
	  * @param currentDate The date when the client asks for the balance.
	  * @return asks the account for the balance through getRealTimebalance method.
	  */
	public float getBalance(Date currentDate) {
		return getRealTimeBalance(currentDate);
	}
	/** This method will actually return the account balance.
	  * @param currentDate The date when the client asks for the balance.
	  * @return the balance of the client at the asked date.
	  */
	private float getRealTimeBalance(Date currentDate){
		return balance;
	}
	
	/** The effective method that will calculate the balance at a given period of time
	 *  with monthly fees applied and interest generated every year.
	  * @param beginComparisonDate The calculation will start from this date.
	  * @param balanceDate The date until the calculation will be done.
	  * @return the balance with monthly fees applied and interest generated once at 12 months 
s	  */
	private float getBalanceWithInterestAndMonthlyFee(Date beginComparison, Date balanceDate){
		int monthsDifference = getMonthsDifference(beginComparison,balanceDate);
		for(int i=1;i<=monthsDifference;i++){
			if(i%12 ==0){
				balance -= 10;
				balance +=  balance*interestPerYear;
			}
			else{
				balance -= 10;
			}
		}
		return balance;
	}
}
