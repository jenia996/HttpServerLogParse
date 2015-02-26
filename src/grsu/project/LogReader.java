package grsu.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

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
}
