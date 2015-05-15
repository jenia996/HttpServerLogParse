package grsu.project.report;

import grsu.project.SomeClassToWorkWithDataBase;
import grsu.project.data.InputParameters;
import grsu.project.data.LogRecord;
import grsu.project.parsers.LogRecordParser;
import grsu.project.reader.Monitor;
import grsu.project.reader.Reader;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
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
			InputParameters parameters, ReportList reportListCreator)
			throws IOException {
		reader = new Reader();
		LineNumberReader fileReader = new LineNumberReader(new FileReader(
				parameters.getFilePath()));
		reader.setReader(fileReader);
		reader.start();
		waitForReader(200l);
		List<Report> reports = fillReportList(params.getParams(),
				reportListCreator);
		return generateReport(reports, params.getStartDate(),
				params.getEndDate(), params.getParams());
	}

	private List<Report> generateReport(List<Report> reports, Date startDate,
			Date endDate, List<Integer> params) throws IOException {
		Date t = new Date();
		SomeClassToWorkWithDataBase worker = new SomeClassToWorkWithDataBase();
		do {
			if (!reader.isEmpty()) {
				LogRecord logRecord = LogRecordParser.parse(reader.getLine());
				if (DateChecker.checkDate(startDate, endDate, logRecord)) {
					while (DateChecker.checkDate(startDate, endDate, logRecord)) {
						if (logRecord != null) {
							for (Report x : reports) {
								x.addInfo(logRecord);
							}
							worker.append(logRecord);
						}
						if (!reader.isEmpty()) {
							logRecord = LogRecordParser.parse(reader.getLine());
							if (reader.isWaiting() && reader.isAlmostEmpty()) {
								notifyReader();
							}
						} else {
							if (true) {
								System.out.println();
							}
							if (reader.isFullRead()) {
								reader.finish();
								break;
							} else {
								waitReader();
							}
						}
					}
					reader.finish();
					break;
				}
			} else {
				waitReader();
			}

		} while (true);
		System.out.println((new Date()).getTime() - t.getTime());
		return reports;
	}

	private void waitReader() {
		synchronized (monitor) {
			monitor.setWaiting(true);
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void notifyReader() {
		synchronized (reader.getMonitor()) {
			reader.getMonitor().notify();
			reader.setWaiting(false);
		}
	}

	public static Monitor getMonitor() {
		return monitor;
	}

	private List<Report> fillReportList(List<Integer> params,
			ReportList reportListCreator) {
		List<Report> reportList = reportListCreator.createReportList(params);
		return reportList;
	}

	public static boolean isWaiting() {
		return monitor.isWaiting();
	}

	public static void setWaiting(boolean isWaiting) {
		monitor.setWaiting(isWaiting);
	}
}
