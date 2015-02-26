package grsu.project;

public class InputData {
	private int startLine;
	private int linesToWrite;
	private String filePath;

	public InputData(String[] args) throws ArrayIndexOutOfBoundsException,
			NumberFormatException {
		try {
			this.filePath = args[0];
			this.startLine = Integer.parseInt(args[1]);
			this.linesToWrite = Integer.parseInt(args[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Check the array of input parameters.");
		} catch (NumberFormatException e) {
			System.out
					.println("Check the numbers in array of input parameters");
		}

	}

	public int getStartLine() {
		return startLine;
	}

	public int getLinesToWrite() {
		return linesToWrite;
	}

	public String getFilePath() {
		return filePath;
	}

}
