Feature: Information about me

@db @temp
Scenario: my self
	Given user logs in using "efewtrell8c@craigslist.org" "jamesmay"
	When the user is on the my self page
	Then user info should match the db records using "efewtrell8c@craigslist.org"