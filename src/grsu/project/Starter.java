package grsu.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Starter {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		InputParameters params = InputParameters.parse(args);
		if (params != null) {
			try {
				String line;
				LineNumberReader reader = new LineNumberReader(new FileReader(
						params.getFilePath()));
				while (reader.ready()) {
					if (reader.getLineNumber() == params.getStartLine()) {
						reader.setLineNumber(0);
						while ((line = reader.readLine()) != null
								&& reader.getLineNumber() < params.getLinesToWrite()) {
							LogRecord logRecord = LogRecordParser.parseLine(line);
							System.out.println(logRecord.toString());
						}
						break;
					}
					reader.readLine();
				}
				reader.close();
				System.out.println("Ended");
			} catch (FileNotFoundException e) {
				System.out.println("File not Found!");
			}
		} else {
			System.out.println("Check input parameters");
		}
	}
}
