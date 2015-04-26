package grsu.project.report.impl.factory;

import grsu.project.report.ReportFactory;
import grsu.project.report.impl.report.SentBytesReport;



public class SentBytesReportFactory implements ReportFactory {

	@Override
	public SentBytesReport createReport() {
		return new SentBytesReport();
	}

}