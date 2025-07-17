package costar.testing.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import costar.testing.api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.File;

import static costar.testing.api.utils.JsonTestUtil.getJsonNodeForField;
import static costar.testing.api.utils.JsonTestUtil.getJsonNodeFromFile;

public class UserApiTests {
	
	private static final String EXPECTED_USER_PATH = "src/test/resources/expected_user.json";
	
	@BeforeClass
	public void setupBaseUrl() {
		
		ConfigReader.load("src/test/resources/api-config.properties");
		
		RestAssured.baseURI = ConfigReader.get("api.url");
		RestAssured.requestSpecification = given()
				.header(ConfigReader.get("api.key.name"), ConfigReader.get("api.key.value"))
				.header("Content-Type", "application/json");
	}
	
	@Test(description = "Validate that GET /api/users/2 returns correct user data", groups= {"smoke", "api"})
	public void validateUserData() {
		
		Response rs = given().when().get("/api/users/2")
		.then()
		.statusCode(200)
		.extract()
		.response();
		
		JsonNode actualUser = getJsonNodeForField(rs.asString(), "data");
		JsonNode expectedUser = getJsonNodeFromFile(new File(EXPECTED_USER_PATH));
		
        Assert.assertEquals(actualUser, expectedUser, "Actual user does not match expected JSON");
	}
}
