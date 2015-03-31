package grsu.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TimestampParser {

	private static TimestampConfiguration timestampConfiguration = new TimestampConfiguration();

	public Date parse(String timestamp) {

		Date date;
		try {
			date = timestampConfiguration.getTimestampFormat().parse(timestamp);
		} catch (ParseException e) {
			System.out.println("Invalid Timestamp format.");
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
