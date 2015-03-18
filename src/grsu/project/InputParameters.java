package grsu.project;

public class InputParameters {
	private int startLine;
	private int linesToWrite;
	private String filePath;

	public static InputParameters parse(String[] parameters) {
		InputParameters params = new InputParameters();
		try {
			params.setFilePath(parameters[0]);
			params.setStartLine(Integer.parseInt(parameters[1]));
			params.setLinesToWrite(Integer.parseInt(parameters[2]));
			return params;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		} catch (NumberFormatException e) {
			return null;
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

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public void setLinesToWrite(int linesToWrite) {
		this.linesToWrite = linesToWrite;
	}
	

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
