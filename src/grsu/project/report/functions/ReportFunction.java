package grsu.project.report.functions;

import grsu.project.data.LogRecord;

@FunctionalInterface
public interface ReportFunction<T> {
	T create(LogRecord logRecord);
}
