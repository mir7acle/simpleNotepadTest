Feature: 2. Home page with three Arrivals only

  @ui
  Scenario: Home page with three Arrivals only
    Given browser "chrome"
    And website loaded this address: "http://practice.automationtesting.in/"
    When click on Shop Menu
    And click on Home menu button
    Then the Home page must contains only three Arrivals
