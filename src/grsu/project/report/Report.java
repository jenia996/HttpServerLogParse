package grsu.project.report;

import grsu.project.data.HttpMethod;
import grsu.project.report.functions.HttpMethodGetter;
import grsu.project.report.functions.RequestGetter;
import grsu.project.report.functions.TransferedBytesGetter;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Report implements Visitor {

	private Date startDate;
	private Map<String, Integer> mostWanted = new HashMap<>();
	private Map<HttpMethod, Long> mostUsedMethod = new HashMap<>();
	private int requestInReport;
	private long transferedBytes;
	private Date endDateDate;

	public long getTransferedBytes() {
		return transferedBytes;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDateDate() {
		return endDateDate;
	}

	public int getRequestInReport() {
		return requestInReport;
	}

	@Override
	public void visit(RequestGetter key) {
		if (mostWanted.containsKey(key.getRequestKey())) {
			mostWanted.put(key.getRequestKey(),
					mostWanted.get(key.getRequestKey()) + 1);
		} else {
			mostWanted.put(key.getRequestKey(), 1);
		}
	}

	@Override
	public void visit(TransferedBytesGetter key) {
		this.transferedBytes += key.getCount();
	}

	@Override
	public void visit(HttpMethodGetter key) {
		if (mostUsedMethod.containsKey(key.getMethod())) {
			mostUsedMethod.put(key.getMethod(),
					mostUsedMethod.get(key.getMethod()) + 1);
		} else {
			mostUsedMethod.put(key.getMethod(), 1l);
		}

	}

	public long getHttpMethodCount() {
		Collection<Long> values = mostUsedMethod.values();
		long summ = 0;
		for (Long x : values) {
			summ += x;
		}
		return summ;
	}

	public Map<HttpMethod, Long> getMostUsedMethod() {
		return mostUsedMethod;
	}

	public Entry<String, Integer> getMostWantedRequest() {
		Entry<String, Integer> max = new AbstractMap.SimpleEntry<String, Integer>(
				null, 0);
		for (Entry<String, Integer> entry : mostWanted.entrySet()) {
			if (entry.getValue() > max.getValue()) {
				max = entry;
			}
		}
		return max;
	}

}
