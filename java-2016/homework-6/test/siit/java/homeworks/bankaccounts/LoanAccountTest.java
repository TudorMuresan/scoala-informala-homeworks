package siit.java.homeworks.bankaccounts;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.junit.Test;

public class LoanAccountTest {

	@Test
	public void loanAccepted_loanPouredIntoRunningAccount() {
		// given
		float installmentsPeriod = 60f;
		float loanAmount = 100000f;

		// when
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		LoanAccount loanAccount = new LoanAccount(accountCreationTime, 100000f, installmentsPeriod, 0.01f, 0.1f);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, 10f);
		runningAccount.addFunds(loanAccount.balance);
		// then
		assertEquals(loanAmount, runningAccount.getBalance(accountCreationTime), 0);

	}

	@Test
	public void loanAccepted_loanPouredIntoRunningAccountSetLoanAccountDebt() {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		float debt = 0f;
		float loanAmount = 100000f;
		float installmentsPeriod = 60f;
		float credirInterest = 0.1f;
		LoanAccount loanAccount = new LoanAccount(accountCreationTime, loanAmount, installmentsPeriod, 0.01f, credirInterest);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, 10f);
		// when
		runningAccount.addFunds(loanAccount.balance);
		loanAccount.balance -= loanAmount + loanAccount.getMonthlyInstallments(installmentsPeriod) * installmentsPeriod;
		debt -= loanAmount + loanAmount * credirInterest;

		assertEquals(debt, loanAccount.balance, 0f);
	}

	@Test
	public void whenLateOnAMonthPayment_deadlinePenaltyApplied() {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		float debtWithOneMonthPayed = 0f;
		float loanAmount = 100000f;
		float penaltyMonthLate = 0.05f;
		float installmentsPeriod = 60f;
		float credirInterest = 0.1f;
		LoanAccount loanAccount = new LoanAccount(accountCreationTime, loanAmount, installmentsPeriod, penaltyMonthLate, credirInterest);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, 10f);
		// when
		float monthlyInstallments = loanAccount.getMonthlyInstallments(installmentsPeriod);
		runningAccount.addFunds(loanAmount);
		Date balanceInterogationTime = TestUtils.getDate(2016, 5, 5);
		// then
		loanAccount.balance -= loanAmount + loanAccount.getMonthlyInstallments(installmentsPeriod) * installmentsPeriod;
		float monthlyInstallmentWithPenaltyApplied = monthlyInstallments + monthlyInstallments * penaltyMonthLate;
		debtWithOneMonthPayed -= loanAmount + loanAmount * credirInterest - monthlyInstallmentWithPenaltyApplied;
		loanAccount.payInstallment(accountCreationTime, balanceInterogationTime);
		assertEquals(debtWithOneMonthPayed, loanAccount.balance, 0f);
	}

	@Test
	public void whenMonthPaymentAtTime_deadlinePenaltyNotApplied() {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);
		float debtWithOneMonthPayed = 0f;
		float loanAmount = 100000f;
		float penaltyMonthLate = 0.05f;
		float installmentsPeriod = 60f;
		float credirInterest = 0.1f;
		LoanAccount loanAccount = new LoanAccount(accountCreationTime, loanAmount, installmentsPeriod, penaltyMonthLate, credirInterest);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, 10f);
		// when
		float monthlyInstallments = loanAccount.getMonthlyInstallments(installmentsPeriod);
		runningAccount.addFunds(loanAmount);
		Date balanceInterogationTime = TestUtils.getDate(2016, 1, 5);
		// then
		loanAccount.balance -= loanAmount + loanAccount.getMonthlyInstallments(installmentsPeriod) * installmentsPeriod;
		debtWithOneMonthPayed -= loanAmount + loanAmount * credirInterest - monthlyInstallments;
		loanAccount.payInstallment(accountCreationTime, balanceInterogationTime);
		assertEquals(debtWithOneMonthPayed, loanAccount.balance, 0f);
	}

	@Test
	public void whenAllInstallmentsArePayed_loanBalanceIsZero() {
		// given
		Date accountCreationTime = TestUtils.getDate(2016, 1, 1);

		float debtWithOneMonthPayed = 0f;
		float loanAmount = 100000f;
		float penaltyMonthLate = 0.05f;
		float installmentsPeriod = 60f;
		float credirInterest = 0.1f;
		float neededBalance = 0f;
		LoanAccount loanAccount = new LoanAccount(accountCreationTime, loanAmount, installmentsPeriod, penaltyMonthLate, credirInterest);
		RunningAccount runningAccount = new RunningAccount(accountCreationTime, 10f);
		// when
		float monthlyInstallments = loanAccount.getMonthlyInstallments(installmentsPeriod);
		runningAccount.addFunds(loanAmount);
		Date endLoanPaymentDate = TestUtils.getDate(2021, 1, 1);
		float totalMonthsToPay = loanAccount.getMonthsDifference(accountCreationTime, endLoanPaymentDate);

		// then
		loanAccount.balance -= loanAmount + loanAccount.getMonthlyInstallments(installmentsPeriod) * installmentsPeriod;
		while (totalMonthsToPay > 0) {
			totalMonthsToPay--;
			loanAccount.balance += monthlyInstallments;
		}
		debtWithOneMonthPayed -= loanAmount + loanAmount * credirInterest - monthlyInstallments;

		System.out.println(debtWithOneMonthPayed);
		assertEquals(neededBalance, loanAccount.balance, 0.1f);
	}

}
