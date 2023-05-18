package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.EmailGenerator.generateRandomEmail;

public class UserCRUD {
    String token = "882f81aa0baa269b59d21c210e97cb43de805ce9fccc1fbae1817da3e995717c";
    String baseUrl = "https://gorest.co.in/public/v2/users";
    private int id;
    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("The creation of a user having the keys name,gender,email & status")
    public void generateUser() {
        JSONObject request = new JSONObject();
        String randomEmail = generateRandomEmail();
        request.put("name", "Abdias Alpire");
        request.put("gender", "male");
        request.put("email", "abdias+33@gmail.com");
        request.put("status", "active");

        id = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(request.toJSONString())
                .when()
                .post(baseUrl)
                .jsonPath()
                .getInt("id");
    }
    @Test (priority=2)
    @Severity(SeverityLevel.NORMAL)
    @Description("The list of users")
    public void listUser() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get(baseUrl);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status code is not 201");
    }
    @Test (priority=3)
    @Severity(SeverityLevel.NORMAL)
    @Description("The update of a user using the same structure as the createUser method, and using the id as a reference")
    public void updateUser() {
        //String new_requestBody = "{\"name\":\"Andres Munoz\", \"email\":\"alpire+3@gmail.com\"}";
        String apiUrl = "https://gorest.co.in/public/v2/users/";
        JSONObject request = new JSONObject();
        String email_updated = "alpire+9@gmail.com";
        request.put("name", "Andres Munoz");
        request.put("email", email_updated);
        request.put("status", "active");
        given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                //.body(new_requestBody)
                .when()
                .patch(apiUrl+id)
                .then()
                .assertThat()
                .statusCode(200);

    }
    @Test (priority=4)
    @Severity(SeverityLevel.NORMAL)
    @Description("The deletion of a user using the id as a reference")
    public void deleteUserTest() {

        given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(baseUrl+"/"+id)
                .then()
                .assertThat()
                .statusCode(204);
    }

}