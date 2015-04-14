package grsu.project;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class Buffer extends Thread {

	private List<String> lines = new ArrayList<String>();
	private LineNumberReader reader;
	private volatile boolean finish = false;
	private volatile boolean isFullRead = false;
	private int bufferSize;

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		if (bufferSize > 0) {
			if (bufferSize < this.bufferSize) {
				System.out
						.println("Buffer size is less then current Buffer size, this can slow down the program");
			}
			this.bufferSize = bufferSize;
		} else {
			System.out.println("Buffer size must be greater than 0");
		}
	}

	public boolean isFullRead() {
		synchronized (this) {
			return isFullRead;
		}
	}

	public void setReader(LineNumberReader reader) {
		this.reader = reader;
	}

	public String getLine() {
		synchronized (this) {
			String line = lines.get(0);
			lines.remove(0);
			return line;
		}

	}

	public boolean isEmpty() {
		return getNumberOfLines() > 0;
	}

	private Integer getNumberOfLines() {
		return lines.size();
	}

	public void finish() {
		finish = true;
	}

	public void run() {
		do {
			if (!finish) {
				if (lines.size() < bufferSize) {
					synchronized (this) {
						try {
							if (reader.ready()) {
								lines.add(reader.readLine());
							} else {
								isFullRead = true;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				} else {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else
				return;

		} while (true);
	}
}
