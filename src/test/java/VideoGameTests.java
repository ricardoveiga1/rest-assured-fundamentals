import config.VideoGameConfig;
import config.VideoGameEndpoints;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import objetct.VideoGame;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class VideoGameTests extends VideoGameConfig {
    String videoGameBody = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Kadico Bros\",\n" +
            "  \"rating\": \"Mature2\",\n" +
            "  \"releaseDate\": \"2013-05-25\",\n" +
            "  \"reviewScore\": 97\n" +
            "}";

    @Test
    public void getAllGames(){
        given()
        .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();

    }

    @Test
    public void createNewGameByJson(){
        given()
                .body(videoGameBody)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void createNewGameByXML(){
        String videoGameBodyXML = "<VideoGameRequest>\n" +
                "\t<category>Platform</category>\n" +
                "\t<name>Mario</name>\n" +
                "\t<rating>Mature</rating>\n" +
                "\t<releaseDate>2012-05-04</releaseDate>\n" +
                "\t<reviewScore>85</reviewScore>\n" +
                "</VideoGameRequest>";
        given()
                .body(videoGameBodyXML)
                .contentType("application/xml")
                .accept("application/xml")
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void updateGame(){
        given()
                .body(videoGameBody)
        .when()
                .put("videogame/3")
        .then();
    }

    @Test
    public void deleteGame(){
        given()
                .body(videoGameBody)
                .accept("text/plain")
        .when()
                .delete("videogame/3")
        .then();
    }

    @Test
    public void getSingleVideoGame(){
        given()
                .body(videoGameBody)
                .pathParams("videoGameId", 3)
        .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
        .then();
    }
    @Test
    public void testVideoGameSerializationByJSON(){
        VideoGame videoGame = new VideoGame("action", "GodOfWar", "kid", "2022-02-01", 92, 6);
        given()
                .body(videoGame)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void testVideoGameSchemaXML(){
        given()
                .pathParams("videoGameId", 5)
                .accept("application/xml")
         .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
         .then()
                .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
    }
    @Test
    public void testVideoGameJsonSchema(){
        given()
                .pathParams("videoGameId", 3)
                .accept("application/json")
        .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    @Test
    public void convertJsonToPojo(){
        Response response =
                given()
                .pathParams("videoGameId", 5)
                .accept("application/json")
        .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES);

        VideoGame videoGame = response.getBody().as(VideoGame.class);
// breakpoint
        System.out.println(videoGame.toString());
    }

    @Test
    public void captureResponseTime(){
        long responseTime = get(VideoGameEndpoints.ALL_VIDEO_GAMES).time();
        System.out.println("Response time in ML: " + responseTime);
    }

    @Test
    public void assertOnResponseTime(){
        get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then().time(lessThan(2000L));  // option + Enter para importar hamcrast no Mac
    }


}
