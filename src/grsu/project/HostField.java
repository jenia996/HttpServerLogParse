package grsu.project;

public class HostField {
	private String host;
	private String hostIP;
//	private byte[] hostIP;
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		if(host != null)
		{
			toString.append(host + " ");
		}
		if(hostIP != null)
		{
			toString.append(hostIP + " ");
		}
		return toString.toString();
	}
	

}
