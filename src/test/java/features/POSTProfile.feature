Feature:
  Verify different POST operations using  REST-assured

  Scenario: Verify post operation
    Given I perform POST operation for "/posts"

  Scenario: Verify Post operation for Profile
    Given I perform post operation for "/posts/{profileNo}/profile" with body
      | name | profile |
      | Sams | 2       |
    Then I should see the body has name as "Sams"