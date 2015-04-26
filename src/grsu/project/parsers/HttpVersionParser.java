package grsu.project.parsers;

public class HttpVersionParser {

	public static String parse(String line) {
		return line.substring(line.indexOf("/") + 1);
	}
}
