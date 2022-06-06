import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class Localhost {

    @Test//localhost get method
    public void localhost_1(){
        baseURI = "http://localhost:3000";

        given().
                param("name","racer").
                get("/subjects").
                then().
                statusCode(200).
                log().all();
    }
    @Test //localhost post method
    public void localhost_2(){
        baseURI = "http://localhost:3000";
        JSONObject request = new JSONObject();
        request.put("firstName","Rafael");
        request.put("lastName","Nadal");
        request.put("subjectId","2");


        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type","application/json").
                body(request.toJSONString()).
                post("/users").
                then().
                statusCode(201).
                log().all();
    }
    @Test// Last situation what inserted new db info
    public void localhost_3(){
        baseURI = "http://localhost:3000";

        given().
                get("/users").
                then().statusCode(200).
                log().all();
    }
    @Test //Assert using with GET Method
    public void localhost_4(){
        baseURI = "http://localhost:3000";

        given().
                get("/subjects/").
                then().
                body("name[1]",equalTo("racer")).
                statusCode(200).
                log().all();

    }

}
