package endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import utility.Reusable;

public class Endpoint {

	private static final String baseUrl = System.getProperty("sys.env.url");
	private static final String downloadbaseUrl = System.getProperty("sys.env.download");

	public static Response registerUser(String json) {
		return given(Reusable.getRequestSpecification())
				.header("Authorization", "Bearer " + Reusable.getCredData().get("Token")).body(json).when()
				.post(baseUrl + "/api/authaccount/registration").then().spec(Reusable.getResponseSpecification())
				.extract().response();
	}

	public static Response login(String json) {
		return given(Reusable.getRequestSpecification()).body(json).when().post(baseUrl + "/api/authaccount/login")
				.then().spec(Reusable.getResponseSpecification()).extract().response();
	}

	public static Response createUser(String json) {
		return given(Reusable.getRequestSpecification()).body(json).when().post(baseUrl + "/api/users").then()
				.spec(Reusable.getResponseSpecification()).extract().response();
	}

	public static byte[] downloadFile() {
		return given().contentType("application/octet-stream").when()
				.get(downloadbaseUrl + "Complete_Java_Course_Notes/raw/master/JavaHomeworks/Homework-Java-001.pdf")
				.then().extract().response().asByteArray();
	}

}
