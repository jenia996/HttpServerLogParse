package grsu.project;

import java.util.ArrayList;
import java.util.List;

public class Start
{
	public static void main(String[] args)
	{
		if (InputData.CheckInputData(args))
		{
			InputData input = new InputData(args);
			List<String> text = new ArrayList<String>();
			text = Reader.readLog(input.getPathToFile());
			for (int i = input.getStartLine(); i < text.size()
					&& i < input.getLinesToWrite() + input.getStartLine(); i++)
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
