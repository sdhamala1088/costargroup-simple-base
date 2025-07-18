package costar.testing.api.tests;

import costar.testing.api.data.Client;
import io.restassured.RestAssured;

public class ClientService {
	
	private final String baseUrl;
	
	public ClientService(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public Client getClient() {
		Client client = RestAssured.given().baseUri(baseUrl).when().get("client/1").then().statusCode(200).extract().as(Client.class);
		return client;
	}
}
