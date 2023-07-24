package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class VideoGameConfig {

    @BeforeClass
    public static void setup(){
        //RestAssured.baseURI = "https://www.videogamedb.uk/";
        //RestAssured.basePath = "api/v2/";

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://www.videogamedb.uk/")
                .setBasePath("api/v2/")
                .setContentType("application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter()) //logs
                .addFilter(new ResponseLoggingFilter()) // logs
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(2000L))
                .build();
    }
}
