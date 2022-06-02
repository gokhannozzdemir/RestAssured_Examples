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
                get("https://www.googletagmanager.com/gtm.js?id=GTM-WFZMVP").
                then().
                statusCode(200).log();
    }

}
