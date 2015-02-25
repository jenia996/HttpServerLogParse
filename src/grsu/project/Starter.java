package grsu.project;

import java.util.ArrayList;
import java.util.List;

public class Starter
{
	public static void main(String[] args)
	{
		if (InputData.CheckInputData(args))
		{
			InputData input = new InputData(args);
			List<String> text = new ArrayList<String>();
			text = Reader.readFileToList(input.getPathToFile());
			for (int i = input.getStartLine(); i < text.size()
					&& i < input.getStartLine() + input.getLinesToWrite(); i++)
			{
				System.out.println(text.get(i));
			}
		}
		else
		{
			System.out.println("Check input Data");
		}
	}
}
