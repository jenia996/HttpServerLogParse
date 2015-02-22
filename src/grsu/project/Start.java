package grsu.project;

import java.util.ArrayList;
import java.util.List;

public class Start
{
	public static void main(String[] args)
	{
		List<String> text = new ArrayList<String>();
		text = Reader.readLog(args[0] + " " + args[1]);
		for (int i = Integer.parseInt(args[2]); i < text.size()
				&& i < Integer.parseInt(args[3]); i++)
		{
			System.out.println(text.get(i));
		}
		System.out.println(text.size());
	}
}
