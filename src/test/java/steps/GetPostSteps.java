package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import utilities.RestAssuredExtension;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetPostSteps {

    private static ResponseOptions<Response> response;

    //Anti pattern mixing rest assured bdd and cucumber bdd together
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) {
        response = RestAssuredExtension.GetOps(url);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String authorName) {
        assertThat(response.getBody().jsonPath().get("author"), hasItem("Karthik KK"));
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
}
