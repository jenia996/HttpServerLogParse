package grsu.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader
{
	private static BufferedReader cin;

	public static ArrayList<String> readLog(String path)
	{
		ArrayList<String> text = new ArrayList<String>();
		try
		{
			cin = new BufferedReader(new FileReader(path));
			String line;
			while ((line = cin.readLine()) != null)
			{
				text.add(line);
			}
			cin.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(0);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return text;
	}
}
