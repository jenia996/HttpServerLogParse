package grsu.project;

import java.util.LinkedList;
import java.util.ListIterator;

public class LogParser {

	public static final String splitPattern = " - - \\[|\\] \"|\" | (?=\\d)| (?=-$)";

	public static LogRecord parseLine(String line) {
		String[] tokens = line.split(splitPattern);
		try {
			LogRecord logRecord = new LogRecord(tokens[0], tokens[1],
					tokens[2], tokens[3], tokens[4]);
			return logRecord;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error");
		}
		return null;
	}

	public static LinkedList<LogRecord> parse(LinkedList<String> lines) {
		LinkedList<LogRecord> logRecords = new LinkedList<LogRecord>();
		ListIterator<String> iterator = lines.listIterator();
		while (iterator.hasNext()) {
			logRecords.add(parseLine(iterator.next()));
		}
		return logRecords;
	}

}
