package config;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void getAllTeamData(){
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamData_DoCheckirst(){
        Response response =
                given()
                .when()
                        .get("teams/57")
                .then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void extractHeaders(){
        Response response =
                get("teams/57")
                .then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        String contentTypeHeader = response.getContentType();
        System.out.println("Content Type: " + contentTypeHeader);

        String apiVersionHeader = response.getHeader("X-API-Version");
        System.out.println("Content Type: " + apiVersionHeader);
    }

    @Test
    public void extractFirstTeamName(){
        String firstTeam = get("/competitions/2021/teams").jsonPath().getString("teams.name[0]");
        System.out.println(firstTeam);
    }

    @Test
    public void extractAllTeamNames(){
        Response response = get("/competitions/2021/teams")
                .then()
                        .extract().response();

        List<String> teamNames = response.path("teams.name");

        for(String teamName : teamNames){
            System.out.println(teamName);
        }

    }



}
