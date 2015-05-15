package grsu.project.report;

import grsu.project.report.impl.factory.HttpMethodReportFactory;
import grsu.project.report.impl.factory.RequestReportFactory;
import grsu.project.report.impl.factory.SentBytesReportFactory;

import java.util.ArrayList;
import java.util.List;

public class ReportListCreator implements ReportList{

	public List<Report> createReportList(List<Integer> params) {
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

}
