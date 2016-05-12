package siit.java.homeworks.bankaccounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void whenMonthlyFeeIs0_balanceIsNotModified() {
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SimpleBankAccount simpleBankAccount = new SimpleBankAccount(accountCreationTime, 0);
		simpleBankAccount.addFunds(1000);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
		assertEquals(1000, simpleBankAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test
	public void whenMonthlyFeeIsSet_balanceIsAutomaticallyUpdated() {
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SimpleBankAccount simpleBankAccount = new SimpleBankAccount(accountCreationTime, 5);
		simpleBankAccount.addFunds(1000);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
		// 5$ for every 4 months (Jan, Feb, Mar & Apr) = 20$
		assertEquals("4 months worth of fees should have been deducted.", 980,
				simpleBankAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test
	public void whenMultipleOperationsCalled_balanceIsCorrect() throws InsufficientFundsException {
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SimpleBankAccount simpleBankAccount = new SimpleBankAccount(accountCreationTime, 5);
		simpleBankAccount.addFunds(1000);
		simpleBankAccount.withdrawFunds(100);
		simpleBankAccount.withdrawFunds(200);
		simpleBankAccount.addFunds(20);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
		assertEquals("4 months worth of fees should have been deducted.", 700,
				simpleBankAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test(expected = InsufficientFundsException.class)
	public void whenNotEnoughFunds_withdrawFails() throws InsufficientFundsException {
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SimpleBankAccount simpleBankAccount = new SimpleBankAccount(accountCreationTime, 5);
		simpleBankAccount.addFunds(1000);
		simpleBankAccount.withdrawFunds(1001);
	}

	@Test
	public void whenNotEnoughFunds_andWithdrawCalled_balanceIsCorrect() throws InsufficientFundsException {
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SimpleBankAccount simpleBankAccount = new SimpleBankAccount(accountCreationTime, 5);
		simpleBankAccount.addFunds(1000);
		try {
			simpleBankAccount.withdrawFunds(1001);
			fail("There should have been insufficient funds.");
		} catch (InsufficientFundsException e) {
			// correct - we expected this to be thrown
			// we can now verify whether the balance has not been updated by the
			// withdrawal.
			Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
			assertEquals("4 months worth of fees should have been deducted.", 980,
					simpleBankAccount.getBalance(balanceInterogationTime), 0);
		}
	}

	/*
	 * This class is required because BankAccount is an abstract class that can
	 * not be instantiated. Since we wish to test the logic that is in
	 * BankAccount (not its specific children), we extend it and add NO
	 * functionality to it.
	 */
	private static class SimpleBankAccount extends BankAccount {

		public SimpleBankAccount(Date accountCreationDate, float monthlyFee) {
			super(accountCreationDate, monthlyFee);
		}

	}

}
