package siit.java.homeworks.bankaccounts;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class CreditAccountTest {

	@Test
	public void monthlyFeesAreNotApplied() {
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		CreditAccount creditAccount = new CreditAccount(accountCreationTime, 1000f, 0.02f, 0.01f);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
		assertEquals(1000, creditAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test
	public void whenMultipleOperationsCalled_balanceIsCorrect() throws InsufficientFundsException {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		float withdrawalCommision = 0.02f;
		float creditPayBackPenalty = 0.01f;
		float credit = 1000f;
		CreditAccount creditAccount = new CreditAccount(accountCreationTime, credit, withdrawalCommision,
				creditPayBackPenalty);

		// when
		creditAccount.withdrawFunds(100);
		creditAccount.withdrawFunds(200);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
		float actualBalance = creditAccount.getBalance(balanceInterogationTime);

		// then
		float withdrawals = (100f + 100f * withdrawalCommision) + (200f + 200f * withdrawalCommision);
		float expectedBalanceWithoutPenalties = credit - withdrawals;
		float expectedPenalty = (credit - expectedBalanceWithoutPenalties) * creditPayBackPenalty;
		float expectedBalanceWithPenalties = expectedBalanceWithoutPenalties - expectedPenalty;

		assertEquals("4 months worth of fees and the penalty should have been deducted.", expectedBalanceWithPenalties,
				actualBalance, 0);
	}

	@Test
	public void whenCreditsAreCovered_balanceIsCorrect() throws InsufficientFundsException {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		float withdrawalCommision = 0.02f;
		float creditPayBackPenalty = 0.01f;
		float credit = 1000f;
		CreditAccount creditAccount = new CreditAccount(accountCreationTime, credit, withdrawalCommision,
				creditPayBackPenalty);

		// when
		creditAccount.withdrawFunds(100);
		creditAccount.withdrawFunds(200);
		creditAccount.addFunds(350);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 1);
		float actualBalance = creditAccount.getBalance(balanceInterogationTime);

		// then
		float withdrawals = (100f + 100f * withdrawalCommision) + (200f + 200f * withdrawalCommision);
		float expectedBalance = credit - withdrawals + 350;

		assertEquals("4 months worth of fees and no penalty should have been deducted.", expectedBalance,
				actualBalance, 0);
	}

	

}
