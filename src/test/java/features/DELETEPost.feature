Feature:
  Verify different DELETE operations using  REST-assured


  Scenario: Verify DELETE operation after POST
    Given I ensure to perform post operation for "/posts" with body as
      | id | title              | author            |
      | 10  | API Testing course | ExecuteAutomation |
    And I perform DELETE operation for "posts/{postId}/"
      | postId |
      | 10      |
    And I perform GET operation with path parameter for "/posts/{postId}"
      | postId |
      | 10      |
    Then I should not see the body with title as "API Testing course"