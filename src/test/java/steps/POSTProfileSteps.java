package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class POSTProfileSteps {

    private static ResponseOptions<Response> response;

    @Given("I perform post operation for {string} with body")
    public void iPerformPostOperationForWithBody(String url, DataTable table) {

        //Set body
        HashMap<String, String> body = new HashMap<>();
        body.put("name", table.cell(1,0));

        //Path params
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("profileNo", table.cell(1,1));

        //Perform Post operation
        response = RestAssuredExtension.postOpsWithBodyAndPathParams(url, pathParams, body);

    }

    @Then("I should see the body has name as {string}")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    }
}
