Feature: Waiting List

  Scenario Outline: If trainees goes to bootcamp, then correct amount of trainees will be waiting list
    When user enters 1 to start simulation
    When the program is generating <numberOfTraineesInThisMonth> trainees in this month
    When 1 bootcamp should be opened and takes <numberOfTraineesGoingToBootCamp> new trainees
    Then <numberOfTraineesInWaitingList1> trainees should be in waiting list
    When the program is generating <numberOfTrainees> trainees in this month
    When no bootcamp opened
    When bootcamp 1 takes <newTrainees> new trainees
    Then <numberOfTraineesInWaitingList2> trainees should be in waiting list
    Examples:
      | numberOfTraineesInThisMonth | numberOfTraineesGoingToBootCamp | numberOfTraineesInWaitingList1 | numberOfTraineesInWaitingList2 | numberOfTrainees | newTrainees |
      | 80                          | 40                              | 40                             | 90                             | 100              | 50          |
      | 100                         | 30                              | 70                             | 130                            | 80               | 20          |
      | 50                          | 50                              | 0                              | 0                              | 10               | 10          |

  Scenario Outline:
    When user enters 1 to start simulation
    When the program is generating <traineesToGenerate> trainees in this month
    When 1 training hub should be opened and takes <newTrainees> new trainees
    Then <numberOfTraineesInWaitingList> trainees should be in waiting list
    Examples:
      | traineesToGenerate | newTrainees | numberOfTraineesInWaitingList |
      | 80                 | 40          | 40                            |
      | 70                 | 30          | 40                            |

  Scenario Outline:
    When user enters 3 to start simulation
    When the program is generating <traineesInMonth0> trainees in this month
    When 1 tech centre should be opened and takes new trainees
    Then only one type trainee should be in tech centre
    Then only this type of trainee should be decreased in waiting list
    When the program is generating <traineesInMonth1> trainees in this month
    Then no centre opened
    When the program is generating <traineesInMonth2> trainees in this month
    When 1 tech centre should be opened and takes new trainees
    Then only one type trainee should be in tech centre
    Then only this type of trainee should be decreased in waiting list
    Examples:
      | traineesInMonth0 | traineesInMonth1 | traineesInMonth2 |
      | 60               | 50               | 80               |



