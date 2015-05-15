package grsu.project.reader;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	private BufferConfiguration configuration;
	private Queue<String> buffer;

	public Buffer() {
		this.configuration = new BufferConfiguration();
		this.buffer = new LinkedList<String>();
	}

	public void add(String line) {
		buffer.add(line);
	}

	public String take() {
		return buffer.poll();
	}

	public boolean isEmpty() {
		return buffer.peek() == null;
	}

	public boolean isFull() {
		return buffer.size() > configuration.getBufferSize();
	}

	public boolean isAlmostEmpty() {
		return configuration.isAlmostEmpty(buffer.size());
	}

	public int getCount() {
		return buffer.size();
	}
}
