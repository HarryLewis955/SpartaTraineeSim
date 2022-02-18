package com.sparta.simulation;
import io.cucumber.java.bs.A;

import java.util.*;
import java.util.stream.Collectors;

public class SimulationController {
    public static void main(String[] args) {

        int months = 24;// user to define this


        // Initializing the lists to be used
        ArrayList<Integer> waitingList = new ArrayList<>();
        List<Integer> listWithoutDuplicates = new ArrayList<>();
        List<Integer> descendingBench = new ArrayList<>();
        ArrayList<Centre> centreList = new ArrayList<>();
        ArrayList<Centre> closedCentres = new ArrayList<>();
        ArrayList<Integer> centresToClose = new ArrayList<>();
        List<Integer> temporaryList;
        ArrayList<Client> clientList = new ArrayList<>();
        ArrayList<Integer> bench = new ArrayList<>();
        ArrayList<int[]> traineesInTraining = new ArrayList<>();
        // Random class for random numbers
        Random r = new Random();
        ArrayList<Client> closedClients = new ArrayList<>();


        // Initializing counting variables
        int javaCount = 0;
        int csharpCount = 0;
        int dataCount = 0;
        int devopsCount = 0;
        int businessCount = 0;
        int idCount = 0;
        int bootCampCount = 0;
        int clientIdCount = 0;

        // Each iteration of the loop represents 1 month of time
        for (int i = 0; i < months; i++) {
            int[] monthlyTrainees = new int[6];
            // Creating random numbers for this month
            int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
            int techCentreStream = r.nextInt(1,6); // only for centre type 3
            int centreType = r.nextInt(1,4);
//            int centreType = 1;

            int newTrainees = r.nextInt(50,101);
//            int newTrainees = 5;
            int centreCapacity = r.nextInt(0,51);
//            int centreCapacity = 50;
            int clientStream = r.nextInt(1,6);
            int clientRequirement = r.nextInt(15,51);
            int clientMonthlyTrainees = r.nextInt(1,clientRequirement);


            // From the new trainees this month, assign each a stream and add to the waiting list, return new row to represent trainees for that month
            int[] monthlyCount = TraineeController.createTrainee(newTrainees, javaCount, csharpCount, dataCount, devopsCount, businessCount, waitingList);



            // Increase number of trainees each centre takes for this month (random for each centre between 0-50)
            CentreController centreControl = new CentreController();
            centreControl.centreCapacity(centreList, centreCapacity);

            // Every two months create a new centre
            if (i%2 == 0){
                // Check how many boot-camps there are in the centre list
                bootCampCount = centreControl.bootcampCheck(centreList, bootCampCount);

                // Generate the type of centre being created (if 2 boot-camps then random between tech centre and training hub)
                centreType = CentreController.centerTypeGen(bootCampCount, centreType);

                // Create the centre for this month (or multiple if training hub is selected)
                CentreController create = new CentreController();
                idCount = create.createCentre(centreList, centreCapacity, centreType, idCount, numberOfTrainingHub, techCentreStream);
            }

            // Create unchanging size of waiting list to reference
            int waitingListSize = waitingList.size();

            // If there are trainees in the waiting list, add to the next free centre
            // Always take from index 0 of waiting list to ensure first into the list are first out
            centreList = centreControl.addToCentre(waitingList, centreList, waitingListSize, monthlyTrainees, traineesInTraining, i);

            // Check if attendance is less than 25, if 3 months of low attendance then add to the "to be closed list"
            // "To be closed list" contains the position of that centre in centreList
            CentreController.checkAttend(centreList, centresToClose);

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


            // Increase client counter by 1 and if its 12 check whether they are staying or going
            for (int j = 0; j < clientList.size(); j++) {
                clientList.get(j).setClientCounter(clientList.get(j).getClientCounter() + 1);
                if(clientList.get(j).getClientCounter() == 12 && clientList.get(j).getCurrentTrainees() == clientList.get(j).getTraineeRequirement()){
                    clientList.get(j).setClientCounter(0);
                    clientList.get(j).setTraineeRequirement(clientList.get(j).getOriginalTraineesReq()+clientList.get(j).getTraineeRequirement());
                    clientList.get(j).setHappy(true);

                } else if (clientList.get(j).getClientCounter() == 12 && clientList.get(j).getCurrentTrainees() != clientList.get(j).getTraineeRequirement()){
                    closedClients.add(clientList.get(j));
                    clientList.get(j).setHappy(false);
                    clientList.remove(clientList.get(j));
                }
            }


            // add the graduating trainees for that month to the bench
            traineesInTraining.add(monthlyTrainees);
            for (int j = 0; j < traineesInTraining.size(); j++) {
                if (traineesInTraining.get(j)[5] == 3) {
                    for (int l = 0; l < 5; l++) {
                        for (int k = 0; k < traineesInTraining.get(j)[l]; k++) {
                            bench.add(l + 1);
                        }
                    }
                }
                traineesInTraining.get(j)[5]++;
            }

            // create a client 50% chance each month and give it a random requirement and stream
            boolean clientToBeMade = r.nextBoolean();
            if (clientToBeMade && i > 12){
                Client client = new Client(clientIdCount, clientStream, clientRequirement, clientMonthlyTrainees);
                clientIdCount++;
                clientList.add(client);
            }

            // Remove from first centre that has the correct stream available
            for (int j = 0; j < bench.size(); j++) {
                for (int k = 0; k < centreList.size(); k++) {
                    if (centreList.get(k).getJavaCount() > 0 && bench.get(j) == 1){
                        centreList.get(k).setJavaCount(centreList.get(k).getJavaCount() - 1);
                        break;
                    } else if (centreList.get(k).getCsharpCount() > 0 && bench.get(j) == 2){
                        centreList.get(k).setCsharpCount(centreList.get(k).getCsharpCount() - 1);
                        break;
                    } else if (centreList.get(k).getDataCount() > 0 && bench.get(j) == 3){
                        centreList.get(k).setDataCount(centreList.get(k).getDataCount() - 1);
                        break;
                    } else if (centreList.get(k).getDevopsCount() > 0 && bench.get(j) == 4){
                        centreList.get(k).setDevopsCount(centreList.get(k).getDevopsCount() - 1);
                        break;
                    } else if (centreList.get(k).getBusinessCount() > 0 && bench.get(j) == 5){
                        centreList.get(k).setBusinessCount(centreList.get(k).getBusinessCount() - 1);
                        break;
                    }
                }
            }

            // loop through clients and give them the correct trainees from the bench
            for (int j = 0; j < clientList.size(); j++) {
                int needed = clientList.get(j).getDifference();
                int streamNeeded = clientList.get(j).getTraineeType();
                int occurrences = Collections.frequency(bench, streamNeeded);
                int benchSize = bench.size();
                    int min = Math.min(occurrences, clientList.get(j).getRandomTrainees());
                    for (int k = 0; k < min; k++) {
                        if (clientList.get(j).getTraineeRequirement() > clientList.get(j).getCurrentTrainees()) {
                            if (needed > 0) {
                                clientList.get(j).setCurrentTrainees(clientList.get(j).getCurrentTrainees() + 1);
                                bench.remove(bench.indexOf(streamNeeded));
                                //descendingBench.remove(position);
                            }
                        }
                    }


            }


            System.out.println(bench);
            //System.out.println(traineesInTraining.get(0));

            // PRINTING OUT STUFF
            for (int m = 0; m < centreList.size(); m++) {
                System.out.println(centreList.get(m).getCentreID() +
                        " Java: " + centreList.get(m).getJavaCount() +
                        " C#: " + centreList.get(m).getCsharpCount() +
                        " Data: " + centreList.get(m).getDataCount() +
                        " DevOps: " + centreList.get(m).getDevopsCount() +
                        " Business: " + centreList.get(m).getBusinessCount() +
                        " Total: " + centreList.get(m).getTotal());
            }




            System.out.println("Waiting list:");
            System.out.println(waitingList);
            System.out.println("Waiting list size: " + waitingList.size());
            System.out.println("\nList of open centres:");
            System.out.println(centreList);
            System.out.println("There are currently: " + centreList.size() +  " centres open");
            System.out.println("\nList of closed centres:");
            System.out.println(closedCentres);
            System.out.println("There are currently: " + closedCentres.size() +  " centres closed");
            System.out.println(clientList);
            System.out.println("There are currently: " + clientList.size() +  " clients open");
            System.out.println(closedClients);
            System.out.println("There are currently: " + closedClients.size() +  " clients removed");
            System.out.println("====================================================================");








        }


//        System.out.println(bench);
//        //System.out.println(traineesInTraining.get(0));
//
//        // PRINTING OUT STUFF
//        for (int i = 0; i < centreList.size(); i++) {
//            System.out.println(centreList.get(i).getCentreID() +
//                    " Java: " + centreList.get(i).getJavaCount() +
//                    " C#: " + centreList.get(i).getCsharpCount() +
//                    " Data: " + centreList.get(i).getDataCount() +
//                    " DevOps: " + centreList.get(i).getDevopsCount() +
//                    " Business: " + centreList.get(i).getBusinessCount() +
//                    " Total: " + centreList.get(i).getTotal());
//        }
//
//
//
//
//        System.out.println("Waiting list:");
//        System.out.println(waitingList);
//        System.out.println("Waiting list size: " + waitingList.size());
//        System.out.println("\nList of open centres:");
//        System.out.println(centreList);
//        System.out.println("There are currently: " + centreList.size() +  " centres open");
//        System.out.println("\nList of closed centres:");
//        System.out.println(closedCentres);
//        System.out.println("There are currently: " + closedCentres.size() +  " centres open");
//        System.out.println(clientList);



//        System.out.println("\nList of trainees in each centre:");
//        System.out.println(traineesInCentres);
//        System.out.println("\nThere are this many centres with trainees in: " + traineesInCentres.stream().filter(x -> !x.isEmpty()).count());

    }
}
