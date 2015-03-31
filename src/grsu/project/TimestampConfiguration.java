package grsu.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConfiguration {

	private DateFormat timestampFormat;

	public TimestampConfiguration() {
		timestampFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss ZZ");
	}

	public void setTimestampFormat(String timestampFormat) {
		if (checkTimestampFormat(timestampFormat)) {
			this.timestampFormat = new SimpleDateFormat(timestampFormat);
		}
	}

	private Boolean checkTimestampFormat(String timestampFormat) {
		try {
			@SuppressWarnings("unused")
			DateFormat temp = new SimpleDateFormat(timestampFormat);
			return true;
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Format");
			return false;
		}
	}

	public DateFormat getTimestampFormat() {
		return timestampFormat;
	}

	@Override
	public String toString() {
		Date thisDate = new Date();
		return timestampFormat.format(thisDate);

	}

}
