package grsu.project.report;

import java.util.List;

public class ReportWriter {

	public static void writeReportInfo(List<Report> reports) {
		for (Report x : reports) {
			System.out.print(x.writeInfo());
		}
	}
}
