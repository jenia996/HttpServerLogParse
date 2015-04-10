package grsu.project.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimestampConfiguration {

	private DateFormat timestampFormat;
	private Locale locale;

	public TimestampConfiguration() {
		locale = Locale.ENGLISH;
		timestampFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss ZZ",
				locale);
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
