package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {

        // Base URI
        RestAssured.baseURI = "https://reqres.in/api";

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