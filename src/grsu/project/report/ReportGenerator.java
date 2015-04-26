package grsu.project.report;

import grsu.project.data.InputParameters;
import grsu.project.data.LogRecord;
import grsu.project.parsers.LogRecordParser;
import grsu.project.reader.Monitor;
import grsu.project.reader.Reader;
import grsu.project.report.impl.factory.HttpMethodReportFactory;
import grsu.project.report.impl.factory.RequestReportFactory;
import grsu.project.report.impl.factory.SentBytesReportFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportGenerator {
	private static Reader reader;
	private static Monitor monitor = new Monitor();

	public void waitForReader(Long timeout) {
		try {
			synchronized (monitor) {
				monitor.wait(timeout);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Report> generateReport(ReportInputParameters params,
			InputParameters parameters) throws IOException {
		reader = new Reader();
		LineNumberReader fileReader = new LineNumberReader(new FileReader(
				parameters.getFilePath()));
		reader.setReader(fileReader);
		reader.start();
		waitForReader(5l);
		List<Report> reports = fillReportList(params.getParams());
		return generateReport(reports, params.getStartDate(),
				params.getEndDate(), params.getParams());
	}

	private List<Report> generateReport(List<Report> reports, Date startDate,
			Date endDate, List<Integer> params) throws IOException {
		Date t = new Date();
		int i = 0;
		do {
			if (!reader.isEmpty()) {
				LogRecord logRecord = LogRecordParser.parse(reader.getLine());
				if (DateChecker.checkDate(startDate, endDate, logRecord)) {
					while (DateChecker.checkDate(startDate, endDate, logRecord)) {
						if (logRecord != null) {
							for (Report x : reports) {
								x.addInfo(logRecord);
							}
							i++;
						}
						if (!reader.isEmpty()) {
							logRecord = LogRecordParser.parse(reader.getLine());
							if (reader.isWaiting() && reader.isAlmostEmpty()) {
								synchronized (reader.getMonitor()) {
									System.out
											.println("Main thread Notify second thread " + i);
									reader.getMonitor().notify();
									reader.setWaiting(false);
								}
							}
						} else {
							if (reader.isFullRead()) {
								reader.finish();
								break;
							} else {
								System.out.println("Main sleep ");
							}
						}
					}
					reader.finish();
					break;
				}
			} else {
				monitor.setWaiting(true);
				System.out.println("Main Sleep");
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} while (true);
		System.out.println((new Date()).getTime() - t.getTime());
		return reports;
	}

	public static Monitor getMonitor() {
		return monitor;
	}

	private List<Report> fillReportList(List<Integer> params) {
		List<Report> reports = new ArrayList<Report>();
		if (params.contains(1)) {
			reports.add((new HttpMethodReportFactory()).createReport());
		}
		if (params.contains(2)) {
			reports.add((new RequestReportFactory()).createReport());
		}
		if (params.contains(3)) {
			reports.add((new SentBytesReportFactory()).createReport());
		}

		return reports;
	}

	public static boolean isWaiting() {
		return monitor.isWaiting();
	}

	public static void setWaiting(boolean isWaiting) {
		monitor.setWaiting(isWaiting);
	}
}
