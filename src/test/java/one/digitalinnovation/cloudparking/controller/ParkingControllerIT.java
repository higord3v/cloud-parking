package one.digitalinnovation.cloudparking.controller;

import org.springframework.http.MediaType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerIT {
    
    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
            .when()
            .get("/parking")
            .then()
            .statusCode(200)
            .extract().response().body().prettyPrint();
    }

    @Test
    void whenCreateThenCheckResult() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("DF2-2342");
        createDTO.setModel("UNO");
        createDTO.setState("DF");
        
        RestAssured.given()
            .when()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(createDTO)
            .post("/parking")
            .then()
            .statusCode(201)
            .body("license", Matchers.equalTo("DF2-2342"));
    }
}
