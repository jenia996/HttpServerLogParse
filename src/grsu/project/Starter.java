package grsu.project;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Starter {
	static long startTime;
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ArrayIndexOutOfBoundsException, NumberFormatException {

		BufferedWriter a = new BufferedWriter(new FileWriter("output.txt"));
		InputData input = new InputData(args);
		// LogReader.writeLines(input);
		ArrayList<LogRecord> logRecords = new ArrayList<LogRecord>();
		logRecords = LogReader.ReadLog(input);
		for (LogRecord logRecord : logRecords) {
			a.write(logRecord.toString()+'\n');
		}
		a.close();
		System.out.println("End");
	}
}
