package grsu.project.report.impl.factory;

import grsu.project.report.ReportFactory;
import grsu.project.report.impl.report.RequestReport;

public class RequestReportFactory implements ReportFactory {

	@Override
	public RequestReport createReport() {
		return new RequestReport();
	}

}
