package grsu.project.report.functions;

import grsu.project.data.LogRecord;

@FunctionalInterface
public interface Function<T> {
	T create(LogRecord logRecord);
}
