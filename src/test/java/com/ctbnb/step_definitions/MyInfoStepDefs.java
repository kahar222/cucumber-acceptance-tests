package com.ctbnb.step_definitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import com.ctbnb.pages.SelfPage;
import com.ctbnb.pages.SigninPage;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.DBUtils;
import com.prestashop.utilities.Driver;

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
	}

	@When("the user is on the my self page")
	public void the_user_is_on_the_my_self_page() {
		SelfPage selfPage = new SelfPage();
		selfPage.goToSelf();
	}

	@Then("user info should match the db records using {string}")
	public void user_info_should_match_the_db_records_using(String email) {
		String sql = "select firstname, lastname, role from users\n" + "where email = 'efewtrell8c@craigslist.org';";

		List<Map<String, Object>> result = DBUtils.getQueryResult(sql);
		// per requirements, we cannot have duplicated emails
		assertEquals("Returned multiple users with email: " + email, 1, result.size());

		Map<String, Object> map = result.get(0);

		String expectedFirstName = (String) map.get("firstname");
		String expectedLastName = (String) map.get("lastname");
		String expectedRole = (String) map.get("role");

		SelfPage selfPage = new SelfPage();

		BrowserUtils.waitFor(2);
		String aFirstname = selfPage.name.getText().split(" ")[0];

		String aLastname = selfPage.name.getText().split(" ")[1];

		String aRole = selfPage.role.getText();

		assertEquals(expectedFirstName, aFirstname);
		assertEquals(expectedLastName, aLastname);
		assertEquals(expectedRole, aRole);

	}

}
