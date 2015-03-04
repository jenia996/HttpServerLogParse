package grsu.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogPattern {

	private static Pattern hostPattern;
	private static Pattern timestampPattern;
	private static Pattern request;
	private static Pattern reply;
	private static Pattern replyCode;
	private static Pattern replyBytes;

	public static class LogPatternHolder {
		public static final LogPattern HOLDER_INSTANCE = new LogPattern();
	}

	public static LogPattern getInstance() {
		return LogPatternHolder.HOLDER_INSTANCE;
	}

	private LogPattern() {
		LogPattern.hostPattern = Pattern.compile("^[\\w\\W]*?\\s");
		LogPattern.timestampPattern = Pattern
				.compile("\\d+\\/\\w+\\/\\d+:\\d+:\\d+:\\d+ -\\d+");
		LogPattern.request = Pattern
				.compile("(HEAD|GET|POST) +[\\w\\s\\W]*?( |\")(HTTP\\/\\d.\\d)?");
		LogPattern.reply = Pattern.compile("\\d+\\ (\\d+|-)$");
		LogPattern.replyCode = Pattern.compile("^\\d+");
		LogPattern.replyBytes = Pattern.compile("(\\d+|-)$");
	}

	public String DetermineHost(String logLine, int lineNumber)
			throws IllegalStateException {
		try {
			Matcher hostMatcher = hostPattern.matcher(logLine);
			hostMatcher.find();
			return hostMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the hosts in file and used patterns"
					+ lineNumber);
		}
		return "Host not found";
	}

	public String DetermineTimestamp(String logLine, int lineNumber)
			throws IllegalStateException {
		try {
			Matcher timeStampMatch = timestampPattern.matcher(logLine);
			timeStampMatch.find();
			return timeStampMatch.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the timestamp in file and used patterns"
					+ lineNumber);
		}
		return "Timestamp not found";
	}

	public String DetermineRequest(String logLine, int lineNumber)
			throws IllegalStateException {
		try {
			Matcher requestMatcher = request.matcher(logLine);
			requestMatcher.find();
			return requestMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the request in file and used patterns"
					+ lineNumber);
		}
		return "Request not found";
	}

	private String DetermineReply(String logLine, int lineNumber)
			throws IllegalStateException {
		Matcher replyMatcher = reply.matcher(logLine);
		replyMatcher.find();
		return replyMatcher.group();
	}

	public String DetermineReplyCode(String logLine, int lineNumber)
			throws IllegalStateException {
		try {
			Matcher replyCodeMatcher = replyCode.matcher(DetermineReply(
					logLine, lineNumber));
			replyCodeMatcher.find();
			return replyCodeMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the replyCode in file and used patterns"
					+ lineNumber);
		}
		return "Reply Code not found";
	}

	public String DetermineReplyBytes(String logLine, int lineNumber)
			throws IllegalStateException {
		try {
			Matcher replyBytesMatcher = replyBytes.matcher(DetermineReply(
					logLine, lineNumber));
			replyBytesMatcher.find();
			return replyBytesMatcher.group();
		} catch (IllegalStateException e) {
			System.out.println("Check the replyBytes in file and used patterns"
					+ lineNumber);
		}
		return "Reply Bytes not found";
	}
}
