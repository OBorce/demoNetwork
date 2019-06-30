package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

public abstract class BaseTest {
    @ClassRule
    public static SpringClassRule SCR = new SpringClassRule();

    @Rule
    public SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @LocalServerPort
    public int port = 8888;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssured.port = port;
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)).build();
    }

    protected RequestSpecification documentationSpec;

    protected String obtainAccessToken(String clientId, String username, String password) {
        final Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        final Response response =
                given(documentationSpec)
                        .accept("application/json")
                        .filter(document("getToken"))
                        .auth()
                        .preemptive()
                        .basic(clientId, "secret")
                        .and()
                        .with()
                        .params(params)
                        .when()
                        .post("/oauth/token");
        return response.jsonPath().getString("access_token");
    }
}
