import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APIMethods {

    @Test//POST method for a simple example
    public void Test_1 () {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","Gokhan");
        map.put("age","32");

        JSONObject request = new JSONObject(map);


        given().
                body(request.toJSONString()).
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(201).

                time(lessThan(3000L));

    }
    @Test//GET request through artificial api
    public void Test_2 (){
        when().
                get("https://reqres.in/api/users").
                then().
                body("data.first_name[0]", equalTo("George")).
                statusCode(200).log();

    }
    @Test //GET method for a real api
    public void Test_3 () {
        given().
                get("https://hstats.hepsiburada.com/api/track").
                then().
                statusCode(200).log();
    }
    @Test //POST Method for Hepsiburada product services
    public void Test_4 (){
        HashMap<String, Object> map_1 = new HashMap<>();
        map_1.put("path","/api/track");

        JSONObject request = new JSONObject(map_1);

        given().
                body(request.toJSONString()).
                when().
                post("https://hstats.hepsiburada.com/api/track").
                then().
                statusCode(200).
                log().all();

    }

    @Test //PUT Method for simple example
    public void Test_5 () {
        Map<String, Object> map_2 = new HashMap<>();
        map_2.put("name","Gokhan");
        map_2.put("job","Builder");
        System.out.println(map_2);

        JSONObject request = new JSONObject(map_2);
        given().
                body(request.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();

    }
    @Test //PUT Method without HashMap
    public void Test_6 (){
        JSONObject request = new JSONObject();
        request.put("name","Brown");
        request.put("job","Developer");

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then().statusCode(200).log().all();
    }
    @Test
    public void Test_7(){
        JSONObject request = new JSONObject();
        request.put("name","Allan");
        request.put("Job","None");

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();
    }
    @Test
    public void Test_8(){
        given().
                delete("https://reqres.in/api/users/2").
                then().
                statusCode(204).
                log().all();
    }

}
