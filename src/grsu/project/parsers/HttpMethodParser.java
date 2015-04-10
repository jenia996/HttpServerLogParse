package grsu.project.parsers;

import grsu.project.data.HttpMethod;

abstract public class HttpMethodParser {

	public static HttpMethod parse(String httpMethod) {
		return HttpMethod.valueOf(httpMethod);
	}
}
