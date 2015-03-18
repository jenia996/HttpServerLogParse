package grsu.project;

public class LogRecord {
	private String host;
	private String timestamp;
	private String request;
	private String replyCode;
	private String replyBytes;

	@Override
	public String toString() {
		return host + " - - [" + timestamp + "] \"" + request + "\" "
				+ replyCode + " " + replyBytes;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public String getReplyBytes() {
		return replyBytes;
	}

	public void setReplyBytes(String replyBytes) {
		this.replyBytes = replyBytes;
	}

}
