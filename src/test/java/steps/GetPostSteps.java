package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import java.util.HashMap;

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

    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
        BDDStyleMethod.performPOSTWithBodyParameter();
    }





    @Given("I ensure to perform post operation for {string} with body as")
    public void  iEnsureToPerformPostOperationForWithBodyAs(String url, DataTable table ) {
        HashMap<String, String> body = new HashMap<>();
        body.put("id", table.cell(1,0));
        body.put("title", table.cell(1,1));
        body.put("author", table.cell(1,2));

        //Perform Post operation
        response = RestAssuredExtension.PostOpsWithBody(url, body);

    }

    @And("I perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String url, DataTable table) {
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postId", table.cell(1,0));

        //Perform DELETE operation
        response = RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
    }

    @And("I perform GET operation with path parameter for {string}")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) {
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postId", table.cell(1,0));

        //Perform GET operation
        response = RestAssuredExtension.GetWithPathParams(url, pathParams);
    }

    @Then("I should not see the body with title as {string}")
    public void iShouldNotSeeTheBodyWithTitleAs(String title) {
        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }
}
