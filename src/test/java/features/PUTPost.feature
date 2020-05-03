Feature:
  Verify different PUT operations using  REST-assured


  Scenario: Verify PUT operation after POST
    Given I ensure to perform post operation for "/posts" with body as
      | id | title              | author            |
      | 10 | API Testing course | ExecuteAutomation |
    And I perform PUT operation for "posts/{postId}/"
      | id | title       | author            |
      | 10 | API Testing | ExecuteAutomation |
    And I perform GET operation with path parameter for "/posts/{postId}"
      | postId |
      | 10     |
    Then I should see the body with title as "API Testing"