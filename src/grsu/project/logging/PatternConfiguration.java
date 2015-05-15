package grsu.project.logging;

public class PatternConfiguration {
	private static String layout = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}: %m%n";

	public static String getLayout() {
		return layout;
	}

	public static void setLayout(String layout) {
		PatternConfiguration.layout = layout;
	}

}
