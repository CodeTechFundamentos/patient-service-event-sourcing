Feature: Register a new Patient

  Scenario Outline: As a patient i want to sign up.

    Given I can sign up as a patient
    And I sending register form to be created with my email<email>
    Then I should be able to see my newly created patient account
    Examples:
      | email              |
      | "bill@gmail.com"   |

  Scenario Outline: As a patient i want to sign up with an used email

    Given I can sign up as a patient
    And I sending register form to be created with an used email <email>
    Then I should be able to see an error message <message>
    Examples:
      | email              | message           |
      | "bill@gmail.com"   | "Email ya en uso" |