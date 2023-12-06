import config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GpathJSONTests  extends FootballConfig {

    @Test
    public void extractMapOfElementsWithFind(){
        Response response = get("/competitions/2021/teams");

        Map<String , ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Aston Villa FC' }");
    //usar breakpoint para ver retorno
        System.out.println("Map of team data = " + allTeamDataForSingleTeam);
    }

    @Test
    public void extracSingValueWithFind(){
        Response response = get("/teams/57");
        String certainPlayer = response.path("squad.find { it.id == 4832 }.name");
        System.out.println("Name of player: " + certainPlayer);
    }

    @Test
    public void extracListOfValuesWithFind(){
        Response response = get("/teams/57");
        List<String> playersName = response.path("squad.findAll { it.id >= 4832 }.name");
        System.out.println("List of players: " + playersName);
    }

    @Test
    public void extracSingleValueWithHigestNumber(){
        Response response = get("/teams/57");
        String playerName = response.path("squad.max { it.id }.name");
        System.out.println("Player with higest id: " + playerName);
    }

    @Test
    public void extracMultipleValuesAndSumThem(){
        Response response = get("/teams/57");
        int sumOdIds = response.path("squad.collect { it.id }.sum()");
        System.out.println("Sum ooff all IDs: " + sumOdIds);
    }

    @Test
    public void extracMapWithFindAndFindAllWithParameters(){

        String position = "Offence";
        String nationality = "England";

        Response response = get("/teams/57");

        Map< String, ?> PlayerOfCertainosition= response.path(
                "squad.findAll { it.position == '%s' }.find { it.nationality == '%s' }",
                position, nationality
        );
        System.out.println("Details of player: " + PlayerOfCertainosition);
    }

    @Test
    public void extracMultiplePlayers(){

        String position = "Offence";
        String nationality = "England";

        Response response = get("/teams/57");

        ArrayList<Map< String, ?>> allPlayerOfCertainosition= response.path(
                "squad.findAll { it.position == '%s' }.findAll { it.nationality == '%s' }",
                position, nationality
        );
        System.out.println("Details of players: " + allPlayerOfCertainosition);
    }




}
