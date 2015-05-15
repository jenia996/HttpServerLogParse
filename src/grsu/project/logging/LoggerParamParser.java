package grsu.project.logging;

import org.apache.log4j.Level;

public class LoggerParamParser {
	public static LoggerOutput parseLoggerOutput(String input) {
		input = input.toUpperCase();
		try {
			return LoggerOutput.valueOf(input);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid parameter");
			return null;
		}
	}
	public static Level parseLoggerLevel(String level)
	{
		Level out;
		level = level.toUpperCase();
		if(level.equals("FATAL"))
		{
			out = Level.FATAL;
			return out;
		}
		if(level.equals("ERROR"))
		{
			out = Level.ERROR;
			return out;
		}
		if(level.equals("WARN"))
		{
			out = Level.WARN;
			return out;
		}
		if(level.equals("DEBUG"))
		{
			out = Level.DEBUG;
			return out;
		}
		if(level.equals("INFO"))
		{
			out = Level.INFO;
			return out;
		}
		if(level.equals("TRACE"))
		{
			out = Level.TRACE;
			return out;
		}
		if(level.equals("ALL"))
		{
			out = Level.ALL;
			return out;
		}
		if(level.equals("OFF"))
		{
			out = Level.OFF;
			return out;
		}
		return null;
	}
}
