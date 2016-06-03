package examples.jaxws.helloservice.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {
	private String message = new String("Hello, ");

	public void Hello() {
	}
	
	@WebMethod
	public String getHello(String name) {
		return message + name + ".";
	}
}
