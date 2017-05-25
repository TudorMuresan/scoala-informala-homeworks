package siit.java.homeworks.bankaccounts;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class RunningAccountTest {
	@Test
	public void checkRunningAccount_whenSalaryIsAdded() {
		// given
		float balance = 0;
		float monthlyFee = 10f;
		float salaryPerMonth = 1500;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, monthlyFee);
		runningAccount.addFunds(salaryPerMonth);
		// when
		Date balanceInterogationTime = TestUtils.getDate(2016, 2, 1);
		balance = salaryPerMonth - monthlyFee;
		// then
		assertEquals(balance, runningAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test
	public void whenOneSalaryIsRecorded_withtMonthlyFeeApplied() {
		// given
		float balance = 1500;
		float salaryPerMonth = 1500;
		float monthlyFee = 10f;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, monthlyFee);
		runningAccount.addFunds(balance);
		// when
		Date balanceInterogationTime = TestUtils.getDate(2016, 2, 1);
		runningAccount.addFunds(salaryPerMonth);
		balance += salaryPerMonth - monthlyFee;

		// then
		assertEquals(balance, runningAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test
	public void whenMultipleSalaryesAreRecordedAndMonthlyFeesAreApplied_resultIsCorrect() {
		// given
		float balance = 1500;
		float salaryPerMonth = 1500;
		float monthlyFee = 10f;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, monthlyFee);
		runningAccount.addFunds(balance);
		// when
		Date balanceInterogationTime = TestUtils.getDate(2016, 10, 1);
		int monthsInterogationRange = runningAccount.getMonthsDifference(accountCreationTime, balanceInterogationTime);
		balance += salaryPerMonth * monthsInterogationRange;
		balance -= monthlyFee * monthsInterogationRange;
		runningAccount.addFunds(salaryPerMonth * monthsInterogationRange);

		// then
		assertEquals(balance, runningAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test
	public void noComissionFeesAppliedForWithdrawalsAndAdds() throws InsufficientFundsException {
		// given
		float balance = 0;
		float salaryPerMonth = 1500;
		float monthlyFee = 10f;
		float withdrawalAttendance = 500f;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, monthlyFee);
		// when
		Date balanceInterogationTime = TestUtils.getDate(2016, 4, 1);
		int monthsInterogationRange = runningAccount.getMonthsDifference(accountCreationTime, balanceInterogationTime);
		runningAccount.addFunds(salaryPerMonth * monthsInterogationRange);
		runningAccount.withdrawFunds(withdrawalAttendance);
		balance -= withdrawalAttendance;
		balance += salaryPerMonth * monthsInterogationRange;
		balance -= monthlyFee * monthsInterogationRange;
		// then
		assertEquals(balance, runningAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test
	public void noInterestGenerated() throws InsufficientFundsException {
		// given
		float balance = 0;
		float salaryPerMonth = 1500;
		float monthlyFee = 10f;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, monthlyFee);
		// when
		Date balanceInterogationTime = TestUtils.getDate(2017, 4, 1);
		int monthsInterogationRange = runningAccount.getMonthsDifference(accountCreationTime, balanceInterogationTime);

		balance += salaryPerMonth * monthsInterogationRange;
		balance -= monthlyFee * monthsInterogationRange;
		runningAccount.addFunds(salaryPerMonth * monthsInterogationRange);
		// then
		assertEquals(balance, runningAccount.getBalance(balanceInterogationTime), 0);
	}

	@Test(expected = InsufficientFundsException.class)
	public void whenTryingToWithdrawMoneyMoreThanBalance_paymentFails() throws Exception {
		// given
		float balance = 1500;
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, 10f);
		runningAccount.addFunds(balance);
		// when
		runningAccount.withdrawFunds(1600);
	}
}
