package com.example.demo.api;

import com.example.demo.BaseTest;
import com.example.demo.DemoApplication;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@ActiveProfiles("test")
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchControllerTest extends BaseTest {

    @Test
    public void getUserWithNickname() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("getUserWithNickname"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .when()
                .get("/api/search/user/nickname/Nick")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nickname", equalTo("Nick"))
        ;
    }

    @Test
    public void getAllUsersByCity() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("getUserByCity"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .param("page", 0)
                .param("size", 10)
                .when()
                .get("/api/search/user/location/Veles")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("numberOfElements", equalTo(1))
        ;
    }

    @Test
    public void getAllPostsByCity() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("getPostsByCity"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .param("page", 0)
                .param("size", 10)
                .when()
                .get("/api/search/post/location/Veles")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("numberOfElements", equalTo(1))
        ;
    }

    @Test
    public void getAllPostsByTitle() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("getPostsByTitle"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .param("page", 0)
                .param("size", 10)
                .when()
                .get("/api/search/post/title/Title")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("numberOfElements", equalTo(1))
        ;
    }
}
