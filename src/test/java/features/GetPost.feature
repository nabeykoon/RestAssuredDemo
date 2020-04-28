Feature:
  Verify different GET operations using  REST-assured

  Scenario: Verify one author of the post
    Given I perform GET operation for "/posts"
    Then I should see the author name as "Karthik KK"

  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/posts"
    Then I should see the author names

  Scenario: Verify parameter of get
    Given I perform GET operation for "/posts"
    Then I should verify GET parameter

  Scenario: Verify queryParam of get
    Given I perform GET operation for "/posts"
    Then I should verify GET queryParameter

