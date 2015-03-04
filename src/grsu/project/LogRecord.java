package grsu.project;

public class LogRecord {
	private String host;
	private String timestamp;
	private String request;
	private String replyCode;
	private String replyBytes;

	public LogRecord(String logLine, int lineNumber) {
		LogPattern logPattern = LogPattern.getInstance();
		this.host = logPattern.DetermineHost(logLine, lineNumber);
		this.timestamp = logPattern.DetermineTimestamp(logLine, lineNumber);
		this.request = logPattern.DetermineRequest(logLine, lineNumber);
		this.replyCode = logPattern.DetermineReplyCode(logLine, lineNumber);
		this.replyBytes = logPattern.DetermineReplyBytes(logLine, lineNumber);
	}

	@Override
	public String toString() {
		return host + " - - [" + timestamp + "]+ \"" + request + "\" "
				+ replyCode + " " + replyBytes;
	}
}
