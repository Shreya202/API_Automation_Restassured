package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserTest extends BaseTest {

    @Test(priority = 1)
    public void testGetUser() {

        Response response =
                given()
                    .spec(requestSpec)
                .when()
                    .get("/users/2")
                .then()
                    .statusCode(200)
                    .extract().response();

        // JsonPath validation
        String email = response.jsonPath().getString("data.email");

        System.out.println("User Email: " + email);

        Assert.assertTrue(email.contains("@reqres.in"));
    }


    @Test(priority = 2)
    public void testCreateUser() {

        // Request Body using Map (Recommended)
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Shreya");
        requestBody.put("job", "QA Engineer");

        Response response =
                given()
                    .spec(requestSpec)
                    .body(requestBody)
                .when()
                    .post("/users")
                .then()
                    .statusCode(201)
                    .extract().response();

        // Validate response
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        String id = response.jsonPath().getString("id");

        Assert.assertEquals(name, "Shreya");
        Assert.assertEquals(job, "QA Engineer");
        Assert.assertNotNull(id);

        System.out.println("Created User ID: " + id);
    }
}