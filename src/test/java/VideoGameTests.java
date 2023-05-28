import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

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
}
