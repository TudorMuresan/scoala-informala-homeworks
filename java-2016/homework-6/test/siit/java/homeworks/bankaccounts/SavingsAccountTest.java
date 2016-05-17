package siit.java.homeworks.bankaccounts;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class SavingsAccountTest {

	@Test
	public void monthlyFeesApplied() {
		// given
		float balance = 6000f;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SavingsAccount savingsAccount = new SavingsAccount(accountCreationTime, 10f, 0.02f, 0.06f);
		// when
		savingsAccount.addFunds(balance);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
		// then
		assertEquals(5960, savingsAccount.getCalculatedBalance(accountCreationTime, balanceInterogationTime), 0);
	}

	@Test(expected = InsufficientFundsException.class)
	public void whenWithdrawAllMoneyAndBalanceDoesentCoverTheWithdrawFee_paymentFails() throws Exception {
		// given
		float balance = 6000f;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SavingsAccount savingsAccount = new SavingsAccount(accountCreationTime, 10f, 0.02f, 0.06f);
		savingsAccount.addFunds(balance);
		// when
		savingsAccount.withdrawFunds(balance);
	}

	@Test(expected = InsufficientFundsException.class)
	public void whenTryingToWithdrawMoneyMoreThanBalance_paymentFails() throws Exception {
		// given
		float balance = 6000f;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		SavingsAccount savingsAccount = new SavingsAccount(accountCreationTime, 10f, 0.02f, 0.06f);
		savingsAccount.addFunds(balance);
		// when
		savingsAccount.withdrawFunds(7000f);
	}

	@Test
	public void whenOneYearPassed_twelveMonthlyFeesAreAppliedAndInterestForOneYearIsGenerated() throws InsufficientFundsException {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);

		float balance = 6000f;
		float monthlyFee = 10f;
		float withdrawalCommision = 0.02f;
		float interestGeneratorPerYear = 0.06f;
		SavingsAccount savingsAccount = new SavingsAccount(accountCreationTime, monthlyFee, withdrawalCommision, interestGeneratorPerYear);
		// when
		Date balanceInterogationTime = TestUtils.getDate(2017, 2, 1);
		savingsAccount.addFunds(balance);
		// then
		float expectedFeesAfterOneYear = 12 * monthlyFee;// 120 one year monthly
															// fee
		float expectedFeesUntillFinalDate = 1 * 10;// 10 remaining 4 monthly fee
		balance -= expectedFeesAfterOneYear;
		balance += balance * interestGeneratorPerYear;
		balance -= expectedFeesUntillFinalDate;

		assertEquals("monthly fee for 13 months deducted and one year interest generated", balance,
				savingsAccount.getCalculatedBalance(accountCreationTime, balanceInterogationTime), 0.5f);

	}

	@Test
	public void whenMultipleOperationsCalled_balanceIsCorrect() throws InsufficientFundsException {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		float withdrawalCommision = 0.02f;
		float balance = 6000f;
		float monthlyFee = 10f;
		float interestGeneratorPerYear = 0.06f;
		SavingsAccount savingsAccount = new SavingsAccount(accountCreationTime, monthlyFee, withdrawalCommision, interestGeneratorPerYear);
		savingsAccount.addFunds(balance);

		// when
		savingsAccount.withdrawFunds(400);
		savingsAccount.withdrawFunds(600);
		Date balanceInterogationTime = TestUtils.getDate(2018, 5, 1);

		// then
		float withdrawals = (400 + 400f * withdrawalCommision) + (600f + 600f * withdrawalCommision);
		float actualBalance = savingsAccount.getCalculatedBalance(accountCreationTime, balanceInterogationTime);
		float expectedFeesAfterOneYear = 12 * monthlyFee;// 120 one year monthly
															// fee
		float expectedFeesUntillFinalDate = 4 * 10;// 40 remaining 4 monthly fee
		balance -= withdrawals + expectedFeesAfterOneYear;
		balance += balance * interestGeneratorPerYear;
		balance -= expectedFeesAfterOneYear;
		balance += balance * interestGeneratorPerYear;
		balance -= expectedFeesUntillFinalDate;
		assertEquals("16 months with fees and interest generated calculation.", balance, actualBalance, 0);
	}
}
