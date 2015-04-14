package grsu.project.report.functions;

import grsu.project.data.LogRecord;
import grsu.project.report.Visitor;

public class RequestGetter extends AbstractGetter implements
		ReportFunction<RequestGetter> {

	private String requestKey;

	@Override
	public RequestGetter create(LogRecord logRecord) {
		return new RequestGetter(logRecord.getRequest());
	}

	public RequestGetter(String request) {
		this.requestKey = request;
	}

	public RequestGetter() {
	}

	public String getRequestKey() {
		return requestKey;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
