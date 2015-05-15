package grsu.project;

import grsu.project.data.*;

abstract public class LogRecordWriter {

	private static TimestampConfiguration timestampConfiguration = new TimestampConfiguration();

	public static String logRecordToString(LogRecord logRecord) {
		try {
			StringBuilder toString = new StringBuilder();
			toString.append(logRecord.getHost().toString() + " - - ");
			toString.append("["
					+ timestampConfiguration.getTimestampFormat().format(
							logRecord.getDate()) + "] ");
			toString.append("\"" + logRecord.getHttpMethod() + " ");
			toString.append(logRecord.getRequest() + " ");
			toString.append("HTTP/" + logRecord.getHttpVersion() + "\" ");
			toString.append(logRecord.getReplyCode() + " ");
			toString.append(logRecord.getReplyBytes());
			return toString.toString();
		} catch (NullPointerException e) {
			return "Error";
		}

	}

	public static void setTimestampFormat(String timestampFormat) {
		timestampConfiguration.setTimestampFormat(timestampFormat);
	}
}
