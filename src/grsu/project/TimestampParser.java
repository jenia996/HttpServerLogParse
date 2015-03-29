package grsu.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TimestampParser {

	private static TimestampConfiguration format = new TimestampConfiguration();

	public Date parse(String timestamp) {

		Date date;
		try {
			date = format.getTimestampFormat().parse(timestamp);
		} catch (ParseException e) {
			System.out.println("Invalid Timestamp format.");
			return null;
		}
		return date;
	}

	public void setTimestampFormat(String format) {
		TimestampParser.format.setTimestampFormat(format);
	}

	public String getFormatString() {
		return format.getTimestampFormatString();
	}

	public DateFormat getFormat() {
		return format.getTimestampFormat();
	}

}
