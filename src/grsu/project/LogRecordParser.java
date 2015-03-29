package grsu.project;

public class LogRecordParser {

	public static final String splitPattern = " - - \\[|\\] \"|\" | (?=\\d+$)| (?=-$)";

	/*
	 * Configuration for logRecord { %H - all hosts, %Hi - host represented by
	 * ip, %Hw all hosts without ip hosts %T - Time %Rq - request %Rs - all
	 * response, %RsC - response code, %RsB - response bytes }
	 */
	public static LogRecord parse(String line) {
		String[] tokens = line.split(splitPattern);
		LogRecord logRecord = new LogRecord();
		try {
			logRecord.setHost(HostFieldParser.parse(tokens[0]));
			logRecord.setTimestamp(TimestampParser.parse(tokens[1]));
			logRecord = fillRequest(logRecord, tokens[2]);
			logRecord.setReplyCode(Integer.parseInt(tokens[3]));
			logRecord.setReplyBytes(tokens[4]);
			return logRecord;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		} catch (NumberFormatException e) {
			System.out.println("Error while parsing reply code");
			return null;
		}

	}

	private static LogRecord fillRequest(LogRecord logRecord, String request) {
		String[] tokens = request.split(" ");
		logRecord.setHttpMethod(HttpMethodParser.parse(tokens[0]));
		logRecord.setRequest(tokens[1]);
		logRecord.setHttpVersion(tokens[2].split("/")[1]);
		return logRecord;
	}

}
