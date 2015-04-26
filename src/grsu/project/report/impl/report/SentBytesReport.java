package grsu.project.report.impl.report;

import grsu.project.data.LogRecord;
import grsu.project.report.Report;

public class SentBytesReport implements Report {

	private long sentBytes;

	@Override
	public void addInfo(LogRecord logRecord) {
		this.sentBytes += logRecord.getReplyBytes();
	}

	public String writeInfo() {
		return String.valueOf(sentBytes) + '\n';
	}
}
