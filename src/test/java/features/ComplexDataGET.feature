Feature: ComplexDataGET
  Verify complex data

  Scenario: Verify GET operation for complex data
    Given I perform authentication operation for "/auth/login" with body
      | email             | password |
      | nilson3@email.com | nilson   |
    Given I perform GET operation with query parameter for address "/location"
      | id |
      | 1  |
    Then I should see the street name as "1st street" for the "primary" address