Feature: Register an account
  New user should be able to create an account

  Scenario Outline: Creating an account with valid and invalid data
    Given User navigates to Mailchimp website
    And User enters an email "<email>"
    And User enters a username "<username>"
    And User enters a password "<password>"
    And User accepts cookies
    And User scrolls down
    When User clicks on the signup button
    Then The output message "<outputMessage>" should be displayed
    Examples:
      | email      | username         | password  | outputMessage                                                                      |
      | randomMail | randomUsername   | Hello123! | Check your email                                                                   |
      | randomMail | longUsername     | Hello123! | Enter a value less than 100 characters long                                        |
      | randomMail | existingUsername | Hello123! | Another user with this username already exists. Maybe it's your evil twin. Spooky. |
      | emptyMail  | randomUsername   | Hello123! | Please enter a value                                                               |



