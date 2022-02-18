Feature: Training Centre 
  Sparta has 3 different types of centres. Training hub, Bootcamp, Tech Centre

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

  Scenario: Only two training hubs open in two months
    Given user enters 1 to start simulation
    When the program is generating 100 trainees in this month
    When no bootcamp opened
    When no tech centre opened
    When 2 training hub opened
    Then training hub 1 takes new 50 trainees
    Then training hub 2 takes new 50 trainees
    When the program is generating 100 trainees in this month
    Then no bootcamp opened
    Then no tech centre opened
    Then no training hub opened
    Then training hub 1 takes new 50 trainees
    Then training hub 2 takes new 50 trainees
    Then training hub 1 is full of 100 trainees
    Then training hub 2 is full of 100 trainees
    Then simulation should be ended

  Scenario: Only one bootcamp open in one month
    Given user enters 1 to start simulation
    When the program is generating 70 trainees in this month
    When no training hub opened
    When no tech centre opened
    Then 1 bootcamp should be opened
    Then bootcamp 1 takes 50 new trainees
    Then simulation should be ended

  Scenario: Only two bootcamp open throughout simulation
    Given user enters 4 to start simulation
    When the program is generating 80 trainees in this month
    When no training hub opened
    When no tech centre opened
    Then 1 bootcamp should be opened
    Then bootcamp 1 takes 50 new trainees
    When the program is generating 50 trainees in this month
    Then no training hub opened
    Then no tech centre opened
    Then no bootcamp opened
    When the program is generating 60 trainees in this month
    Then no training hub opened
    Then no tech centre opened
    Then 1 bootcamp should be opened
    Then bootcamp 1 takes 50 new trainees
    Then bootcamp 2 takes 40 new trainees
    When the program is generating 90 trainees in this month
    Then no training hub opened
    Then no tech centre opened
    Then no bootcamp opened
    When the program is generating 50 trainees in this month
    Then no bootcamp opened
    Then simulation should be ended


