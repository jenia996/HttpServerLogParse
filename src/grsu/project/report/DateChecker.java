package grsu.project.report;

import grsu.project.data.LogRecord;

import java.util.Date;

public class DateChecker {
	private static Boolean checkStartDate(Date startDate, Date logRecordDate) {
		if (startDate != null) {
			return logRecordDate.after(startDate);
		}
		return true;
	}

	private static Boolean checkEndDate(Date endDate, Date logRecordDate) {
		if (endDate != null) {
			return logRecordDate.before(endDate);
		}
		return true;
	}

	public static Boolean checkDate(Date startDate, Date endDate,
			LogRecord logRecord) {
		try {
			return checkStartDate(startDate, logRecord.getDate())
					&& checkEndDate(endDate, logRecord.getDate());
		} catch (NullPointerException e) {
			return true;
		}
	}

}
