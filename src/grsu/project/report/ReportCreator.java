package grsu.project.report;

import grsu.project.DateChecker;
import grsu.project.data.InputParameters;
import grsu.project.data.LogRecord;
import grsu.project.parsers.LogRecordParser;
import grsu.project.report.functions.AbstractTypeKey;
import grsu.project.report.functions.Function;
import grsu.project.report.functions.HttpMethodGetter;
import grsu.project.report.functions.RequestGetter;
import grsu.project.report.functions.TransferedBytesGetter;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportCreator<T extends AbstractTypeKey> {

	@SuppressWarnings("unchecked")
	private static <T extends AbstractTypeKey> T cast(final Object object) {
		return (T) object;
	}

	@SuppressWarnings({ "rawtypes" })
	public Report createReport(Date startDate, Date endDate,
			List<Integer> functionsNumbers, InputParameters parameters)
			throws IOException {
		Report report = new Report();
		List<Function> functions = checkFunctions(functionsNumbers);
		LineNumberReader reader = new LineNumberReader(new FileReader(
				parameters.getFilePath()));
		Visitor visitor = report;
		while (reader.ready()) {
			LogRecord logRecord = LogRecordParser.parse(reader.readLine());
			if (DateChecker.checkDate(startDate, endDate, logRecord)) {
				while (reader.ready()
						&& DateChecker.checkDate(startDate, endDate, logRecord)) {
					if (logRecord != null) {
						for (Function<?> x : functions) {
							T t = cast(x.create(logRecord));
							t.accept(visitor);
						}
					}
					logRecord = LogRecordParser.parse(reader.readLine());
				}
				break;
			}
		}
		reader.close();
		return report;
	}

	@SuppressWarnings({ "rawtypes" })
	private List<Function> checkFunctions(List<Integer> functionsNumbers) {
		List<Function> functions = new ArrayList<>();
		if (functionsNumbers.contains(1)) {
			RequestGetter requsetgetter = new RequestGetter();
			Function<RequestGetter> function = requsetgetter::create;
			functions.add(function);
		}
		if (functionsNumbers.contains(2)) {
			HttpMethodGetter httpGetter = new HttpMethodGetter();
			Function<HttpMethodGetter> function = httpGetter::create;
			functions.add(function);
		}
		if (functionsNumbers.contains(3)) {
			TransferedBytesGetter transferefBytesGetter = new TransferedBytesGetter();
			Function<TransferedBytesGetter> function = transferefBytesGetter::create;
			functions.add(function);
		}
		return functions;
	}

}
