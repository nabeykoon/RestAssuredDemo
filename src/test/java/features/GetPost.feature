Feature:
  Verify different GET operations using  REST-assured

  Scenario: Verify one author of the post
    Given I perform GET operation for "/post"
    And I perform GET for the post number "1"
    Then I should see the author name as "Karthik kk"

  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/post"
    Then I should see the author names

  Scenario: Verify parameter of get
    Given I perform GET operation for "/post"
    Then I should verify GET parameter

  Scenario: Verify queryParameter of get
    Given I perform GET operation for "/post"
    Then I should verify GET queryParameter