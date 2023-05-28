package config;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTest extends FootballConfig{

    @Test
    public void getDetailsOfOneArea(){
        given()
                .queryParam("areas", 2001)
        .when()
                .get("/areas");
    }

    @Test
    public void getDetailsOfMultipleAreas(){
        String areaIds = "2001, 2076, 2010";
        given()
                .queryParam("areas", areaIds)
        .when()
                .get("/areas");
    }

    @Test
    public void getDateFounded(){
        given()
        .when()
                .get("/teams/57")
        .then()
                .body("founded", equalTo(1886));
    }

    @Test
    public void getFirstTeamName(){
        given()
        .when()
                .get("/competitions/2021/teams")
        .then()
                .body("teams[0].name", equalTo("Arsenal FC"))
                .body("teams[1].name", equalTo("Aston Villa FC"));
    }

}
