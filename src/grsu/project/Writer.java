package grsu.project;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Writer {

	public static void writeToFile(LinkedList<LogRecord> logRecords, String path)
			throws FileNotFoundException, IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			ListIterator<LogRecord> iterator = logRecords.listIterator();
			while (iterator.hasNext()) {
				writer.write(iterator.next().toString() + '\n');
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Not such directory");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void WriteLines(InputParameters input)
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


}
