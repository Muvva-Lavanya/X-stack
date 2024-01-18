Feature: Changing User Password

  Scenario: User updates their password successfully
    Given the user has a valid update password request
    When the user submits the update password request
    Then the system should update the user's password successfully
    And the system should respond with HTTP status code 200



