package grsu.project.report;

import grsu.project.data.LogRecord;

public interface Report {

	public void addInfo(LogRecord logRecord);

	public String writeInfo();
}
