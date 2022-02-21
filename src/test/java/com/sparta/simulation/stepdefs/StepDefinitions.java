package com.sparta.simulation.stepdefs;

import com.sparta.simulation.model.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class StepDefinitions {

    CentreModel centreModel;
    ClientModel clientModel;
    Centre centre;
    ArrayList<Centre> centreList;
    int centreCapacity;
    ArrayList<Integer> waitingList = new ArrayList<>();
    List<Integer> listWithoutDuplicates = new ArrayList<>();
    List<Integer> descendingBench = new ArrayList<>();
    ArrayList<Centre> closedCentres = new ArrayList<>();
    ArrayList<Integer> centresToClose = new ArrayList<>();
    List<Integer> temporaryList = new ArrayList<>();
    List<Client> clientList = new ArrayList<>();
    ArrayList<Integer> bench = new ArrayList<>();
    ArrayList<int[]> traineesInTraining = new ArrayList<>();
    List<Client> closedClients = new ArrayList<>();
    int[] monthlyTrainees = new int[6];

    int javaCount = 0;
    int csharpCount = 0;
    int dataCount = 0;
    int devopsCount = 0;
    int businessCount = 0;
    int idCount = 0;
    int bootCampCount = 0;
    int clientIdCount = 0;


    int currentMonth = 0;

    boolean isCentreOpened = false;

    @Before
    public void setUp() {
        centreModel = new CentreModel();
        centreList = new ArrayList<>();
    }

    @After
    public void close() {
        centreModel = null;
        centre = null;
        centreList = null;
        waitingList = null;
        traineesInTraining = null;
    }


    @Given("user enters {int} to start simulation")
    public void userEntersToStartSimulation(int monthsToRunSimulationFor) {
        System.out.println("Assume that user enters " + monthsToRunSimulationFor + " to start the programme");
        // assume that user entered a number
    }

    @When("the program is generating {int} trainees in this month")
    public void theProgramIsGeneratingTraineesInThisMonth(int numberOfTrainees) {

        TraineeModel.createTraineeAddToWaitingList(numberOfTrainees, waitingList);
        int i = 0;
        for (int java : waitingList) {
            if (java == 1) {
                i++;
            }
        }
        System.out.println("Arrays.toString(trainee) = " + Arrays.toString(monthlyTrainees));
        traineesInTraining.add(monthlyTrainees);
        currentMonth++;
        System.out.println("currentMonth = " + currentMonth);
        System.out.println("waitingList.size() = " + waitingList.size());
    }

    @Then("maximum {int} training hubs should be opened")
    public void maximumTrainingHubsShouldBeOpened(int maxTrainingHubsOpened) {
        if (currentMonth % 2 == 0) {
            int numberOfTrainingHubsOpened = centreModel.createCentre(centreList, centreCapacity, centre.getCentreType(), 0, maxTrainingHubsOpened, 0);
            Assertions.assertTrue(maxTrainingHubsOpened >= numberOfTrainingHubsOpened);
        }
    }

    @Then("minimum {int} training hub should be opened")
    public void minimumTrainingHubShouldBeOpened(int minTrainingHubsOpened) {
        if (currentMonth % 2 == 0) {
            int numberOfTrainingHubsOpened = centreModel.createCentre(centreList, centreCapacity, centre.getCentreType(), 0, minTrainingHubsOpened, 0);
            Assertions.assertTrue(minTrainingHubsOpened <= numberOfTrainingHubsOpened);
        }
    }

    @Then("simulation should be ended")
    public void simulationShouldBeEnded() {
        System.out.println("Simulation should be ended");
    }

    @Then("{int} trainees should be in waiting list")
    public void traineesShouldBeInWaitingList(int traineesInWaitingList) {
        Assertions.assertEquals(traineesInWaitingList, waitingList.size());
//        Assertions.assertEquals(traineesInWaitingList, test);
    }

    @Then("only one type trainee should be in tech centre")
    public void only_one_type_trainee_should_be_in_tech_centre() {
        for (Centre centre : centreList) {
            if (centre.getCentreType() == 3) {
                if (centre.getJavaCount() != 0) {
                    Assertions.assertEquals(0, centre.getBusinessCount());
                    Assertions.assertEquals(0, centre.getCsharpCount());
                    Assertions.assertEquals(0, centre.getDataCount());
                    Assertions.assertEquals(0, centre.getDevopsCount());
                } else if (centre.getDevopsCount() != 0) {
                    Assertions.assertEquals(0, centre.getBusinessCount());
                    Assertions.assertEquals(0, centre.getCsharpCount());
                    Assertions.assertEquals(0, centre.getDataCount());
                    Assertions.assertEquals(0, centre.getJavaCount());
                } else if (centre.getDataCount() != 0) {
                    Assertions.assertEquals(0, centre.getBusinessCount());
                    Assertions.assertEquals(0, centre.getCsharpCount());
                    Assertions.assertEquals(0, centre.getDevopsCount());
                    Assertions.assertEquals(0, centre.getJavaCount());
                } else if (centre.getCsharpCount() != 0) {
                    Assertions.assertEquals(0, centre.getBusinessCount());
                    Assertions.assertEquals(0, centre.getDataCount());
                    Assertions.assertEquals(0, centre.getDevopsCount());
                    Assertions.assertEquals(0, centre.getJavaCount());
                } else if (centre.getBusinessCount() != 0) {
                    Assertions.assertEquals(0, centre.getCsharpCount());
                    Assertions.assertEquals(0, centre.getDataCount());
                    Assertions.assertEquals(0, centre.getDevopsCount());
                    Assertions.assertEquals(0, centre.getJavaCount());
                }
            }
        }
    }

    @Then("{int} centre type {int} should be opened in month {int}")
    public void centreTypeShouldBeOpenedInMonth(int numberOfCentre, int centreNo, int currentMonth) {

        if (isCentreOpened && numberOfCentre > 0) {
            if (currentMonth % 2 == 0) {
                Assertions.fail("You can't open more than one time in a month");
            }
        }
        // get number of open centres number before adding new centres
        int numberOfCentersOpenedBefore = centreList.size();

        int numberOfCentersOpened = 0;
        if (currentMonth % 2 == 0) {
            numberOfCentersOpened = centreModel.createCentre(centreList,
                    20, centreNo, 0, numberOfCentre, 0);
            isCentreOpened = true;
            // get number of open centres number after adding new centres
            int numberOfOpenCentresAfter = centreList.size();

            if (centreNo == 2 || centreNo == 3) {
                Assertions.assertTrue(numberOfCentersOpened == 1);
                Assertions.assertTrue(numberOfOpenCentresAfter - numberOfCentersOpenedBefore == 1);
            } else if (centreNo == 1) {
                Assertions.assertTrue(numberOfCentersOpened == numberOfCentre);
                Assertions.assertTrue(numberOfOpenCentresAfter - numberOfCentersOpenedBefore <= 3);
            } else {
                Assertions.fail("Not acceptable centre no");
            }
        } else {
            isCentreOpened = false;
            if (numberOfCentre != 0) {
                Assertions.fail("You can't open a centre in odd months.");
            }
        }
        System.out.println("centreList.size() = " + centreList.size());
    }

    @Then("new bootcamp should not be opened")
    public void newBootcampShouldNotBeOpened() {
        int sizeOld = centreList.size();
        CentreModel.centerTypeGen(centreModel.bootcampCheck(centreList, bootCampCount), 2);
        int sizeNew = centreList.size();
        Assertions.assertTrue(sizeNew == sizeOld);
    }

    @When("centres take {int} new trainees")
    public void centresTakeNewTrainees(int numberOfTrainees) {
        centreModel.centreCapacity(centreList, numberOfTrainees);
        System.out.println("waitingList.size() = " + waitingList.size());
        centreModel.addToCentre(waitingList, centreList, numberOfTrainees, monthlyTrainees);
         int test = waitingList.size();
//        System.out.println("waitingList.size() after adding to centre = " + waitingList.size());
        System.out.println("waitingList.size() after adding to centre = " + test);
    }

    @Then("{int} months passed")
    public void monthsPassed(int months) {
        CentreModel.checkAttend(centreList, centresToClose);
    }

    @Then("centres should not be closed")
    public void centresShouldNotBeClosed() {
        Assertions.assertTrue(closedCentres.size() == 0);
    }

    @Then("check centre attendance and try to close it")
    public void checkCentreAttendanceAndTryToCloseIt() {
        temporaryList = centresToClose.stream().distinct().toList();
        new LinkedList<>(temporaryList).descendingIterator().forEachRemaining(listWithoutDuplicates::add);
        centreModel.closeCentre(listWithoutDuplicates, centreList, closedCentres, waitingList);
    }

    @Then("centres should be closed if attendance lower than twenty five")
    public void centresShouldBeClosedIfAttendanceLowerThanTwentyFive() {
        System.out.println("temporaryList.size() = " + temporaryList.size());
        Assertions.assertEquals(0, centreList.size());
    }
}
