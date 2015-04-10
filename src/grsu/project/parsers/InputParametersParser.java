package grsu.project.parsers;

import grsu.project.data.InputParameters;

public abstract class InputParametersParser {
	public static InputParameters parse(String filePath, String startLine,
			String linesToWrite) {
		InputParameters params = new InputParameters();
		try {
			params.setFilePath(filePath);
			params.setStartLine(Integer.parseInt(startLine));
			params.setLinesToWrite(Integer.parseInt(linesToWrite));
			return params;
		} catch (NumberFormatException e) {
			return null;
		}

	}
}
