package grsu.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Starter {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ArrayIndexOutOfBoundsException, NumberFormatException {
		try {
			InputData input = new InputData(args[0], Integer.parseInt(args[1]),
					Integer.parseInt(args[2]));
			LinkedList<LogRecord> logRecords = new LinkedList<LogRecord>();
			LinkedList<LogRecord> logRecords2 = new LinkedList<LogRecord>();
			logRecords = LogParser.parse((LogReader.ReadLog(input)));
			logRecords2 = LogReader.ReadAndParseLog(input);
			Writer.writeToFile(logRecords, "f:\\Logs\\output.txt");
			Writer.writeToFile(logRecords2, "f:\\Logs\\output2.txt");
			System.out.println("Ended");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Check the array of input parameters.");
		} catch (NumberFormatException e) {
			System.out
					.println("Check the numbers in array of input parameters");
		}
	}
}
