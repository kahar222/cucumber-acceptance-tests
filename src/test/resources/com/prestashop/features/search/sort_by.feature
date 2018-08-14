Feature: Sorting options on the seach result page 

Scenario Outline: Sort by <option> 
	Given the user is on the home page 
	And the user clicks on link Dresses 
	When user sorts by "<option>" 
	Then following product should be displayed on top 
		|name |<name> |
		|price|<price>|
		
	Examples: 
		|option				 	 |name				     | price |
		|Price: Lowest first		 |Printed Chiffon Dress  | $16.40|
		|Price: Highest first	 |Printed Dress			 | $50.99|
		|Product Name: A to Z	 |Printed Chiffon Dress  | $16.40|
		|Product Name: Z to A	 |Printed Summer Dress   | $28.98|
		|In stock				 |Printed Chiffon Dress  | $16.40|
		|Reference: Lowest first |Printed Dress			 | $26.00|
		|Reference: Highest first|Printed Dress			 | $26.00|
		
Scenario: Test sort by dropdown 
	Given the user is on the home page 
	When the user clicks on link Dresses 
	Then the user should be able to sort results
	
	
	
	
	
