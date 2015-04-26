package grsu.project.report.impl.report;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import grsu.project.data.HttpMethod;
import grsu.project.data.LogRecord;
import grsu.project.report.Report;

public class HttpMethodReport implements Report {
	private Map<HttpMethod, Long> mostUsedMethod = new HashMap<>();

	@Override
	public void addInfo(LogRecord logRecord) {
		if (mostUsedMethod.containsKey(logRecord.getHttpMethod())) {
			mostUsedMethod.put(logRecord.getHttpMethod(),
					mostUsedMethod.get(logRecord.getHttpMethod()) + 1);
		} else {
			mostUsedMethod.put(logRecord.getHttpMethod(), 1l);
		}

	}

	@Override
	public String writeInfo() {
		StringBuilder info = new StringBuilder();
		long methodCount = getHttpMethodCount();
		for (Entry<HttpMethod, Long> entry : mostUsedMethod.entrySet()) {
			float percent = entry.getValue() * 1.0f / methodCount * 100;
			info.append(entry.getKey() + " ");
			info.append(String.format("%f", percent));
			info.append("%");
			info.append('\n');
		}
		return info.toString();
	}

	private long getHttpMethodCount() {
		Collection<Long> values = mostUsedMethod.values();
		long summ = 0;
		for (Long x : values) {
			summ += x;
		}
		return summ;
	}

}
