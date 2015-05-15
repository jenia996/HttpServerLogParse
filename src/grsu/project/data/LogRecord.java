package grsu.project.data;

import java.util.Date;

public class LogRecord {

	private HostField host;
	private Date date;
	private String request;
	private HttpMethod httpMethod;
	private int replyCode;
	private String httpVersion;
	private int replyBytes;

	public HostField getHost() {
		return host;
	}

	public void setHost(HostField host) {
		this.host = host;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return host + " " + date + " " + " " + httpMethod + " "
				+ replyCode + " " + httpVersion + " " + replyBytes;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getReplyBytes() {
		return replyBytes;
	}

	public void setReplyBytes(int replyBytes) {
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
