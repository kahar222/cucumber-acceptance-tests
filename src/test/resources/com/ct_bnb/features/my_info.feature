Feature: Information about me

@db
Scenario: my self
	Given user logs in using "efewtrell8c@craigslist.org" "jamesmay"
	When the user is on the my self page
	Then user info should match the db records using "efewtrell8c@craigslist.org"
	
	
	
@db @temp
Scenario: my self
	Given user logs in using "efewtrell8c@craigslist.org" "jamesmay"
	When the user is on the my team page
	Then team info should match the db records using "efewtrell8c@craigslist.org"
	
	
	
		