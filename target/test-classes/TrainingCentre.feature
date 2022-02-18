@ip
Feature: Training Centre
  Sparta has 3 different types of centres. Centre No 1, 2 and 3
  represents Training hub, Bootcamp, Tech Centre

  Scenario: Maximum 3 training hubs open in one month
    Given user enters 1 to start simulation
    When the program is generating 90 trainees in this month
    When no bootcamp opened
    When no tech centre opened
    Then maximum 3 training hubs should be opened
    Then simulation should be ended

  Scenario: Minimum 1 training hub opened in one month
    Given user enters 1 to start simulation
    When the program is generating 90 trainees in this month
    When no bootcamp opened
    When no tech centre opened
    Then minimum 1 training hub should be opened
    Then simulation should be ended

  Scenario: Only one bootcamp open in one month
    Given user enters 1 to start simulation
    When the program is generating 70 trainees in this month
    When no training hub opened
    When no tech centre opened
    Then 1 centre type 2 should be opened in month 0
    Then bootcamp 1 takes 50 new trainees
    Then simulation should be ended

  Scenario: Only two bootcamp open throughout simulation
    Given user enters 4 to start simulation
    When the program is generating 80 trainees in this month
    When no training hub opened
    When no tech centre opened
    Then 1 centre type 2 should be opened in month 0
    Then bootcamp 1 takes 50 new trainees
    When the program is generating 50 trainees in this month
    Then no training hub opened
    Then no tech centre opened
    Then no bootcamp opened
    When bootcamp 1 takes 30 new trainees
    When the program is generating 60 trainees in this month
    Then no training hub opened
    Then no tech centre opened
    Then 1 centre type 2 should be opened in month 2
    Then bootcamp 1 takes 50 new trainees
    Then bootcamp 2 takes 40 new trainees
    When the program is generating 90 trainees in this month
    Then no training hub opened
    Then no tech centre opened
    Then no bootcamp opened
    Then bootcamp 2 takes 30 new trainees
    When the program is generating 50 trainees in this month
    Then 1 training hub opened
    Then 1 centre type 1 should be opened in month 4
    Then no tech centre opened
    Then bootcamp should not be opened
    Then simulation should be ended
