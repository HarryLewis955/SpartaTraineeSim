Feature: Waiting List
  Note:
  Centre Type 1: Training Hub
  Centre Type 2: Bootcamp
  Centre Type 3: Tech Centre

  Scenario Outline: If trainees goes to bootcamp, then correct amount of trainees will be waiting list
    When user enters 1 to start simulation
    When the program is generating <numberOfTraineesInThisMonth> trainees in this month
    When 1 centre type 2 should be opened in month 0
    When centres take <numberOfTraineesGoingToBootcamp> new trainees
    Then <numberOfTraineesInWaitingList1> trainees should be in waiting list
    When the program is generating <numberOfTrainees> trainees in this month
    Then 0 centre type 1 should be opened in month 1
    Then 0 centre type 2 should be opened in month 1
    Then 0 centre type 3 should be opened in month 1
    When centres take <newTrainees> new trainees
    Then <numberOfTraineesInWaitingList2> trainees should be in waiting list
    Examples:
      | numberOfTraineesInThisMonth | numberOfTraineesGoingToBootcamp | numberOfTraineesInWaitingList1 | numberOfTraineesInWaitingList2 | numberOfTrainees | newTrainees |
      | 80                          | 40                              | 40                             | 90                             | 100              | 50          |
      | 100                         | 30                              | 70                             | 130                            | 80               | 20          |
      | 50                          | 50                              | 0                              | 0                              | 10               | 10          |

  Scenario Outline:
    When user enters 1 to start simulation
    When the program is generating <traineesToGenerate> trainees in this month
    Then 1 centre type 2 should be opened in month 0
    When 0 centre type 1 should be opened in month 0
    Then 0 centre type 3 should be opened in month 0
    When centres take <newTrainees> new trainees
    Then <numberOfTraineesInWaitingList> trainees should be in waiting list
    Examples:
      | traineesToGenerate | newTrainees | numberOfTraineesInWaitingList |
      | 80                 | 40          | 40                            |
      | 70                 | 30          | 40                            |

  Scenario Outline:
    When user enters 1 to start simulation
    When the program is generating <traineesInMonth0> trainees in this month
    When 1 centre type 3 should be opened in month 0
    Then 0 centre type 1 should be opened in month 0
    Then 0 centre type 2 should be opened in month 0
    Then centres take 30 new trainees
    Then only one type trainee should be in tech centre
    Examples:
      | traineesInMonth0 |
      | 60               |
      | 67               |
      | 89               |
      | 95               |



