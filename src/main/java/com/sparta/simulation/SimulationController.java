package com.sparta.simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationController {
    public static void main(String[] args) {

        int months = 100;// user to define this
        ArrayList<Integer> waitingList = new ArrayList<>();

        ArrayList<Centre> centreList = new ArrayList<>();
        ArrayList<Centre> closedCentres = new ArrayList<>();
        ArrayList<Centre> centresToClose = new ArrayList<>();
        Random r = new Random();
        int javaCount = 0;
        int csharpCount = 0;
        int dataCount = 0;
        int devopsCount = 0;
        int businessCount = 0;
        int idCount = 0;
        int bootCampCount = 0;
        // loop through months, each month this is executed
        for (int i = 0; i < months; i++) {
            int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
            int techCentreStream = r.nextInt(1,6); // only for centre type 3
            int centreType = r.nextInt(1,4);
//            int centreType = 2;
            // generate number of new trainees for this month
            int newTrainees = r.nextInt(50,100);
//            int newTrainees = 10;
            TraineeController.createTrainee(newTrainees, javaCount, csharpCount, dataCount, devopsCount, businessCount, waitingList);
             // add trainees to waiting list

            int centreCapacity = r.nextInt(0,50);
//            int centreCapacity = 5;
            // if you want to add your own capacity for each month comment out the line above and uncomment this below
            CenterController centreControl = new CenterController();
            centreControl.centreCapacity(centreList, centreCapacity);



            if (i%2 == 0){
                bootCampCount = centreControl.bootcampCheck(centreList, bootCampCount);
                centreType = CenterController.centerTypeGen(bootCampCount, centreType);
                CenterController create = new CenterController();
                idCount = create.createCentre(centreList, centreCapacity, centreType, idCount, numberOfTrainingHub, techCentreStream);
            }
            int waitingListSize = waitingList.size();
            // If there are trainees in the waiting list
            centreList = centreControl.addToCentre(waitingList, centreList, waitingListSize);
            // move to center controller
            CenterController.checkAttend(centreList, centresToClose);

            int centreListSize = centreList.size();
            ArrayList<Integer> tempTrainee = new ArrayList<>();
//            for (int j = 0; j < centresToClose.size(); j++) {
//
//                centreList.remove(centresToClose.get(j));
//
//
//
//
//
//                for (int k = 0; k < centreListSize-1; k++) {
//                    if (centresToClose.size() > 0) {
//                        if (centreList.get(k).getCentreID() == centresToClose.get(j).getCentreID()) {
//                            closedCentres.add(centreList.get(k));
//                            centreList.remove(centreList.get(k));
//                            centresToClose.remove(j);
//                        }
//                    }
//                }
//
//
//
//
////                int k = centreList.indexOf(centresToClose[j]);
//////                if (centresToClose.get(j) == centreList.get(k).getCentreID()){
////                    for (int l = 0; l < centreList.get(k).getJavaCount(); l++) {
////                        waitingList.add(1);
////                        Collections.rotate(waitingList, 1);
////                    }
////                    for (int l = 0; l < centreList.get(k).getCsharpCount(); l++) {
////                        waitingList.add(2);
////                        Collections.rotate(waitingList, 1);
////                    }
////                    for (int l = 0; l < centreList.get(k).getDataCount(); l++) {
////                        waitingList.add(3);
////                        Collections.rotate(waitingList, 1);
////                    }
////                    for (int l = 0; l < centreList.get(k).getDevopsCount(); l++) {
////                        waitingList.add(4);
////                        Collections.rotate(waitingList, 1);
////                    }
////                    for (int l = 0; l < centreList.get(k).getBusinessCount(); l++) {
////                        waitingList.add(5);
////                        Collections.rotate(waitingList, 1);
////                    }
////                    centreList.remove(k);
//////                }
////
//
//
//
//
//
////                tempTrainee.add(centreList.get(centresToClose.get(i)).getJavaCount());
////                tempTrainee.add(centreList.get(centresToClose.get(i)).getCsharpCount());
////                tempTrainee.add(centreList.get(centresToClose.get(i)).getDataCount());
////                tempTrainee.add(centreList.get(centresToClose.get(i)).getDevopsCount());
////                tempTrainee.add(centreList.get(centresToClose.get(i)).getBusinessCount());
////
//            }


            // close centers
            // if no place for next lad on waiting list, move to next person
            // fill centres until their current capacity before filling the next
            // refactor



        }





        // PRINTING OUT STUFF
//        List<Integer> temporaryList = centresToClose.stream().distinct().toList();
//        System.out.println(temporaryList);

        System.out.println(centresToClose);
        System.out.println(closedCentres);

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
//        System.out.println("\nList of trainees in each centre:");
//        System.out.println(traineesInCentres);
//        System.out.println("\nThere are this many centres with trainees in: " + traineesInCentres.stream().filter(x -> !x.isEmpty()).count());

    }
}
