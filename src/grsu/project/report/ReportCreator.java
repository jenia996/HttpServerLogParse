package grsu.project.report;

import grsu.project.Buffer;
import grsu.project.data.InputParameters;
import grsu.project.data.LogRecord;
import grsu.project.parsers.LogRecordParser;
import grsu.project.report.functions.AbstractGetter;
import grsu.project.report.functions.ReportFunction;
import grsu.project.report.functions.HttpMethodGetter;
import grsu.project.report.functions.RequestGetter;
import grsu.project.report.functions.TransferedBytesGetter;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportCreator<T extends AbstractGetter> {

	@SuppressWarnings("unchecked")
	private static <T extends AbstractGetter> T cast(final Object object) {
		return (T) object;
	}

	private static Buffer buffer;
	Date t = new Date();

	@SuppressWarnings({ "rawtypes" })
	public Report createReport(Date startDate, Date endDate,
			List<Integer> functionsNumbers, InputParameters parameters)
			throws IOException, InterruptedException {
		Report report = new Report();
		Visitor visitor = report;
		buffer = new Buffer();
		buffer.setReader(new LineNumberReader(new FileReader(parameters
				.getFilePath())));
		buffer.setBufferSize(4000);
		buffer.start();
		List<ReportFunction> functions = checkFunctions(functionsNumbers);
		do {
			if (!buffer.isEmpty()) {

				LogRecord logRecord = LogRecordParser.parse(buffer.getLine());
				if (DateChecker.checkDate(startDate, endDate, logRecord)) {

					while (DateChecker.checkDate(startDate, endDate, logRecord)) {
						if (logRecord != null) {
							for (ReportFunction<?> x : functions) {
								T t = cast(x.create(logRecord));
								t.accept(visitor);
							}
						}
						if (!buffer.isEmpty()) {
							logRecord = LogRecordParser.parse(buffer.getLine());
						} else {
							if (buffer.isFullRead()) {
								buffer.finish();
								break;
							} else {
								Thread.sleep(10);
							}

						}

					}
					buffer.finish();
					break;
				}

			} else {
			//	System.out.println("Main Sleep");
				Thread.sleep(10);
			}

		} while (true);
		return report;
	}

	@SuppressWarnings({ "rawtypes" })
	private List<ReportFunction> checkFunctions(List<Integer> functionsNumbers) {
		List<ReportFunction> functions = new ArrayList<>();
		if (functionsNumbers.contains(1)) {
			RequestGetter requsetgetter = new RequestGetter();
			ReportFunction<RequestGetter> function = requsetgetter::create;
			functions.add(function);
		}
		if (functionsNumbers.contains(2)) {
			HttpMethodGetter httpGetter = new HttpMethodGetter();
			ReportFunction<HttpMethodGetter> function = httpGetter::create;
			functions.add(function);
		}
		if (functionsNumbers.contains(3)) {
			TransferedBytesGetter transferefBytesGetter = new TransferedBytesGetter();
			ReportFunction<TransferedBytesGetter> function = transferefBytesGetter::create;
			functions.add(function);
		}
		return functions;
	}

}
