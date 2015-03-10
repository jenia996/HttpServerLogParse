package grsu.project;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

}
