package grsu.project;


public class LogRecord {
	private String host;
	private String timestamp;
	private String request;
	private String replyCode;
	private String replyBytes;

	public LogRecord(String logLine) {
		LogPattern logPattern = new LogPattern();
		this.host = logPattern.DetermineHost(logLine);
		this.timestamp = logPattern.DetermineTimestamp(logLine);
		this.request = logPattern.DetermineRequest(logLine);
		this.replyCode = logPattern.DetermineReplyCode(logLine);
		this.replyBytes = logPattern.DetermineReplyBytes(logLine);
	}

	@Override
	public String toString() {
		return host + " - - " + timestamp+ " " + request + " " + replyCode + " " + replyBytes;
	}
}
