package com.sparta.simulation;
import java.util.ArrayList;
import java.util.Random;

public class SimulationController {
    public static void main(String[] args) {

        int months = 8;// user to define this
        ArrayList<Trainee> waitingList = new ArrayList();
        ArrayList<Centre> centreList = new ArrayList();
        ArrayList<ArrayList<Trainee>> traineesInCentres = new ArrayList();

        Random r = new Random();

        // loop through months, each month this is executed
        for (int i = 0; i < months; i++) {
            // generate number of new trainees for this month
            int newTrainees = r.nextInt(50,100);
//            int newTrainees = 2;

            // add trainees to waiting list
            for (int j = 0; j < newTrainees; j++) {
                waitingList.add(new Trainee(j));
            }

            int centreCapacity = r.nextInt(0,50);
            // if you want to add your own capacity for each month comment out the line above and uncomment this below
//            int centreCapacity = 100;

            // increase open centres capacity by new amount up to max capacity of 100
            for (int j = 0; j < centreList.size(); j++) {
                centreList.get(j).setCentreCapacity(centreList.get(j).getCentreCapacity() + centreCapacity);
                if (centreList.get(j).getCentreCapacity() >= 100){
                    centreList.get(j).setCentreCapacity(100);
                }
            }

            // every two months generate new centre and add to array list of centres
            // create new arraylist of trainees for that centre
            if (i%2 == 0){
                Centre centre = new Centre(i/2, centreCapacity);
                centreList.add(centre);
                traineesInCentres.add(new ArrayList<Trainee>());
            }

            int waitingListSize = waitingList.size();
            // If there are trainees in the waiting list
            if (waitingList.size() > 0){
                // For each trainee in the waiting list
                for (int j = 0; j < waitingListSize; j++) {
                    // Check capacity free in each traineesInCentres
                    for (int k = 0; k < traineesInCentres.size(); k++) {
                        int freeCapacity = centreList.get(k).getCentreCapacity() - traineesInCentres.get(k).size();
                        // If there is free capacity in the centre, add the trainee to the centre
                        if(freeCapacity > 0 && waitingList.size() > 0 ){
                            traineesInCentres.get(k).add(waitingList.get(0));
                            waitingList.remove(0);
                        }
                    }
                }
            }
        }

        System.out.println("Waiting list:");
        System.out.println(waitingList);
        System.out.println("Waiting list size: " + waitingList.size());
        System.out.println("\nList of centres:");
        System.out.println(centreList);
        System.out.println("There are currently: " + centreList.size() +  " centres open");
        System.out.println("\nList of trainees in each centre:");
        System.out.println(traineesInCentres);
        System.out.println("\nThere are this many centres with trainees in: " + traineesInCentres.stream().filter(x -> !x.isEmpty()).count());
    }
}
