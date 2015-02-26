package grsu.project;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Starter {
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ArrayIndexOutOfBoundsException, NumberFormatException {

		InputData input = new InputData(args);
		LogReader.writeLines(input);

	}
}
