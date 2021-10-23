package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static support.TestContext.*;

    public class RestWrapper implements Loggable {

        public Logger getLogger(){
            return LogManager.getLogger(RestWrapper .class);
  }

    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private static String loginToken;

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON = "application/json";
    public static final String AUTH = "Authorization";


    public RestWrapper login(Map<String, String> user) {
        // prepare
        RequestSpecification request = RestAssured //saved
                .given() //opener
                .log().all()
                .baseUri(baseUrl)    // end point
                .basePath("login")   // end point    // it does "https://skryabin.com/recruit/api/v1/" + "login"
                .header(CONTENT_TYPE, JSON) //header key and value
                .body(user); //provide our map in body
        // execute
        Response response = request //it will return response
                .when()
                .post(); //send request (our data) to server

        // verify and extract
        Map<String, Object> result = response
                .then()
                .log().all()//more information to help if some-g wrong
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap(""); //empty means take everything

        loginToken = "Bearer " + result.get("token"); //take token from result
        System.out.println(loginToken);
        return new RestWrapper();
    }

        public Map<String, Object> createPosition(Map<String, String> position) {
            String dateOpen = position.get("dateOpen");
            String isoDateOpen = new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateOpen));
            position.put("dateOpen", isoDateOpen);
            // prepare
            RequestSpecification request = RestAssured
                    .given()
                    .log().all()
                    .baseUri(baseUrl)
                    .basePath("positions")
                    .header(CONTENT_TYPE, JSON)
                    .header(AUTH, loginToken)
                    .body(position);


        // execute
        Response response = request
                .when()
                .post();

        // verify and extract
        Map<String, Object> result = response
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");


        setTestData("newPosition", result);

        return result;
    }

    public List<Map<String, Object>> getPositions() {
        getLogger().info("Reading list of  position");
        // prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions");

        // execute
        Response response = request
                .when()
                .get();

        // verify and extract
        List<Map<String, Object>> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");
        return result;
    }


    public Map<String, Object> updatePosition(Map<String, String> fields, Object id) {
        getLogger().info("Updating position with id: " + id);

        // prepare
        Map<String, Object> result = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(fields)
                .when()
                .patch()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        Map<String, Object> newPosition = getTestDataMap("newPosition");
        for (String key : result.keySet()) {
            newPosition.put(key, result.get(key));
        }
        setTestData("newPosition", newPosition);

        return result;
    }

    public Map<String, Object> getPositionById(Object id) {
        Map<String, Object> result = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        return result;
    }

    public void deletePositionById(Object id) {
        RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .header(AUTH, loginToken)
                .when()
                .delete()
                .then()
                .log().all()
                .statusCode(204);
    }



        public Map<String, Object> createCandidate(Map<String, String> candidate){
            //prepare
            RequestSpecification request = RestAssured
                    .given()
                    .log().all()
                    .baseUri(baseUrl)
                    .basePath("candidates")
                    .header(CONTENT_TYPE,JSON)
                    .header("Autorization", loginToken)
                    .body(candidate);

            //execute
            Response response = request
                    .when()
                    .post();

            //verify and extract
            Map<String, Object> result = response
                    .then()
                    .log().all()
                    .statusCode(201)
                    .extract()
                    .jsonPath()
                    .get("");

            setTestData("newCandidate", result);
            return result;
        }


        public List<Map<String, Object>> getCandidates(){
            // prepare
            RequestSpecification request = RestAssured
                    .given()
                    .log().all()
                    .baseUri(baseUrl)
                    .basePath("candidates");

            // execute
            Response response = request
                    .when()
                    .get();

            // verify and extract
            List<Map<String, Object>> result = response
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .jsonPath()
                    .getList("");
            return result;
        }




    }




