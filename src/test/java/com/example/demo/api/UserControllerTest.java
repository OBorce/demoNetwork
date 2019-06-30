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
public class UserControllerTest extends BaseTest {

    @Test
    public void getUser() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("getUser"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .when()
                .get("/api/user/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nickname", equalTo("Nick"))
                .body("firstName", equalTo("Name"))
                .body("lastName", equalTo("LName"))
        ;
    }

    @Test
    public void getUserNotFound() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .auth()
                .oauth2(tokenValue)
                .and()
                .when()
                .get("/api/user/3213123")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }

    @Test
    public void createUser() throws FileNotFoundException {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        File file = ResourceUtils.getFile("classpath:data/createUser.json");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("createUser"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .body(file)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/user")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nickname", equalTo("NewNick"))
                .body("firstName", equalTo("NewName"))
                .body("lastName", equalTo("NewLName"))
        ;
    }

    @Test
    public void updateUser() throws FileNotFoundException {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        File file = ResourceUtils.getFile("classpath:data/updateUser.json");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("updateUser"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .body(file)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/user/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nickname", equalTo("NewNick"))
                .body("firstName", equalTo("NewName"))
                .body("lastName", equalTo("NewLName"))
        ;
    }

    @Test
    public void deleteUser() throws FileNotFoundException {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        File file = ResourceUtils.getFile("classpath:data/updateUser.json");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("deleteUser"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .body(file)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/user/3")
                .then()
                .statusCode(HttpStatus.SC_OK)
        ;
    }

    @Test
    public void getAllUsers() {
        final var tokenValue = obtainAccessToken("clientId", "user", "pass");

        given(documentationSpec)
                .accept("application/json")
                .filter(document("getUserChronologically"))
                .auth()
                .oauth2(tokenValue)
                .and()
                .with()
                .param("page", 0)
                .param("size", 10)
                .when()
                .get("/api/user")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("numberOfElements", equalTo(3))
        ;
    }
}
