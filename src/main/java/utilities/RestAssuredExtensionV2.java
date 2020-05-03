package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtensionV2 {

    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     * RestAssuredExtentionV2 constructor to pass the initial settings for ExecuteAPI method
     *
     * @param uri
     * @param method
     * @param token
     */
    public RestAssuredExtensionV2(String uri, String method, String token) {

        //Formulate the API url
        this.url = "http://localhost:3000" + uri;
        this.method = method;

        if (token != null)
            builder.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE/PUT
     *
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> ExecuteAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.PUT))
            return request.put(this.url);
        return null;
    }

    /**
     * Authenticate to get the token variable
     *
     * @param body
     * @return String token
     */
    public String Authenticate(Map<String, String> body) {
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Executing API with query params being passed as input of it
     *
     * @param queryParams
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteAPIWithQueryParams(Map<String, String> queryParams) {
        builder.addQueryParams(queryParams);
        return ExecuteAPI();
    }

    /**
     * Executing API with path params being passed as input of it
     *
     * @param pathParams
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParams(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    /**
     * Executing API with path params and body being passed as input of it
     *
     * @param pathParams
     * @param body
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParamsAndBody(Map<String, String> pathParams, Map<String, String> body) {
        builder.setBody(body);
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

}
