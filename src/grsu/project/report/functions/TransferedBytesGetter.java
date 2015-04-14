package grsu.project.report.functions;

import grsu.project.data.LogRecord;
import grsu.project.report.Visitor;

public class TransferedBytesGetter extends AbstractGetter implements
		ReportFunction<TransferedBytesGetter> {
	private long count;

	public long getCount() {
		return count;
	}

	@Override
	public TransferedBytesGetter create(LogRecord logRecord) {
		return new TransferedBytesGetter(logRecord.getReplyBytes());
	}

	public TransferedBytesGetter() {
	}

	public TransferedBytesGetter(long count) {
		this.count = count;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);

	}
}
