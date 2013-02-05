package ca.ulaval.glo4002.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HomeConnectionHandler {

	private static final String SERVER_URL = "http://localhost:8080/test";
	private WebResource webResource;

	public HomeConnectionHandler() {
		Client client = Client.create();
		webResource = client.resource(SERVER_URL);
	}

	public WebResource getWebResource() {
		return webResource;
	}

	public void sendPostRequest(String info) throws RuntimeException {
		ClientResponse response = webResource.type("Application/xml").post(
				ClientResponse.class, info);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed: HTTP error code: "
					+ response.getStatus());
		}
	}
}
