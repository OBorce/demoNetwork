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
public class PostControllerTest extends BaseTest {

    @Test
    public void getPost() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("getPost"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .when()
                .get("/api/post/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo("Title"))
                .body("description", equalTo("Description"))
        ;
    }

    @Test
    public void getPostNotFound() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .auth()
                .oauth2(tokenValue)
                .and()
                .when()
                .get("/api/post/3213123")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }

    @Test
    public void createPost() throws FileNotFoundException {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        File file = ResourceUtils.getFile("classpath:data/createPost.json");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("createPost"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .body(file)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/post")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo("CreatedTitle"))
                .body("description", equalTo("CreatedDescription"))
        ;
    }

    @Test
    public void updateUser() throws FileNotFoundException {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        File file = ResourceUtils.getFile("classpath:data/updatePost.json");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("updatePost"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .body(file)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/post/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo("NewTitle"))
                .body("description", equalTo("NewDescription"))
        ;
    }
}
