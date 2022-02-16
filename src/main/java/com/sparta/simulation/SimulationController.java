package com.sparta.simulation;
import java.util.*;
import java.util.stream.Collectors;

public class SimulationController {
    public static void main(String[] args) {
        int months = 100;// user to define this

        // Initializing the lists to be used
        ArrayList<Integer> waitingList = new ArrayList<>();
        List<Integer> listWithoutDuplicates = new ArrayList<>();
        ArrayList<Centre> centreList = new ArrayList<>();
        ArrayList<Centre> closedCentres = new ArrayList<>();
        ArrayList<Integer> centresToClose = new ArrayList<>();
        List<Integer> temporaryList;

        // Random class for random numbers
        Random r = new Random();

        // Initializing counting variables
        int javaCount = 0;
        int csharpCount = 0;
        int dataCount = 0;
        int devopsCount = 0;
        int businessCount = 0;
        int idCount = 0;
        int bootCampCount = 0;

        // Each iteration of the loop represents 1 month of time
        for (int i = 0; i < months; i++) {
            // Creating random numbers for this month
            int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
            int techCentreStream = r.nextInt(1,6); // only for centre type 3
            int centreType = r.nextInt(1,4);
//            int centreType = 1;
            int newTrainees = r.nextInt(50,101);
//            int newTrainees = 10;
            int centreCapacity = r.nextInt(0,51);
//            int centreCapacity = 5;

            // From the new trainees this month, assign each a stream and add to the waiting list
            TraineeController.createTrainee(newTrainees, javaCount, csharpCount, dataCount, devopsCount, businessCount, waitingList);

            // Increase number of trainees each centre takes for this month (random for each centre between 0-50)
            CentreController centreControl = new CentreController();
            centreControl.centreCapacity(centreList, centreCapacity);

            // Every two months create a new centre
            if (i%2 == 0){
                // Check how many bootcamps there are in the centre list
                bootCampCount = centreControl.bootcampCheck(centreList, bootCampCount);

                // Generate the type of centre being created (if 2 bootcamps then random between tech centre and training hub)
                centreType = CentreController.centerTypeGen(bootCampCount, centreType);

                // Create the centre for this month (or multiple if training hub is selected)
                CentreController create = new CentreController();
                idCount = create.createCentre(centreList, centreCapacity, centreType, idCount, numberOfTrainingHub, techCentreStream);
            }

            // Create unchanging size of waiting list to reference
            int waitingListSize = waitingList.size();

            // If there are trainees in the waiting list, add to the next free centre
            // Always take from index 0 of waiting list to ensure first into the list are first out
            centreList = centreControl.addToCentre(waitingList, centreList, waitingListSize);

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
        }

        // PRINTING OUT STUFF
        for (int i = 0; i < centreList.size(); i++) {
            System.out.println(centreList.get(i).getCentreID() +
                    " Java: " + centreList.get(i).getJavaCount() +
                    " C#: " + centreList.get(i).getCsharpCount() +
                    " Data: " + centreList.get(i).getDataCount() +
                    " DevOps: " + centreList.get(i).getDevopsCount() +
                    " Business: " + centreList.get(i).getBusinessCount() +
                    " Total: " + centreList.get(i).getTotal());
        }

        System.out.println("Waiting list:");
        System.out.println(waitingList);
        System.out.println("Waiting list size: " + waitingList.size());
        System.out.println("\nList of open centres:");
        System.out.println(centreList);
        System.out.println("There are currently: " + centreList.size() +  " centres open");
        System.out.println("\nList of closed centres:");
        System.out.println(closedCentres);
        System.out.println("There are currently: " + closedCentres.size() +  " centres open");
//        System.out.println("\nList of trainees in each centre:");
//        System.out.println(traineesInCentres);
//        System.out.println("\nThere are this many centres with trainees in: " + traineesInCentres.stream().filter(x -> !x.isEmpty()).count());

    }
}
