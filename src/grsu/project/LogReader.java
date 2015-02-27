package grsu.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public abstract class LogReader {
	public static void writeLines(InputData input)
			throws FileNotFoundException, IOException {
		try {
			LineNumberReader in = new LineNumberReader(new FileReader(
					input.getFilePath()));
			int readedLines = 0;
			while (in.ready()) {
				if (readedLines == input.getStartLine()) {
					int writedLines = 0;
					while (in.ready() && writedLines < input.getLinesToWrite()) {
						System.out.println(in.readLine());
						writedLines++;
					}
					break;
				}
				in.readLine();
				readedLines++;

			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<LogRecord> ReadLog(InputData input) {
		ArrayList<LogRecord> logRecords = new ArrayList<LogRecord>();
		try {
			LineNumberReader in = new LineNumberReader(new FileReader(
					input.getFilePath()));
			int readedLines = 0;
			while (in.ready()) {
				if (readedLines == input.getStartLine()) {
					int writedLines = 0;
					while (in.ready() && writedLines < input.getLinesToWrite()) {
						LogRecord logRecord = new LogRecord(in.readLine());
						logRecords.add(logRecord);
						writedLines++;
					}
					break;
				}
				in.readLine();
				readedLines++;

			}
			in.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return logRecords;
	}
}
