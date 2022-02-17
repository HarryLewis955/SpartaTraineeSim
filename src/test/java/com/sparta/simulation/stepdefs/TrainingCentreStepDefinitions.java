package com.sparta.simulation.stepdefs;

import com.sparta.simulation.Centre;
import com.sparta.simulation.CentreController;
import com.sparta.simulation.TraineeController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainingCentreStepDefinitions {

    public int generateRandom(int start, int end, ArrayList<Integer> exclude) {
        Random r = new Random();
        int range = end - start +1 - exclude.size();
        int random = r.nextInt(range) + 1;

        for(int i = 0; i < exclude.size(); i++) {
            if(exclude.get(i) > random) {
                return random;
            }
            random++;
        }

        return random;
    }

    ArrayList<Integer> excludeBootcamp = new ArrayList<>(List.of(2));

    ArrayList<Integer> excludeTechCentre = new ArrayList<>(List.of(3));

    Centre centre;

    CentreController c;

    ArrayList<Centre> centreList;
    int centreCapacity;


    @Given("user enters {int} to start simulation")
    public void userEntersToStartSimulation(int monthsToRunSimulationFor) {
        System.out.println("Assume that user enters " + monthsToRunSimulationFor + " to start the programme");
        // assume that user entered a number
    }

    @When("the program is generating {int} trainees in this month")
    public void theProgramIsGeneratingTraineesInThisMonth(int numberOfTrainees) {
        ArrayList<Integer> waitingList = new ArrayList<>();
        TraineeController.createTrainee(numberOfTrainees,0,0,0,0,0,waitingList);
    }

    @When("no bootcamp opened")
    public void noBootcampOpened() {
        Random r = new Random();
        Integer centreType = generateRandom(1,3, excludeBootcamp);
        centreCapacity = r.nextInt(0,51);
        centre = new Centre(1,centreCapacity,centreType,0);
        centreList = new ArrayList<>();
        centreList.add(centre);
        c = new CentreController();
    //    c.centreCapacity(centreList,centreCapacity);
     //   centreList.get(0).setCentreType();
    }

    @When("no tech centre opened")
    public void noTechCentreOpened() {
        if(centre.getCentreType() == 3){
            centre.setCentreType(1);
        }
    }

    @Then("maximum {int} training hubs should be opened")
    public void maximumTrainingHubsShouldBeOpened(int maxTrainingHubsOpened) {
        int numberOfTrainingHubsOpened = c.createCentre(centreList,centreCapacity,centre.getCentreType(),0,maxTrainingHubsOpened,0);
        System.out.println(numberOfTrainingHubsOpened);
        Assertions.assertTrue(maxTrainingHubsOpened >= numberOfTrainingHubsOpened);
    }

    @Then("minimum {int} training hub should be opened")
    public void minimumTrainingHubShouldBeOpened(int minTrainingHubsOpened) {
        int numberOfTrainingHubsOpened = c.createCentre(centreList,centreCapacity,centre.getCentreType(),0,minTrainingHubsOpened,0);
        System.out.println(minTrainingHubsOpened);
        Assertions.assertTrue(minTrainingHubsOpened <= numberOfTrainingHubsOpened);
    }

    @When("{int} training hub opened")
    public void trainingHubOpened(int arg0) {
    }

    @Then("no training hub opened")
    public void noTrainingHubOpened() {
    }

    @Then("simulation should be ended")
    public void simulationShouldBeEnded() {
    }

    @Then("training hub {int} is full of {int} trainees")
    public void trainingHubIsFullOfTrainees(int arg0, int arg1) {
    }

    @When("the program is generating {int} of trainees in this month")
    public void theProgramIsGeneratingOfTraineesInThisMonth(int arg0) {
    }

    @Then("{int} bootcamp should be opened")
    public void bootcampShouldBeOpened() {
    }

    @Then("training hub {int} takes new {int} trainees")
    public void trainingHubTakesNewTrainees(int arg0, int arg1) {
    }

    @When("bootcamp {int} takes {int} new trainees")
    public void bootcampTakesNewTrainees(int arg0, int arg1) {
    }
}
