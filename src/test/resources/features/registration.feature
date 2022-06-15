Feature: user registration
    @register
    Scenario: New user registration
        Given I have the user payload
        When I send a post request with user payload
        Then I validate that user is registred
	    