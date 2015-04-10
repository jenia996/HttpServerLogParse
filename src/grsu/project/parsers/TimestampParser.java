package grsu.project.parsers;

import java.text.DateFormat;
import grsu.project.data.*;
import java.text.ParseException;
import java.util.Date;

public class TimestampParser {

	private static TimestampConfiguration timestampConfiguration = new TimestampConfiguration();

	public Date parse(String timestamp) {

		Date date;
		try {
			date = timestampConfiguration.getTimestampFormat().parse(timestamp);
		} catch (ParseException e) {
			if (!timestamp.endsWith("")) {
				System.out.println("Invalid Timestamp format.");
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
