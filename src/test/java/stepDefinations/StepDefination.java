package stepDefinations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.json.JSONObject;
import org.junit.Assert;

import endpoints.Endpoint;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utility.Reusable;

public class StepDefination {

	private static JSONObject userPayload = new JSONObject();
	private static JSONObject loginData = new JSONObject();
	private static String name;
	private static String email;
	private static Integer password;
	private static Response response;

	@Given("I have the user payload")
	public static void i_have_the_user_payload() {
		name = Reusable.genreateRandonName();
		email = Reusable.genreateRandonName() + Reusable.generateRandomNumber() + "@gmail.com";
		password = Reusable.generateRandomNumber();
		userPayload.put("name", name);
		userPayload.put("email", email);
		userPayload.put("password", password);
	}

	@When("I send a post request with user payload")
	public static void i_send_a_post_request_with_user_payload() {
		response = Endpoint.registerUser(userPayload.toString());
	}

	@Then("I validate that user is registred")
	public static void i_validate_that_user_is_registred() {
		Assert.assertEquals("user was not able to register successfully", response.statusCode(), 200);
		Assert.assertEquals("Name was not addedcorrectly", response.jsonPath().getString("data.Name"), name);
	}

	@Given("I have {string} and {int}")
	public void i_have_and(String email, Integer pwd) {
		loginData.put("email", email);
		loginData.put("password", pwd);
	}

	@When("I send a Post request for login")
	public void i_send_a_post_request_for_login() {
		response = Endpoint.login(loginData.toString());
	}

	@Then("I validate user is successfully loggedIn")
	public void i_validate_user_is_successfully_logged_in() {
		Assert.assertTrue("login was unsuccessful",
				response.jsonPath().getString("data.Email").equals(loginData.get("email")));
	}

	@Given("^The value should be (true|false)$")
	public void the_value_should_be_true(Boolean value) {
		System.out.println(value);
	}

	@When("I send a GET request")
	public void i_send_a_post_request() throws IOException {
		byte[] fileContent = Endpoint.downloadFile();
		OutputStream os = new FileOutputStream(new File("src\\test\\resources\\data\\Homework-Java-001.pdf"));
		os.write(fileContent);
	}

	@Then("I validate the file is downloaded")
	public void i_validate_the_file_is_downloaded() {

	}

}
