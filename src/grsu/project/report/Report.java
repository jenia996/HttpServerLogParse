package grsu.project.report;

import grsu.project.data.HttpMethod;
import grsu.project.report.functions.HttpMethodGetter;
import grsu.project.report.functions.RequestGetter;
import grsu.project.report.functions.TransferedBytesGetter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Report implements Visitor {

	private Date startDate;
	private Map<String, Integer> mostWanted = new HashMap<>();
	private Map<HttpMethod, Integer> mostUsedMethod = new HashMap<>();
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

	public Map<String, Integer> getMostWanted() {
		return mostWanted;
	}

	public Map<HttpMethod, Integer> getMostUsedMethod() {
		return mostUsedMethod;
	}

	@Override
	public void visit(RequestGetter key) {
		if (mostWanted.containsKey(key.getRequestKey())) {
			for (Entry<?, Integer> e : mostWanted.entrySet()) {
				if (e.getKey().equals(key.getRequestKey())) {
					e.setValue(e.getValue() + 1);
					break;
				}
			}
		} else {
			mostWanted.put(key.getRequestKey(), 1);
		}
	}

	@Override
	public void visit(TransferedBytesGetter key) {
		if (key.getCount() >= 1500000) {
			System.out.println(key.getCount() + " " + transferedBytes);

		}
		this.transferedBytes += key.getCount();
	}

	@Override
	public void visit(HttpMethodGetter key) {
		if (mostUsedMethod.containsKey(key.getMethod())) {
			for (Entry<?, Integer> e : mostUsedMethod.entrySet()) {
				if (e.getKey().equals(key.getMethod())) {
					e.setValue(e.getValue() + 1);
					break;
				}
			}
		} else {
			mostUsedMethod.put(key.getMethod(), 1);
		}

	}

}
