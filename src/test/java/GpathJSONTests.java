import config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class GpathJSONTests  extends FootballConfig {

    @Test
    public void extractMapfElementsWithFind(){
        Response response = get("/competitions/2021/teams");

        Map<String , ?> allTeamsDataForSingleeam = response.path("teams.find { it.name == 'Aston Villa FC' }");

        System.out.println("Map of team data = " + allTeamsDataForSingleeam);
    }


}
