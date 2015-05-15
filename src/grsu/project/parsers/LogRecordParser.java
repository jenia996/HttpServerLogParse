package grsu.project.parsers;

import grsu.project.data.LogRecord;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class LogRecordParser {

	private static final String splitPattern = "\\[|\\]";
	final static Logger logger = Logger.getLogger(LogRecordParser.class);

	public static LogRecord parse(String line) {
		String[] tokens = line.split(splitPattern);
		LogRecord logRecord = new LogRecord();
		int requestStartIndex, requestEndIndex;
		try {
			if (tokens.length > 1) {
				logRecord.setHost(HostFieldParser.parse(tokens[0].substring(0,
						tokens[0].indexOf(" "))));
				logRecord.setDate(TimestampParser.parse(tokens[1]));
				requestStartIndex = tokens[2].indexOf("\"") + 1;
				requestEndIndex = tokens[2].lastIndexOf("\"");
				fillRequestData(logRecord, tokens[2].substring(requestEndIndex));
				fillRequest(logRecord,
						tokens[2].substring(requestStartIndex, requestEndIndex));
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			logger.error("Number " + line);
			return null;
		} catch (IllegalArgumentException e) {
			logger.error("Argument " + line);
			return null;
		} catch (IndexOutOfBoundsException e) {
			logger.error("IndexOutOf " + line);
			return null;
		}
		return logRecord;
	}

	private static LogRecord fillRequestData(LogRecord logRecord,
			String requestData) {
		StringTokenizer tokens = new StringTokenizer(requestData, "\" ");
		logRecord.setReplyCode(Integer.parseInt(tokens.nextToken()));
		logRecord.setReplyBytes(getReplyBytes(tokens.nextToken()));
		return logRecord;
	}

	private static LogRecord fillRequest(LogRecord logRecord, String request) {
		String[] tokens = request.split(" |\"");
		logRecord.setHttpMethod(HttpMethodParser.parse(tokens[0]));
		logRecord.setRequest(tokens[1]);
		if (tokens.length > 2) {
			logRecord.setHttpVersion(HttpVersionParser.parse(tokens[2]));
		} else {
			logRecord.setHttpVersion("1.0");
		}

		return logRecord;
	}

	private static int getReplyBytes(String replyBytes) {
		if (replyBytes.equals("-")) {
			return 0;
		}
		return Integer.parseInt(replyBytes);
	}

}
