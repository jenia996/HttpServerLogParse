package grsu.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.ListIterator;

public abstract class LogReader {
	public static void readAndWriteLines(InputData input)
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

	public static LinkedList<String> ReadLog(InputData input) {
		LinkedList<String> logRecords = new LinkedList<String>();
		ListIterator<String> iterator = logRecords.listIterator();
		try {
			LineNumberReader in = new LineNumberReader(new FileReader(
					input.getFilePath()));
			String logRecord;
			int readedLines = 0;
			while (in.ready()) {
				if (readedLines == input.getStartLine()) {
					int writedLines = 0;
					while (in.ready() && writedLines < input.getLinesToWrite()) {
						logRecord = (in.readLine());
						iterator.add(logRecord);
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

	public static LinkedList<LogRecord> ReadAndParseLog(InputData input) {
		LinkedList<LogRecord> logRecords = new LinkedList<LogRecord>();
		ListIterator<LogRecord> iterator = logRecords.listIterator();
		try {
			LineNumberReader in = new LineNumberReader(new FileReader(
					input.getFilePath()));
			String logRecord;
			int readedLines = 0;
			while (in.ready()) {
				if (readedLines == input.getStartLine()) {
					int writedLines = 0;
					while (in.ready() && writedLines < input.getLinesToWrite()) {
						logRecord = (in.readLine());
						iterator.add(LogParser.parseLine(logRecord));
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
