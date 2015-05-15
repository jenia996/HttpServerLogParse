package grsu.project.report.impl.report;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import grsu.project.data.LogRecord;
import grsu.project.report.Report;

public class RequestReport implements Report {
	private Map<String, Integer> mostWanted = new HashMap<String, Integer>();

	public void addInfo(LogRecord logRecord) {
		if (mostWanted.containsKey(logRecord.getRequest())) {
			mostWanted.put(logRecord.getRequest(),
					mostWanted.get(logRecord.getRequest()) + 1);
		} else {
			mostWanted.put(logRecord.getRequest(), 1);
		}

	}

	private Entry<String, Integer> getMostWantedRequest() {
		Entry<String, Integer> max = new AbstractMap.SimpleEntry<String, Integer>(
				null, 0);
		for (Entry<String, Integer> entry : mostWanted.entrySet()) {
			if (entry.getValue() > max.getValue()) {
				max = entry;
			}
		}
		return max;
	}

	public String writeInfo() {
		Entry<String, Integer> max = getMostWantedRequest();
		return max.getKey() + " " + max.getValue() + '\n';
	}

}
