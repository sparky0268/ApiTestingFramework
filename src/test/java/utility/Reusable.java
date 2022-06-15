package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Reusable {

	public static RequestSpecification getRequestSpecification() {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.addHeader("content-type", "application/json").log(LogDetail.URI).log(LogDetail.BODY).build();
		return requestSpecification;
	}

	public static ResponseSpecification getResponseSpecification() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType("application/json")
				.log(LogDetail.URI).log(LogDetail.BODY).build();
		return responseSpecification;
	}

	public static String genreateRandonName() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 7;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		return randomString;
	}

	public static Integer generateRandomNumber() {
		Random rand = new Random();
		Integer generatedInteger = rand.nextInt(100000000);
		return generatedInteger;
	}

	public static JSONObject getCredData() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		try {
			Object object = parser.parse(new FileReader("src\\test\\java\\utility\\cred.json"));
			jsonObject = (JSONObject) object;
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}
}
