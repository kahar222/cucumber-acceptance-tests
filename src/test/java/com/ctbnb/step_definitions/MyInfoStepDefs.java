package com.ctbnb.step_definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.ctbnb.pages.SelfPage;
import com.ctbnb.pages.SigninPage;
import com.ctbnb.pages.TeamPage;
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
		signInPage.password.sendKeys(password+ Keys.ENTER);
//		signInPage.signInButton.click();
	}

	@When("the user is on the my self page")
	public void the_user_is_on_the_my_self_page() {
		SelfPage selfPage = new SelfPage();
		selfPage.goToSelf();
	}

	@Then("user info should match the db records using {string}")
	public void user_info_should_match_the_db_records_using(String email) {
		String sql = "select firstname, lastname, role from users where email = '" + email + "';";
		System.out.println(sql);
		List<Map<String, Object>> result = DBUtils.getQueryResultMap(sql);
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

	@When("the user is on the my team page")
	public void the_user_is_on_the_my_team_page() {
		SelfPage selfPage = new SelfPage();
		selfPage.goToTeam();
		BrowserUtils.waitFor(2);

	}

	@Then("team info should match the db records using {string}")
	public void team_info_should_match_the_db_records_using(String email) {
		TeamPage teamPage = new TeamPage();

		List<String> actualNames = new ArrayList<>();
		for (WebElement el : teamPage.teamMemberNames) {
			actualNames.add(el.getText());
		}

		String query = "SELECT u1.firstname, u1.lastname, u1.role FROM users u1 INNER JOIN users u2 ON (u1.team_id = u2.team_id) where u2.email='"
				+ email + "'";

		System.out.println(query);
		List<Map<String, Object>> queryResult = DBUtils.getQueryResultMap(query);

		assertEquals(queryResult.size(), actualNames.size());

		for (Map<String, Object> map : queryResult) {
			String fullName = map.get("firstname") + " " + map.get("lastname");
			assertTrue(fullName + " was not found", actualNames.contains(fullName));
		}

	}

}
