package grsu.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Starter {
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ArrayIndexOutOfBoundsException, NumberFormatException {

		InputData input = new InputData(args);
		//LogReader.writeLines(input);
		ArrayList<LogRecord> logRecords = new ArrayList<LogRecord>();
		logRecords = LogReader.ReadLog(input);
		for(LogRecord logRecord : logRecords)
		{
			System.out.println(logRecord.toString());
		}

	}
}
