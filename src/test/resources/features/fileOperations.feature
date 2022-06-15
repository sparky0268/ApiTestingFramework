Feature: file opertions
    @file
    Scenario: download
       Given The value should be true
       When I send a GET request
       Then I validate the file is downloaded