package grsu.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampParser {
	static class TimestampFormatConfiguration {
		private String timestampFormat;
		private DateFormat formatter;

		private TimestampFormatConfiguration() {
			timestampFormat = "dd/MMM/yyyy:HH:mm:ss ZZ";
			formatter = new SimpleDateFormat(timestampFormat);
		}

		public static class SingletonHolder {
			public static final TimestampFormatConfiguration HOLDER_INSTANCE = new TimestampFormatConfiguration();
		}

		public static TimestampFormatConfiguration getInstance() {
			return SingletonHolder.HOLDER_INSTANCE;
		}

		public void setTimestampFormat(String timestampFormat) {
			this.timestampFormat = timestampFormat;
			changeTimestampFormat();
		}

		private void changeTimestampFormat() {
			formatter = new SimpleDateFormat(timestampFormat);
		}

		public DateFormat getFormatter() {
			return formatter;
		}

	}

	public static Date parse(String timestamp) {

		TimestampFormatConfiguration timestampFormatter = TimestampFormatConfiguration
				.getInstance();
		Date date;
		try {
			date = timestampFormatter.getFormatter().parse(timestamp);
		} catch (ParseException e) {
			System.out.println("Invalid Timestamp format.");
			return null;
		}
		return date;
	}

}
