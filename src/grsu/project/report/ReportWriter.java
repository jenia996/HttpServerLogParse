package grsu.project.report;

import grsu.project.data.HttpMethod;

import java.util.Map.Entry;

public class ReportWriter {

	public static void writeReportInfo(Report report,
			ReportInputParameters params) {
		if (params.getParams().contains(1)) {
			Entry<String, Integer> request = report.getMostWantedRequest();
			System.out.println(request.getKey() + " " + request.getValue());
		}
		if (params.getParams().contains(2)) {
			long methodCount = report.getHttpMethodCount();
			for (Entry<HttpMethod, Long> entry : report.getMostUsedMethod()
					.entrySet()) {
				float percent = entry.getValue() * 1.0f / methodCount * 100;
				System.out.print(entry.getKey() + " ");
				System.out.printf("%f", percent);
				System.out.println("%");
			}
		}
		if (params.getParams().contains(3)) {
			System.out.println(report.getTransferedBytes());
		}
	}
}
