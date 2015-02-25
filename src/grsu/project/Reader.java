package grsu.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Reader
{
	public static ArrayList<String> readFileToList(String path)
	{
		ArrayList<String> text = new ArrayList<String>();
		try
		{
			String line;
			BufferedReader cin = new BufferedReader(new FileReader(path));
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
			e.printStackTrace();
			System.exit(0);
		}
		return text;
	}
}
