package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

public class FootballConfig {

    @BeforeClass
    public static void setup(){

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.football-data.org/")
                .setBasePath("/v4")
                .addHeader("X-Auth-Token", "0e6346d9c0c34297bd7c618c440bd964")
                .addHeader("X-Response-Control", "minified")
                .addFilter(new RequestLoggingFilter()) //logs
                .addFilter(new ResponseLoggingFilter()) // logs
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
