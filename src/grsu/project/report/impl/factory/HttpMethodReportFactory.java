package grsu.project.report.impl.factory;

import grsu.project.report.ReportFactory;
import grsu.project.report.impl.report.HttpMethodReport;

public class HttpMethodReportFactory implements ReportFactory {

	public HttpMethodReport createReport() {
		return new HttpMethodReport();
	}

}
