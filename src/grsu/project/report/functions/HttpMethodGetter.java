package grsu.project.report.functions;

import grsu.project.data.HttpMethod;
import grsu.project.data.LogRecord;
import grsu.project.report.Visitor;

public class HttpMethodGetter extends AbstractGetter implements
		ReportFunction<HttpMethodGetter> {
	private HttpMethod method;

	public HttpMethod getMethod() {
		return method;
	}

	@Override
	public HttpMethodGetter create(LogRecord logRecord) {
		return new HttpMethodGetter(logRecord.getHttpMethod());
	}

	public HttpMethodGetter() {
	}

	public HttpMethodGetter(HttpMethod method) {
		this.method = method;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);

	}

}
