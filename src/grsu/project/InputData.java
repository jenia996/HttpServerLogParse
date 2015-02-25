package grsu.project;

public class InputData
{
	private int startLine;
	private int linesToWrite;
	private String pathToFile;

	public InputData(String[] args)
	{
		try
		{
			this.startLine = Integer.parseInt(args[2]);
			this.linesToWrite = Integer.parseInt(args[3]);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		this.pathToFile = args[0] + " " + args[1];
	}
	public static boolean CheckInputData(String[] args)
	{
		if (args.length > 3)
			return true;
		return false;
	}
	public int getStartLine()
	{
		return startLine;
	}
	public int getLinesToWrite()
	{
		return linesToWrite;
	}
	public String getPathToFile()
	{
		return pathToFile;
	}
}
