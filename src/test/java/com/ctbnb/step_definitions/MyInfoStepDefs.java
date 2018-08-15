package com.ctbnb.step_definitions;

import com.ctbnb.pages.SigninPage;
import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.Driver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyInfoStepDefs {

	@Given("user logs in using {string} {string}")
	public void user_logs_in_using(String username, String password) {
		Driver.getDriver().get(ConfigurationReader.getProperty("qa1_url"));
		SigninPage signInPage = new SigninPage();
		signInPage.email.sendKeys(username);
		signInPage.password.sendKeys(password);
		signInPage.signInButton.click();
		System.out.println();
	}

	@When("the user is on the my self page")
	public void the_user_is_on_the_my_self_page() {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("user info should match the db records")
	public void user_info_should_match_the_db_records() {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}
