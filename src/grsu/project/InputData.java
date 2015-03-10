package grsu.project;

public class InputData {
	private int startLine;
	private int linesToWrite;
	private String filePath;

	public InputData(String filePath, int startLine, int linesToWrite) {
		this.filePath = filePath;
		this.startLine = startLine;
		this.linesToWrite = linesToWrite;
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
