package grsu.project;

abstract public class HttpMethodParser {

	public static  HttpMethod parse(String httpMethod)
	{
		return HttpMethod.valueOf(httpMethod);	
	}
}
