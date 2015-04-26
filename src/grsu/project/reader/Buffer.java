package grsu.project.reader;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
	private int lastBufferIndex;
	private int firstBufferIndex;
	private List<List<String>> buffers;
	private List<Integer> bufferSequence;
	private BufferConfiguration configuration;

	public Buffer() {
		this.lastBufferIndex = 0;
		this.firstBufferIndex = 0;
		this.configuration = new BufferConfiguration();
		this.buffers = new ArrayList<>();
		this.buffers.add(new ArrayList<String>());
		this.bufferSequence = new ArrayList<>();
		this.bufferSequence.add(0);
	}

	public void add(String line) {
		if (buffers.get(lastBufferIndex).size() < configuration.getPartSize()) {
			buffers.get(lastBufferIndex).add(line);
		} else {
			int emptyBuffer = findEmptyBuffer();
			if (emptyBuffer != -1) {
				bufferSequence.add(emptyBuffer);
				buffers.get(emptyBuffer).add(line);
				lastBufferIndex = emptyBuffer;
			} else {
				lastBufferIndex = buffers.size();
				bufferSequence.add(lastBufferIndex);
				buffers.add(new ArrayList<String>());
				buffers.get(lastBufferIndex).add(line);
			}
		}
	}

	public String take() {
		if (buffers.get(firstBufferIndex).size() > 0) {
			String line = buffers.get(firstBufferIndex).get(0);
			buffers.get(firstBufferIndex).remove(0);
			return line;
		} else {
			bufferSequence.remove(0);
			firstBufferIndex = bufferSequence.get(0);
			return take();
		}
	}

	public int getBufferSequnceSize() {
		return bufferSequence.size();
	}

	public int getBufferSize() {
		return configuration.getPartSize();
	}

	public boolean isEmpty() {
		return buffers.get(lastBufferIndex).size() == 0;
	}

	public boolean isFull() {
		return getBufferSequnceSize() > configuration.getNumberOfParts();
	}

	/*
	 * public void setBufferSize(int bufferSize) { if (bufferSize > 0) { if
	 * (bufferSize < configuration.getPartSize()) { System.out .println(
	 * "Buffer size is less then current Buffer size, this can slow down the program"
	 * ); } this.bufferPartSize = bufferSize; } else {
	 * System.out.println("Buffer size must be greater than 0"); } }
	 */

	public int findEmptyBuffer() {
		for (List<String> buffer : buffers) {
			if (buffer.isEmpty()) {
				return buffers.indexOf(buffer);
			}
		}
		return -1;
	}

	public boolean isAlmostEmpty() {
		return configuration.isAlmostEmpty(getBufferSequnceSize());
	}
}
