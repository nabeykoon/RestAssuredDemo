package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import org.hamcrest.core.IsNot;
import pojo.Address;
import pojo.Location;
import pojo.LoginBody;
import pojo.Posts;
import utilities.APIConstant;
import utilities.RestAssuredExtension;
import utilities.RestAssuredExtensionV2;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetPostSteps {

    private static ResponseOptions<Response> response;
    public static String token;

    //Anti pattern mixing rest assured bdd and cucumber bdd together
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) {
        response = RestAssuredExtension.GetOpsWithToken(url, token);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String authorName) {

        var posts = new Posts.Builder().build();
        var post = response.getBody().as(posts.getClass());
        assertThat(post.getAuthor(), equalTo(authorName));

        //without builder pattern
       // var posts = response.getBody().as(Posts.class);
        //assertThat(posts.getAuthor(), equalTo(authorName));
        //assertThat(response.getBody().jsonPath().get("author"), hasItem("Karthik KK"));
    }

    @Then("I should see the author names")
    public void iShouldSeeTheAuthorNames() {
        BDDStyleMethod.performContainsCollection();
    }

    @Then("I should verify GET parameter")
    public void iShouldVerifyGETParameter() {
        BDDStyleMethod.performPathParameter();
    }

    @Then("I should verify GET queryParameter")
    public void iShouldVerifyGETQueryParameter() {
        BDDStyleMethod.performQueryParameter();
    }

    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
        BDDStyleMethod.performPOSTWithBodyParameter();
    }


    @Given("I ensure to perform post operation for {string} with body as")
    public void iEnsureToPerformPostOperationForWithBodyAs(String url, DataTable table) {
        HashMap<String, String> body = new HashMap<>();
        body.put("id", table.cell(1, 0));
        body.put("title", table.cell(1, 1));
        body.put("author", table.cell(1, 2));

        //Perform Post operation
        response = RestAssuredExtension.PostOpsWithBody(url, body);

    }

    @And("I perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String url, DataTable table) {
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postId", table.cell(1, 0));

        //Perform DELETE operation
        response = RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
    }

    @And("I perform GET operation with path parameter for {string}")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) {
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postId", table.cell(1, 0));

        //Perform GET operation
        response = RestAssuredExtension.GetWithPathParams(url, pathParams);
    }

    @Then("I should not see the body with title as {string}")
    public void iShouldNotSeeTheBodyWithTitleAs(String title) {
        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }

    @Given("I perform authentication operation for {string} with body")
    public void iPerformAuthenticationOperationForWithBody(String uri, DataTable table) {
     /*   HashMap<String, String> body = new HashMap<>();
        body.put("email", table.cell(1, 0));
        body.put("password", table.cell(1, 1));*/

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(table.cell(1, 0));
        loginBody.setPassword(table.cell(1, 1));

        // response = RestAssuredExtension.PostOpsWithBody(url, body);
        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, APIConstant.ApiMethods.POST, null);
        token = restAssuredExtensionV2.Authenticate(loginBody);
    }

    @Given("I perform GET operation with query parameter for address {string}")
    public void iPerformGETOperationWithQueryParameterForAddress(String uri, DataTable table) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("id", table.cell(1, 0));

        //response = RestAssuredExtension.GetWithQueryParamsWithToken(url,queryParams, response.getBody().jsonPath().get("access_token"));
        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, "GET", token);
        response = restAssuredExtensionV2.ExecuteAPIWithQueryParams(queryParams);
    }


    @Then("I should see the street name as {string} for the {string} address")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String type) {
        var location = response.getBody().as(Location[].class);

        //Filter the address based on the type of address
        Address address = location[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase(type))
                .findFirst().orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Then("I should see the author name as {string} with json validation")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) {

        //returns the body as string
        var responseBody = response.getBody().asString();

        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
    }
}
