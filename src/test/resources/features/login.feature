Feature: Login feature
    @login
    Scenario Outline: scenario description
    	    Given I have "<email>" and <password>
    	    When I send a Post request for login 
    		Then I validate user is successfully loggedIn
    		
    		Examples:
    		| email | password |
    		| YFRETWK35105506@gmail.com | 51184654 |
    		| ZZCMRFA56707665@gmail.com | 92276653 |