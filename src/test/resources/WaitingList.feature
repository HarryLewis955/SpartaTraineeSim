Feature: Waiting List

  @Scenario
  Scenario Outline:
    When user enters <monthsToRunSimulationFor> to start simulation
    When the program is generating <numberOfTraineesInThisMonth> trainees in this month
    When 1 bootcamp should be opened and takes <numberOfTrainees> new trainees
    Then <numberOfTraineesInWaitingList> trainees should be in waiting list
    Examples:
      | monthsToRunSimulationFor | numberOfTraineesInThisMonth | numberOfTrainees | numberOfTraineesInWaitingList |
      | 1                        | 80                          | 40               | 40                            |
      | 2                        | 100                         | 30               | 70                            |

