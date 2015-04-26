package grsu.project;

import grsu.project.data.InputParameters;
import grsu.project.parsers.InputParametersParser;
import grsu.project.parsers.ReportInputParametersParser;
import grsu.project.report.ReportGenerator;
import grsu.project.report.ReportInputParameters;
import grsu.project.report.ReportWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.PropertyConfigurator;

public class LogFileAnalizer {
	@FunctionalInterface
	interface Converter<F, T> {
		T convert(F from);
	}

	public static void main(String[] args) throws InterruptedException {
		PropertyConfigurator.configure("log4j.properties");
		try {
			InputParameters params = InputParametersParser.parse(args[0],
					args[1], args[2]);
			if (params != null) {
				BufferedReader input = new BufferedReader(
						new InputStreamReader(System.in));
				do {
					ReportInputParameters parameters = ReportInputParametersParser
							.inputParams();
					if (parameters != null) {
						System.out.println(parameters.toString());
						System.out
								.println("Do you want to continue with this params (Y,N)?");
						String agree = input.readLine().toLowerCase();
						if (agree.equals("y")) {
							ReportGenerator generator = new ReportGenerator();
							ReportWriter.writeReportInfo(generator
									.generateReport(parameters, params));
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
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
