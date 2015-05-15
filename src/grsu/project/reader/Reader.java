package grsu.project.reader;

import grsu.project.report.ReportGenerator;

import java.io.IOException;
import java.io.LineNumberReader;

public class Reader extends Thread {

	private static Buffer buffer;
	private LineNumberReader reader;
	private Monitor monitor = new Monitor();
	private volatile boolean finish = false;
	private volatile boolean isFullRead = false;

	public void setWaiting(boolean isWaiting) {
		monitor.setWaiting(isWaiting);
	}

	public Object getMonitor() {
		return monitor;
	}

	public void finish() {
		finish = true;
	}

	public boolean isEmpty() {
		try {
			return buffer.isEmpty();
		} catch (NullPointerException e) {
			System.out.println("Empty");
			return true;
		}
	}

	public boolean isFullRead() {
		synchronized (this) {
			return isFullRead;
		}
	}

	public boolean isAlmostEmpty() {
		return buffer.isAlmostEmpty();
	}

	public String getLine() {
		return buffer.take();
	}

	public void setReader(LineNumberReader reader) {
		this.reader = reader;
	}

	public void run() {
		buffer = new Buffer();
		do {
			if (!finish) {
				if (!buffer.isFull()) {
					try {
						if (reader.ready()) {
							buffer.add(reader.readLine());
						} else {
							isFullRead = true;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					synchronized (monitor) {
						while (buffer.isFull()) {
							try {
								monitor.setWaiting(true);
								if (ReportGenerator.isWaiting()) {
									synchronized (ReportGenerator.getMonitor()) {
										ReportGenerator.getMonitor().notify();
										ReportGenerator.setWaiting(false);
									}
								} else {
									monitor.wait();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		} while (true);
	}

	public boolean isWaiting() {
		return monitor.isWaiting();
	}
}
