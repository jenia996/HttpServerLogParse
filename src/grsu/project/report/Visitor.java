package grsu.project.report;

import grsu.project.report.functions.HttpMethodGetter;
import grsu.project.report.functions.RequestGetter;
import grsu.project.report.functions.TransferedBytesGetter;

public interface Visitor {
	public void visit(RequestGetter key);

	public void visit(TransferedBytesGetter key);

	public void visit(HttpMethodGetter key);
}
