package com.sparta.simulation.controller;

import com.sparta.simulation.model.*;
import com.sparta.simulation.view.DisplayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimulationController {
    public static void initiateSimulation(){
        Logger logger = LogManager.getLogger("Controller - Main Controller");
        logger.info("Initializing Sparta Simulation");
        logger.info("===========================================");
        logger.info("Retrieving user input");
        DisplayManager dm = new DisplayManager();
        int months = dm.getNumberOfMonths();// user defines this
        int displayChoice = dm.choiceOfDisplay();

        // Initializing the lists to be used
        logger.info("Initializing storage lists");
        ArrayList<Integer> waitingList = new ArrayList<>();
        List<Integer> listWithoutDuplicates = new ArrayList<>();
        ArrayList<Centre> centreList = new ArrayList<>();
        ArrayList<Centre> closedCentres = new ArrayList<>();
        ArrayList<Integer> centresToClose = new ArrayList<>();
        List<Integer> temporaryList;
        ArrayList<Client> clientList = new ArrayList<>();
        ArrayList<Integer> bench = new ArrayList<>();
        ArrayList<int[]> traineesInTraining = new ArrayList<>();
        Random r = new Random();
        ArrayList<Client> closedClients = new ArrayList<>();

        // Initializing counting variables
        logger.info("Initializing counting variables");
        int idCount = 0;
        int bootCampCount = 0;
        int clientIdCount = 0;

        // Each iteration of the loop represents 1 month of time
        for (int i = 0; i < months; i++) {
            logger.info("===========================================");
            logger.info("Beginning month " + i);
            // Creating random numbers for this month
            int[] monthlyTrainees = new int[6];
            int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
            int techCentreStream = r.nextInt(1,6); // only for centre type 3
            int centreType = r.nextInt(1,4);
            int newTrainees = r.nextInt(50,101);
            int centreCapacity = r.nextInt(0,51);
            int clientStream = r.nextInt(1,6);
            int clientRequirement = r.nextInt(15,51);
            int clientMonthlyTrainees = r.nextInt(1,clientRequirement);

            // From the new trainees this month, assign each a stream and add to the waiting list, return new row to represent trainees for that month
            TraineeModel.createTraineeAddToWaitingList(newTrainees, waitingList);

            // Increase number of trainees each centre takes for this month (random for each centre between 0-50)
            CentreModel centreControl = new CentreModel();
            centreControl.centreCapacity(centreList, centreCapacity);

            // Every two months create a new centre
            if (i%2 == 0){
                logger.info("Creating center/s");
                // Check how many boot-camps there are in the centre list
                bootCampCount = centreControl.bootcampCheck(centreList, bootCampCount);

                // Generate the type of centre being created (if 2 boot-camps then random between tech centre and training hub)
                centreType = CentreModel.centerTypeGen(bootCampCount, centreType);

                // Create the centre for this month (or multiple if training hub is selected)
                CentreModel create = new CentreModel();
                idCount = create.createCentre(centreList, centreCapacity, centreType, idCount, numberOfTrainingHub, techCentreStream);
            }

            // Create unchanging size of waiting list to reference
            int waitingListSize = waitingList.size();

            // Check if attendance is less than 25, if 3 months of low attendance then add to the "to be closed list"
            // "To be closed list" contains the position of that centre in centreList
            CentreModel.checkAttend(centreList, centresToClose);

            // Remove duplicates from the "to be closed list"
            temporaryList = centresToClose.stream().distinct().toList();

            // Sort into descending order to not affect indexing when centres are removed from centreList
            new LinkedList<>(temporaryList).descendingIterator().forEachRemaining(listWithoutDuplicates :: add);

            // Add closed centre to separate list and remove from centreList
            // Count number of each type of trainee from closed centre and add to start of waiting list, to ensure they are selected first again
            centreControl.closeCentre(listWithoutDuplicates, centreList, closedCentres, waitingList);

            // Clear lists ready for next loop
            listWithoutDuplicates.clear();
            centresToClose.clear();

            // If there are trainees in the waiting list, add to the next free centre
            // Always take from index 0 of waiting list to ensure first into the list are first out
            centreList = centreControl.addToCentre(waitingList, centreList, waitingListSize, monthlyTrainees);

            ClientModel c = new ClientModel();
            // Increase client counter by 1 and if its 12 check whether they are staying or going
            c.addToCounter(clientList, closedClients);

            // Add the graduating trainees for that month to the bench and remove from available centres
            traineesInTraining.add(monthlyTrainees);
            TraineeModel.removeFromCentreAddToBench(traineesInTraining, bench, centreList);

            // create a client at 50% chance each month and give it a random requirement and stream
            boolean clientToBeMade = r.nextBoolean();
            if (clientToBeMade && i > 12){
                logger.info("Creating client");
                Client client = new Client(clientIdCount, clientStream, clientRequirement, clientMonthlyTrainees);
                clientIdCount++;
                clientList.add(client);
            }

            // loop through clients and give them the correct trainees from the bench
            c.addToClient(clientList, bench);


            if(displayChoice == 1) {
                logger.info("Displaying results for month");
                DisplayController d = new DisplayController();
                d.displayResults(centreList, closedCentres, clientList, closedClients, waitingList);
                System.out.println("================Next Month=================");
                System.out.println("===========================================");
            }
        }
        if(displayChoice == 2) {
            logger.info("Displaying final month results");
            DisplayController d = new DisplayController();
            d.displayResults(centreList, closedCentres, clientList, closedClients, waitingList);
        }
    }
}
