package grsu.project;

import grsu.project.data.InputParameters;
import grsu.project.parsers.InputParametersParser;
import grsu.project.parsers.ReportInputParametersParser;
import grsu.project.report.Report;
import grsu.project.report.ReportCreator;
import grsu.project.report.ReportInputParameters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class LogFileAnalizer {
	@FunctionalInterface
	interface Converter<F, T> {
		T convert(F from);
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		try {
			InputParameters params = InputParametersParser.parse(args[0],
					args[1], args[2]);
			if (params != null) {
				BufferedReader input = new BufferedReader(
						new InputStreamReader(System.in));
				// LineNumberReader reader = new LineNumberReader(new
				// FileReader(
				// params.getFilePath()));
				/*
				 * String line;
				 * 
				 * while (reader.ready()) { if (reader.getLineNumber() ==
				 * params.getStartLine()) { reader.setLineNumber(0); while
				 * ((line = reader.readLine()) != null && reader.getLineNumber()
				 * < params .getLinesToWrite()) { LogRecord logRecord =
				 * LogRecordParser.parse(line);
				 * System.out.println(LogRecordWriter
				 * .logRecordToString(logRecord)); } break; }
				 * 
				 * reader.readLine(); } reader.close();
				 * System.out.println(LogRecordParser.getTimestampFormat());
				 * System.out.println("Ended");
				 */
				do {
					System.out.println("Enter Start Date");
					String startDate = input.readLine();
					System.out.println("Enter End Date");
					String endDate = input.readLine();
					System.out.println("Enter functions Numbers");
					String functions = input.readLine();
					ReportInputParameters parameters = ReportInputParametersParser
							.parse(startDate, endDate, functions);
					System.out.println(parameters.toString());
					System.out
							.println("Do you want to continue with this params (Y,N)?");
					String agree = input.readLine().toLowerCase();
					if (agree.equals("y")) {
						ReportCreator creator = new ReportCreator();
						Report report = creator.createReport(
								parameters.getStartDate(),
								parameters.getEndDate(),
								parameters.getParams(), params);
						if (parameters.getParams().contains(3)) {
							System.out.print(report.getTransferedBytes());
						}
					}
				} while (true);
			} else {
				System.out.println("Check input parameters");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Check input parameters");
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!");
		}

	}
}
