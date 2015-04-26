package grsu.project.parsers;

import java.text.DateFormat;

import grsu.project.data.*;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimestampParser {

	private static TimestampConfiguration timestampConfiguration = new TimestampConfiguration();
	final static Logger logger = Logger.getLogger(TimestampParser.class);

	public Date parse(String timestamp) {

		Date date;
		try {
			date = timestampConfiguration.getTimestampFormat().parse(timestamp);
		} catch (ParseException e) {
			if (!timestamp.equals("")) {
				logger.error("Invalid Date " + timestamp);
			}
			return null;
		}
		return date;
	}

	public void setTimestampFormat(String format) {
		TimestampParser.timestampConfiguration.setTimestampFormat(format);
	}

	public DateFormat getTimestampFormat() {
		return timestampConfiguration.getTimestampFormat();
	}

	public String getTimestampFormatString() {
		return timestampConfiguration.toString();
	}

}
