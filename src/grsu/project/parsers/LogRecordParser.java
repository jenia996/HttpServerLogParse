package grsu.project.parsers;

import grsu.project.HttpVersionException;
import grsu.project.data.LogRecord;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class LogRecordParser {

	private static final String splitPattern = "\\[|\\]";
	private static TimestampParser timestampParser = new TimestampParser();
	final static Logger logger = Logger.getLogger(LogRecordParser.class);

	public static LogRecord parse(String line) {
		String[] tokens = line.split(splitPattern);
		LogRecord logRecord = new LogRecord();
		int requestStartIndex, requestEndIndex;
		try {
			logRecord.setHost(HostFieldParser.parse(tokens[0].substring(0,
					tokens[0].indexOf(" "))));
			logRecord.setTimestamp(timestampParser.parse(tokens[1]));
			requestStartIndex = tokens[2].indexOf("\"") + 1;
			requestEndIndex = tokens[2].lastIndexOf("\"");
			fillRequestData(logRecord, tokens[2].substring(requestEndIndex));
			fillRequest(logRecord,
					tokens[2].substring(requestStartIndex, requestEndIndex));
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("Error while parsing " + line);
			return null;
		} catch (NumberFormatException e) {
			logger.error("Error while parsing requestData " + line);
			return null;
		} catch (IllegalArgumentException e) {
			logger.error("Error while parsing HttpMethod" + line);
			return null;
		} catch (HttpVersionException e) {
			logger.warn("Missing HTTP Version " + line);
			logRecord.setHttpVersion("1.0");
			return logRecord;
		} catch (IndexOutOfBoundsException e) {
			logger.error("Error while parsing " + line);
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

	private static LogRecord fillRequest(LogRecord logRecord, String request)
			throws HttpVersionException {
		String[] tokens = request.split(" |\"");
		logRecord.setHttpMethod(HttpMethodParser.parse(tokens[0]));
		logRecord.setRequest(tokens[1]);
		try {
			logRecord.setHttpVersion(HttpVersionParser.parse(tokens[2]));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new HttpVersionException(e);
		}

		return logRecord;
	}

	private static int getReplyBytes(String replyBytes) {
		if (replyBytes.equals("-")) {
			return 0;
		}
		return Integer.parseInt(replyBytes);
	}

	public static String getTimestampFormat() {
		return timestampParser.getTimestampFormatString();
	}

	public static void setTimestampFormat(String timestampFormat) {
		timestampParser.setTimestampFormat(timestampFormat);
	}

}
