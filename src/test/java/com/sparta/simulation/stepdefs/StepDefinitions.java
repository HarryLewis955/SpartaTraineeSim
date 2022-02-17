package com.sparta.simulation.stepdefs;

import com.sparta.simulation.Centre;
import com.sparta.simulation.CentreController;
import com.sparta.simulation.TraineeController;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.Ce;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StepDefinitions {

    public int generateRandom(int start, int end, ArrayList<Integer> exclude) {
        Random r = new Random();
        int range = end - start + 1 - exclude.size();
        int random = r.nextInt(range) + 1;

        for (int i = 0; i < exclude.size(); i++) {
            if (exclude.get(i) > random) {
                return random;
            }
            random++;
        }

        return random;
    }
    CentreController centreController;
    ArrayList<Integer> excludeBootcamp = new ArrayList<>(List.of(2));

    ArrayList<Integer> excludeTechCentre = new ArrayList<>(List.of(3));

    ArrayList<Integer> excludeTrainingHub = new ArrayList<>(List.of(1));

    Centre centre;

    ArrayList<Centre> centreList;
    int centreCapacity;
    ArrayList<Integer> waitingList = new ArrayList<>();

    @Before
    public void setUp() {
        centreController = new CentreController();
        centreList = new ArrayList<>();
    }

    @After
    public void close() {
        centreController = null;
        centre = null;
        centreList = null;
        waitingList = null;
    }


    @Given("user enters {int} to start simulation")
    public void userEntersToStartSimulation(int monthsToRunSimulationFor) {
        System.out.println("Assume that user enters " + monthsToRunSimulationFor + " to start the programme");
        // assume that user entered a number
    }

    @When("the program is generating {int} trainees in this month")
    public void theProgramIsGeneratingTraineesInThisMonth(int numberOfTrainees) {

        waitingList = TraineeController.createTrainee(numberOfTrainees, 0, 0, 0, 0, 0, waitingList);
        int i = 0;
        for (int java : waitingList) {
            if (java == 1) {
                i++;
            }
        }

        System.out.println("waitingList.size() = " + waitingList.size());
    }

    @When("no bootcamp opened")
    public void noBootcampOpened() {
//        Random r = new Random();
//        Integer centreType = generateRandom(1, 3, excludeBootcamp);
//        centreCapacity = r.nextInt(0, 51);
//        centre = new Centre(1, centreCapacity, centreType, 0);
        //    c.centreCapacity(centreList,centreCapacity);
        //   centreList.get(0).setCentreType();
    }

    @When("no tech centre opened")
    public void noTechCentreOpened() {
        centre = new Centre(1,0,generateRandom(1,3,excludeTechCentre)
            ,0);

        if(centre.getCentreType() == 3){
            centre.setCentreType(2);
        }
    }

    @Then("maximum {int} training hubs should be opened")
    public void maximumTrainingHubsShouldBeOpened(int maxTrainingHubsOpened) {
        int numberOfTrainingHubsOpened = centreController.createCentre(centreList, centreCapacity, centre.getCentreType(), 0, maxTrainingHubsOpened, 0);
        Assertions.assertTrue(maxTrainingHubsOpened >= numberOfTrainingHubsOpened);
    }

    @Then("minimum {int} training hub should be opened")
    public void minimumTrainingHubShouldBeOpened(int minTrainingHubsOpened) {
        int numberOfTrainingHubsOpened = centreController.createCentre(centreList, centreCapacity, centre.getCentreType(), 0, minTrainingHubsOpened, 0);
        Assertions.assertTrue(minTrainingHubsOpened <= numberOfTrainingHubsOpened);
    }

    @When("{int} training hub opened")
    public void trainingHubOpened(int arg0) {
        centreController.createCentre(centreList, centreCapacity, centre.getCentreType()
                , 0, arg0, 0);
    }

    @Then("no training hub opened")
    public void noTrainingHubOpened() {
        centre = new Centre(1,0,generateRandom(1,3,excludeTrainingHub)
        ,0);
    }

    @Then("simulation should be ended")
    public void simulationShouldBeEnded() {
    }

    @Then("training hub {int} is full of {int} trainees")
    public void trainingHubIsFullOfTrainees(int arg0, int arg1) {
        Assertions.assertTrue(centreList.get(arg0).getCentreCapacity() == 100);
        Assertions.assertEquals(arg1, centreList.get(arg0).getBusinessCount() + centreList.get(arg0).getCsharpCount() +
                centreList.get(arg0).getDataCount() + centreList.get(arg0).getDevopsCount() +
                centreList.get(arg0).getJavaCount());
    }

    @When("the program is generating {int} of trainees in this month")
    public void theProgramIsGeneratingOfTraineesInThisMonth(int arg0) {
    }

    @Then("{int} bootcamp should be opened")
    public void bootcampShouldBeOpened(int arg0) {
        centreController.createCentre(centreList, 20, 2, 0, 0, 0);
    }

    @Then("training hub {int} takes new {int} trainees")
    public void trainingHubTakesNewTrainees(int arg0, int arg1) {
        System.out.println("waitingList.size() before = " + waitingList.size());
        centreController.addToCentre(waitingList, centreList, waitingList.size());
        System.out.println("waitingList.size() = " + waitingList.size());
        //   c.addCentreOneTwo(centreList,waitingList,0);
        System.out.println(centreList.size() + "size");
        System.out.println(centreList.toString());
        System.out.println("This one" + centreList.get(arg0).getCentreCapacity());

        System.out.println(centreList.get(arg0).getJavaCount());
        Assertions.assertEquals(arg1, centreList.get(arg0).getBusinessCount() + centreList.get(arg0).getCsharpCount() +
                centreList.get(arg0).getDataCount() + centreList.get(arg0).getDevopsCount() +
                centreList.get(arg0).getJavaCount());
    }

    @When("bootcamp {int} takes {int} new trainees")
    public void bootcampTakesNewTrainees(int arg0, int arg1) {
        centreController.centreCapacity(centreList,arg1);
        centreController.addToCentre(waitingList,centreList,waitingList.size());
        System.out.println("waitingList.size() after adding to centre = " + waitingList.size());
    }

    @When("{int} bootcamp should be opened and takes {int} new trainees")
    public void bootcampOpenedAndTakesNewTrainees(int arg0, int numberOfNewTrainees) {
        centreController.createCentre(centreList,numberOfNewTrainees,2,1,0,0);
        centreController.addToCentre(waitingList,centreList,waitingList.size());
    }

    @Then("{int} trainees should be in waiting list")
    public void traineesShouldBeInWaitingList(int arg0) {
        Assertions.assertEquals(arg0,waitingList.size());
    }

    @When("{int} training hub should be opened and takes {int} new trainees")
    public void training_hub_should_be_opened_and_takes_new_trainees(Integer int1, Integer int2) {
        centreController.createCentre(centreList,int2,1,1,1,0);
        centreController.addToCentre(waitingList,centreList,waitingList.size());
    }

    @When("{int} tech centre should be opened and takes new trainees")
    public void tech_centre_should_be_opened_and_takes_new_trainees(Integer int1) {

    }
    @Then("only one type trainee should be in tech centre")
    public void only_one_type_trainee_should_be_in_tech_centre() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("only this type of trainee should be decreased in waiting list")
    public void only_this_type_of_trainee_should_be_decreased_in_waiting_list() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("no centre opened")
    public void no_centre_opened() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("bootcamp should not be opened")
    public void bootcampShouldNotBeOpened() {
        int numberOfBootcamp = centreController.bootcampCheck(centreList, 0);
        CentreController.centerTypeGen(numberOfBootcamp,2);
        Assertions.assertTrue(centreController.bootcampCheck(centreList,0) == numberOfBootcamp);
    }
}
