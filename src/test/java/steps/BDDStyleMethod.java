package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BDDStyleMethod {
    public static void simpleGetPost(String postNumber) {
        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3001/posts/%s", postNumber)).
                then().body("author", is("Karthik KK"))
                .statusCode(200);
    }

    public static void performContainsCollection() {
        given()
                .contentType(ContentType.JSON)
                .when().get("http://localhost:3001/posts/")
                .then()
                .body("author", containsInAnyOrder("Karthik KK", "Karthik KK", null))
                .statusCode(200);
    }

    public static void performPathParameter() {
        given()
                .contentType(ContentType.JSON).
        with()
                .pathParams("postKey", 1).
        when()
                .get("http://localhost:3001/posts/{postKey}").
        then()
                .body("author", containsString("Karthik KK"));
    }

    public static void performQueryParameter() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("id",1).
        when()
                .get("http://localhost:3001/posts/").
        then()
                .body("author", hasItem("Karthik KK"));
    }
}
