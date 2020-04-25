package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;

public class GetPostSteps {
    //Anti pattern mixing rest assured bdd and cucumber bdd together
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) {
    }

    @And("I perform GET for the post number {string}")
    public void iPerformGETForThePostNumber(String postNumber) {
        BDDStyleMethod.simpleGetPost(postNumber);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String arg0) {
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
