package grsu.project.parsers;

import grsu.project.report.ReportInputParameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class ReportInputParametersParser {
	final static Logger logger = Logger
			.getLogger(ReportInputParametersParser.class);

	public static ReportInputParameters inputParams() {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println("Enter Start Date");
			String startDate = input.readLine();
			System.out.println("Enter End Date");
			String endDate = input.readLine();
			System.out.println("Enter functions Numbers");
			String functions = input.readLine();
			return parse(startDate, endDate, functions);
		} catch (IOException e) {
			System.out.println("Wrong Parameters!");
			return null;
		}
	}

	private static ReportInputParameters parse(String startDate,
			String endDate, String functionParams) {
		ReportInputParameters params = new ReportInputParameters();
		Date startDateTime = TimestampParser.parse(startDate);
		Date endDateTime = TimestampParser.parse(endDate);
		if (startDateTime == null || endDateTime == null
				|| startDateTime.before(endDateTime)) {
			params.setStartDate(startDateTime);
			params.setEndDate(endDateTime);
			String[] functions = functionParams.split(" ");
			List<Integer> tempListFunctions = new ArrayList<Integer>();
			try {
				for (String param : functions) {
					tempListFunctions.add(Integer.parseInt(param));
				}
			} catch (NumberFormatException e) {
				logger.error("Invalid function params");
				return null;
			}
			params.setParams(tempListFunctions);
			return params;
		} else {
			System.out.println("Start Date must be before end Date");
			return null;
		}
	}
}
