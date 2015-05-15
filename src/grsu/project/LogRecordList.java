package grsu.project;

import grsu.project.data.LogRecord;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class LogRecordList {

	private List<LogRecord> records;

	public List<LogRecord> getRecords() {
		return records;
	}

	public LogRecordList() {
		records = new ArrayList<LogRecord>();
	}

	public boolean add(Date date) {
		if (records.size() > 0) {
			if (date.equals(records.get(0).getDate())) {
				return true;
			}
			return false;
		} else {
			return true;
		}
	}

	public void add(LogRecord logRecord) {
		records.add(logRecord);
	}

	public double getScore() {
		return records.get(0).getDate().getTime();
	}

	public void clear() {
		records.clear();
	}
}
