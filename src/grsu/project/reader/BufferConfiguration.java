package grsu.project.reader;

public class BufferConfiguration {
	private int partSize;
	private int numberOfParts;
	private int numberOfPartsWhenContinue;

	public BufferConfiguration(int numberOfParts, int partSize) {
		this.numberOfParts = numberOfParts;
		this.partSize = partSize;
		numberOfPartsWhenContinue = numberOfParts-numberOfParts/3;
	}

	public BufferConfiguration() {
		this(10,5000);
	}

	public int getPartSize() {
		return partSize;
	}

	public int getNumberOfParts() {
		return numberOfParts;
	}

	public boolean isAlmostEmpty(int currentNumberOfParts) {
		return currentNumberOfParts <= numberOfPartsWhenContinue;
	}
}
