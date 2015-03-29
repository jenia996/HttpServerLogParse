package grsu.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class LogFileAnalizer {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		try {
			InputParameters params = InputParametersParser.parse(args[0],
					args[1], args[2]);
			if (params != null) {
				String line;
				LineNumberReader reader = new LineNumberReader(new FileReader(
						params.getFilePath()));
				while (reader.ready()) {
					if (reader.getLineNumber() == params.getStartLine()) {
						reader.setLineNumber(0);
						while ((line = reader.readLine()) != null
								&& reader.getLineNumber() < params
										.getLinesToWrite()) {
							LogRecord logRecord = LogRecordParser
									.parse(line);
							System.out.println(logRecord.toString());
							
						}
						break;
					}
					reader.readLine();
				}
				reader.close();
				System.out.println("Ended");

			} else {
				System.out.println("Check input parameters");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Check input parameters");
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!");
		}

	}
}
