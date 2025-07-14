package costar.testing.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class JsonTestUtil {

    private static final JsonMapper mapper = new JsonMapper();
    static Logger logger = LogManager.getRootLogger();
    
    
    public static JsonNode getJsonNodeForField(String jsonString, String field) {
    	
    	JsonNode jsonNode = null;
    	    	
    	try {
    		jsonNode = mapper.readTree(jsonString).get(field);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	    	
    	if (jsonNode == null) {
    		logger.error("No such field {} exists in json {}", field, jsonString);
    	} else {
    		logger.info("Json for field {} = {}", field, jsonNode.toPrettyString());
    	}
		
    	return jsonNode;
    }
    
    public static JsonNode getJsonNodeFromFile(File file) {
    	
    	JsonNode jsonNode = null;
    	
    	try {
    		jsonNode = mapper.readTree(file);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	if (jsonNode == null) {
    		logger.error("No json could be loaded from file {}", file);
    	} else {
    		logger.info("Json extracted from file {} = {}", file.getAbsolutePath(), jsonNode.toPrettyString());

    	}
    	
		return jsonNode;
    }

    public static void assertJsonMatchesFile(String actualJsonString, String expectedJsonFilePath) {
        try {
        	
            JsonNode actualJson = mapper.readTree(actualJsonString);
            JsonNode expectedJson = mapper.readTree(new File(expectedJsonFilePath));

            Assert.assertEquals(actualJson, expectedJson, 
                "Actual JSON does not match expected JSON from file: " + expectedJsonFilePath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read or parse JSON: " + e.getMessage(), e);
        }
    }

    public static void assertJsonMatchesFile(JsonNode actualJsonNode, String expectedJsonFilePath) {
        try {
        	
            JsonNode expectedJson = mapper.readTree(new File(expectedJsonFilePath));

            Assert.assertEquals(actualJsonNode, expectedJson,
                "Actual JSON does not match expected JSON from file: " + expectedJsonFilePath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read or parse JSON: " + e.getMessage(), e);
        }
    }
}
