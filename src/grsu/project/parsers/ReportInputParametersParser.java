package grsu.project.parsers;

import grsu.project.report.ReportInputParameters;

import java.util.ArrayList;
import java.util.List;

public class ReportInputParametersParser {
	private static TimestampParser timestampParser = new TimestampParser();

	public static ReportInputParameters parse(String startDate, String endDate,
			String functionParams) {
		ReportInputParameters params = new ReportInputParameters();
		params.setStartDate(timestampParser.parse(startDate));
		params.setEndDate(timestampParser.parse(endDate));
		String[] functions = functionParams.split(" ");
		List<Integer> tempListFunctions = new ArrayList<Integer>();
		for (String param : functions) {
			tempListFunctions.add(Integer.parseInt(param));
		}
		params.setParams(tempListFunctions);
		return params;
	}
}
