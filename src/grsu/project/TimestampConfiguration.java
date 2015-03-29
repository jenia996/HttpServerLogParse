package grsu.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimestampConfiguration {

	private DateFormat formatter;

	public TimestampConfiguration() {
		formatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss ZZ");
	}

	public void setTimestampFormat(String timestampFormat) {
		formatter = new SimpleDateFormat(timestampFormat);
	}

	public DateFormat getTimestampFormat() {
		return formatter;
	}

	public String getTimestampFormatString() {
		return formatter.toString();
	}

}
