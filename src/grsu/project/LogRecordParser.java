package grsu.project;

public class LogRecordParser {

	public static final String splitPattern = " - - \\[|\\] \"|\" | (?=\\d)| (?=-$)";

	/*
	 * Configuration for logRecord { %H - all hosts, %Hi - host represented by
	 * ip, %Hw all hosts without ip hosts %T - Time %Rq - request %Rs - all
	 * response, %RsC - response code, %RsB - response bytes
	 * 
	 * Pattern for %Hi
	 * 
	 * 
	 * 
	 * }
	 */
	public static LogRecord parseLine(String line) {
		String[] tokens = line.split(splitPattern);
		LogRecord logRecord = new LogRecord();
		try {
			logRecord.setHost(tokens[0]);
			logRecord.setTimestamp(tokens[1]);
			logRecord.setRequest(tokens[2]);
			logRecord.setReplyCode(tokens[3]);
			logRecord.setReplyBytes(tokens[4]);
			return logRecord;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static LogRecord parseLine(String line,String config)
	{
		LogRecord logRecord = new LogRecord();
		//String temp =
		//System.out.println(temp.indexOf("%", 1));
		
		
		
		
		return logRecord;
	}

}
