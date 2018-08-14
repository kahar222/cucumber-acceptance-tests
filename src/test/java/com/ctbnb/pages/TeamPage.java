package com.ctbnb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prestashop.utilities.Driver;

public class TeamPage extends TopNavigationBar {
	public TeamPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	

}
