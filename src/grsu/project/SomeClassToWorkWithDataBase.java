package grsu.project;

import grsu.project.data.LogRecord;

public class SomeClassToWorkWithDataBase {
	private LogRecordList logRecords;
	private DataBase db;
	private int i = 0;

	public SomeClassToWorkWithDataBase() {
		db = new DataBase("LogRecordsDB");
		logRecords = new LogRecordList();
	}

	public void append(LogRecord logRecord) {
		if (logRecords.add(logRecord.getDate())) {
			logRecords.add(logRecord);
		} else {
			flush();
			logRecords.add(logRecord);
		}
	}

	private void flush() {
		db.save(logRecords);
		i += logRecords.getRecords().size();
		System.out.println("Saved " + i);
		logRecords.clear();
	}
}
