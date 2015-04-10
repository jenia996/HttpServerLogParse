package grsu.project.parsers;

import grsu.project.data.HostField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class HostFieldParser {

	private static final String IPPattern = "(\\d{1,3}.){3}\\d{1,3}";

	public static HostField parse(String host) {
		HostField hostField = new HostField();
		Matcher IPMatcher = Pattern.compile(IPPattern).matcher(host);
		if (IPMatcher.find()) {
			hostField.setHostIP(host);
		} else {
			hostField.setHost(host);
		}
		return hostField;
	}
}
