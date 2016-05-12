package siit.java.homeworks.bankaccounts;

import java.util.Calendar;
import java.util.Date;

public class TestUtils {
	
	/**
	 * @param year
	 *            e.g 2016
	 * @param month
	 *            1-12
	 * @param day
	 *            1-31
	 * @return {@link Date} object
	 */
	public static Date getDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month + 1, day);
		return calendar.getTime();
	}

}
