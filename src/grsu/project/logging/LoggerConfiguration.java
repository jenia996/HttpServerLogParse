package grsu.project.logging;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerConfiguration {
	public static void changeLogLevel(String param) {
		Level level = LoggerParamParser.parseLoggerLevel(param);
		Logger.getRootLogger().setLevel(level);
	}

	public static void changeLogAppender(String param) throws IOException {
		LoggerOutput output = LoggerParamParser.parseLoggerOutput(param);
		if (output != null) {
			Appender appender;
			String layout = PatternConfiguration.getLayout();
			if (output == LoggerOutput.FILE) {
				appender = new FileAppender(new PatternLayout(layout), "log",
						true);
				Logger.getRootLogger().addAppender(appender);
			} else {
				appender = new ConsoleAppender(new PatternLayout(layout));
				Logger.getRootLogger().addAppender(appender);
			}
		}
	}
}
