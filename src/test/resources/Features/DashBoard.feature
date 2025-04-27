Feature: Dashboard

  Background: Testing Dashboard Page of Selenium Automation Testing Playground

  @Test
  Scenario Template:1: Validate Basic Input Section
    Given load CSV Data from "<Path>" for header row contains "user1"
    When User Navigates to Base URL
    Then Validate value of "<Header>" and "<Title>"
    And User Enters Required Values "<filePath>"
    Examples:
      | Header                                 | Title                                  | Path                                         | filePath                                            |
      | Selenium Automation Testing Playground | Selenium Test Playground & Stock Chart | src/test/resources/TestData/csv/tetsData.csv | src/test/resources/TestData/pdf/Welcome to Word.pdf |

  @Test
  Scenario:2: Validate Basic Input Section
    When User Navigates to Base URL
    Then Validate selection part
      | Alice | Engineer |

#  @Test
  Scenario:3: Validate Basic Input Section
    When User Navigates to Base URL
    Then Validate Buttons and Actions

  @Test
  Scenario:4: Checking the Dynamic Xpath case
    When User Navigates to Base URL
    Then Validate dynamic items

#  @Test
  Scenario Template:5: Checking the API cases
    When User Navigates to Base URL
    Then take reponse from "<URL>"  and using "<METHOD>" method with "<PATH>"
    Examples:
      | URL                | METHOD | PATH             |
      | https://reqres.in/ | GET    | api/users?page=2 |




