package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected RequestSpecification requestSpec;
    @Parameters("baseUrl")

    @BeforeClass
    public void setup(String baseUrl) {

        // Base URI
        RestAssured.baseURI = baseUrl;

    	//RestAssured.baseURI = System.getProperty("baseUrl", "https://reqres.in/api");
        // Build common request specification
        requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        // Log only when validation fails (optional safety)
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}