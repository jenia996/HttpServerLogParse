package grsu.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogPattern {

	private Pattern hostPattern;
	private Pattern timestampPattern;
	private Pattern request;
	private Pattern reply;
	private Pattern replyCode;
	private Pattern replyBytes;

	public LogPattern() {
		this.hostPattern = Pattern.compile("^[\\w\\.]*?\\s");
		this.timestampPattern = Pattern.compile("\\[[\\w\\W]*?\\]");
		this.request = Pattern.compile("\\\"+[\\w\\W]*?\\\"");
		this.reply = Pattern.compile("\\d+\\ (\\d+|-)$");
		this.replyCode = Pattern.compile("^\\d+");
		this.replyBytes = Pattern.compile("(\\d+|-)$");
	}

	public String DetermineHost(String logLine) throws IllegalStateException {
		try {
			Matcher hostMatcher = hostPattern.matcher(logLine);
			hostMatcher.find();
			return hostMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the text in file and used patterns");
		}
		return "Host not found";
	}

	public String DetermineTimestamp(String logLine)
			throws IllegalStateException {
		try {
			Matcher timeStampMatch = timestampPattern.matcher(logLine);
			timeStampMatch.find();
			return timeStampMatch.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the text in file and used patterns");
		}
		return "Timestamp not found";
	}

	public String DetermineRequest(String logLine) throws IllegalStateException {
		try {
			Matcher requestMatcher = request.matcher(logLine);
			requestMatcher.find();
			return requestMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the text in file and used patterns");
		}
		return "Request not found";
	}

	private String DetermineReply(String logLine) throws IllegalStateException {
		Matcher replyMatcher = reply.matcher(logLine);
		replyMatcher.find();
		return replyMatcher.group();
	}

	public String DetermineReplyCode(String logLine)
			throws IllegalStateException {
		try {
			Matcher replyCodeMatcher = replyCode
					.matcher(DetermineReply(logLine));
			replyCodeMatcher.find();
			return replyCodeMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the text in file and used patterns");
		}
		return "Reply Code not found";
	}

	public String DetermineReplyBytes(String logLine)
			throws IllegalStateException {
		try {
			Matcher replyBytesMatcher = replyBytes
					.matcher(DetermineReply(logLine));
			replyBytesMatcher.find();
			return replyBytesMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the text in file and used patterns");
		}
		return "Reply Bytes not found";
	}
}
