@ip
Feature: Training Centre
  Sparta has 3 different types of centres. Centre No 1, 2 and 3
  represents Training hub, Bootcamp, Tech Centre

  Scenario: Maximum 3 training hubs open in one month
    Given user enters 1 to start simulation
    When the program is generating 90 trainees in this month
    Then maximum 3 training hubs should be opened
    Then simulation should be ended

  Scenario: Minimum 1 training hub opened in one month
    Given user enters 1 to start simulation
    When the program is generating 90 trainees in this month
    Then minimum 1 training hub should be opened
    Then simulation should be ended

  Scenario: Only one bootcamp open in one month
    Given user enters 1 to start simulation
    When the program is generating 70 trainees in this month
    When 1 centre type 2 should be opened in month 0
    Then 0 centre type 1 should be opened in month 0
    Then 0 centre type 3 should be opened in month 0
    Then centres take 50 new trainees
    Then 20 trainees should be in waiting list
    Then simulation should be ended

  Scenario: Only two bootcamp open throughout simulation
    Given user enters 4 to start simulation
    When the program is generating 80 trainees in this month
    Then 1 centre type 2 should be opened in month 0
    Then 0 centre type 1 should be opened in month 0
    Then 0 centre type 3 should be opened in month 0
    Then centres take 50 new trainees
    When the program is generating 50 trainees in this month
    Then 0 centre type 1 should be opened in month 1
    Then 0 centre type 2 should be opened in month 1
    Then 0 centre type 3 should be opened in month 1
    When centres take 30 new trainees
    When the program is generating 60 trainees in this month
    Then 1 centre type 2 should be opened in month 2
    Then 0 centre type 1 should be opened in month 2
    Then 0 centre type 3 should be opened in month 2
    Then centres take 50 new trainees
    Then centres take 40 new trainees
    When the program is generating 90 trainees in this month
    Then 0 centre type 1 should be opened in month 3
    Then 0 centre type 2 should be opened in month 3
    Then 0 centre type 3 should be opened in month 3
    Then centres take 30 new trainees
    When the program is generating 50 trainees in this month
    Then 1 centre type 1 should be opened in month 4
    Then 0 centre type 2 should be opened in month 4
    Then 0 centre type 3 should be opened in month 4
    Then new bootcamp should not be opened
    Then simulation should be ended
