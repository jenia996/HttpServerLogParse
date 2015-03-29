package grsu.project;

import java.util.Date;

public class LogRecord {
	private HostField host;
	private Date timestamp;
	private String request;
	private HttpMethod httpMethod;
	private int replyCode;
	private String httpVersion;
	private String replyBytes;

	/*
	 * @Override public String toString() { return host.toString() + " - - [" +
	 * timestamp + "] \"" + httpMethod + " " + request + " " + "HTTP/" +
	 * httpVersion + "\" " + replyCode + " " + replyBytes; }
	 */
	public HostField getHost() {
		return host;
	}

	public void setHost(HostField host) {
		this.host = host;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getReplyBytes() {
		return replyBytes;
	}

	public void setReplyBytes(String replyBytes) {
		this.replyBytes = replyBytes;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public int getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(int replyCode) {
		this.replyCode = replyCode;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

}
