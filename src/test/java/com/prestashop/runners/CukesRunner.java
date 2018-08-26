package com.prestashop.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
				"pretty",
				"html:target/default-cucumber-reports", //generates every time we run the cukes runner
				"json:target/cucumber.json"
		
		},
		
		tags="@login",
		features= {"src/test/resources/com/prestashop/features", 
				"src/test/resources/com/ct_bnb/features"}, 
		glue= {"com/prestashop/step_definitions", "com/ctbnb/step_definitions" }
//		,dryRun = true
		)
public class CukesRunner {}
