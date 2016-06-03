package com.xxx.yyy.test.automation;

public class InfomationServer {
	private final String HOST = "10.23.157.47";
	private final String PORT = "8090";
	private final int TIME_OUT = 3000;
	private final int READ_TIME_OUT = 3000;
	private String host;
	private String port;
	private String url;
	private int timeOut;
	private int readTimeOut;
	private HTTPMethod [] methods;  
	
	public InfomationServer() {
		init(HOST, PORT, TIME_OUT, READ_TIME_OUT);
	}

	public InfomationServer(String host, String port) {
		init(host, port, TIME_OUT, READ_TIME_OUT);
	}
	
	private void init(String HOST, String PORT, int TIME_OUT, int READ_TIME_OUT) {
		host = HOST;
		port = PORT;
		url = "http://" + host + ":" + port;
		timeOut = TIME_OUT;
		readTimeOut = READ_TIME_OUT;
		methods = new HTTPMethod[] {HTTPMethod.GET};
	}
	
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @return the URL
	 */
	public String getURL() {
		return url;
	}
	
	public int getReadTimeOut() {
		return readTimeOut;
	}

	public int getTimeOut() {
		return timeOut;
	}
	
	public String getDefaultRequestMethod() {
		return methods[0].methodName;
	}
	
	public HTTPMethod[] getRequestMethods() {
		return methods;
	}
	
	enum HTTPMethod {
		POST("POST"), GET("GET"), PUT("PUT"), PATCH("PATCH"), DELETE("DELETE");
		private String methodName = null;
		
		private HTTPMethod(String methodName) {
			this.methodName = methodName;
		}
		
		public String getMethodName() {
			return methodName;
		}
	}
}
