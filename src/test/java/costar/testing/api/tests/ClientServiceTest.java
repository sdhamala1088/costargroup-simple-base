package costar.testing.api.tests;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


import costar.testing.api.data.Client;

public class ClientServiceTest {
	
	private static WireMockServer wireMockServer;
	private ClientService clientService;
    static Logger logger = LogManager.getRootLogger();

	
	@BeforeClass
	public void setup() {
		wireMockServer = new WireMockServer(8080);
		wireMockServer.start();
		clientService = new ClientService("http://localhost:8080");
	}
	
	@AfterClass
	public void tearDown() {
		wireMockServer.stop();
	}

	@Test
	public void testClientService() throws JsonProcessingException {
		Client client = new Client(1, "Sanket", "san@email.com");
		ObjectMapper mapper = new ObjectMapper();
		String clientString = mapper.writeValueAsString(client);
		
		wireMockServer.stubFor(get(urlEqualTo("/client/1")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBody(clientString)));
		
		Client clientR = clientService.getClient();
		logger.log(Level.INFO, clientR.toString());
	}
	
}
