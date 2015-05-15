package grsu.project.reader;

public class BufferConfiguration {
	private int bufferSize;
	private int sizeWhenContinue;

	public BufferConfiguration(int numberOfParts, int partSize) {
		this.bufferSize = partSize;
		sizeWhenContinue = numberOfParts - numberOfParts / 3 - numberOfParts
				/ 4;
	}

	public BufferConfiguration() {
		this(60000, 40000);
	}


	public int getBufferSize() {
		return bufferSize;
	}

	public boolean isAlmostEmpty(int currentNumberOfParts) {
		return currentNumberOfParts <= sizeWhenContinue;
	}
}
