Feature: Groceries
  Check the list of groceries and the details of a particular grocery

  # Groceries list contains 10 static elements
  Scenario: User should see groceries list when opened the app
    Given The app started
    Then I see Groceries list screen

  Scenario: User should exit the app when pressing back in groceries list screen
    Given The app started
    And I see Groceries list screen
    When I press back
    Then I should exit the app

  Scenario: User clicks on help action item and is navigated to Help webpage
    Given The app started
    And I see Groceries list screen
    When I click on question mark action item
    Then I see Help web page

  Scenario: User clicks on list item and is navigated to grocery details
    Given The app started
    And I see Groceries list screen
    When I click on first list item
    Then I see "Bread" grocery details screen

  Scenario: User clicks on last list item and is navigated to grocery details
    Given The app started
    And I see Groceries list screen
    When I click on last list item
    Then I see "Honey" grocery details screen

  Scenario: User clicks on the list item and is navigated to grocery details
    Given The app started
    And I see Groceries list screen
    When I click on "Cheese" list item
    Then I see "Cheese" grocery details screen

  Scenario: User should be able to clear the grocery note
    Given The app started
    And I navigate to "Pasta" grocery details screen
    And I input "Buy some more for spaghetti bolognese" note
    When I press Clear
    Then I see empty note

  Scenario: User deletes grocery item
    Given The app started
    And I navigate to "Milk" grocery details screen
    When I click on Delete menu item
    And I see delete confirmation dialog
    Then I confirm the deletion