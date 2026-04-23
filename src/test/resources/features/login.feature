Feature: Login and inventory validation

  @smoke
  Scenario: Successful login and add product to cart
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the inventory page should be displayed
    And the user adds a backpack to the cart
    Then the cart badge count should be "1"

  @negative
  Scenario: Invalid login shows error message
    Given the user is on the login page
    When the user logs in with username "locked_out_user" and password "wrong_password"
    Then an error message should be displayed
