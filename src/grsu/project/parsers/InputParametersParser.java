package grsu.project.parsers;

import org.apache.log4j.Logger;

import grsu.project.data.InputParameters;

public abstract class InputParametersParser {
	final static Logger logger = Logger.getLogger(InputParametersParser.class);

	public static InputParameters parse(String filePath, String startLine,
			String linesToWrite) {
		InputParameters params = new InputParameters();
		try {
			params.setFilePath(filePath);
			params.setStartLine(Integer.parseInt(startLine));
			params.setLinesToWrite(Integer.parseInt(linesToWrite));
			return params;
		} catch (NumberFormatException e) {
			logger.error("Invalid input parameters");
			return null;
		}

	}
}
