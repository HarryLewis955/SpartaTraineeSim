package com.sparta.simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationController {
    public static void main(String[] args) {

        int months = 4;// user to define this
//        ArrayList<Trainee> waitingList = new ArrayList();
        ArrayList<Integer> waitingList = new ArrayList<>();

        ArrayList<Centre> centreList = new ArrayList<>();
        ArrayList<Centre> closedCentres = new ArrayList<>();
        ArrayList<ArrayList<Trainee>> traineesInCentres = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<>();

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
            // generate number of new trainees for this month
            int newTrainees = r.nextInt(50,100);
//            int newTrainees = 50;

            // add trainees to waiting list
            for (int j = 0; j < newTrainees; j++) {
                int traineeType = r.nextInt(1, 6);
                waitingList.add(traineeType);
                if(traineeType == 1){
                    javaCount++;
                } else if (traineeType == 2){
                    csharpCount++;
                } else if (traineeType == 3){
                    dataCount++;
                } else if (traineeType == 4){
                    devopsCount++;
                } else if (traineeType == 5){
                    businessCount++;
                } else{
                    System.out.println("error1");
                }
            }

            int centreCapacity = r.nextInt(0,50);
            // if you want to add your own capacity for each month comment out the line above and uncomment this below
//             int centreCapacity = 95;

            // increase open centres capacity by new amount up to max capacity of 100
            for (int j = 0; j < centreList.size(); j++) {
                centreList.get(j).setCentreCapacity(centreList.get(j).getCentreCapacity() + centreCapacity);
                if(centreList.get(j).getCentreType() == 1){
                    if (centreList.get(j).getCentreCapacity() >= 100){
                        centreList.get(j).setCentreCapacity(100);
                    }
                } else if (centreList.get(j).getCentreType() == 2){
                    if (centreList.get(j).getCentreCapacity() >= 500){
                        centreList.get(j).setCentreCapacity(500);
                    }
                } else if (centreList.get(j).getCentreType() == 3){
                    if (centreList.get(j).getCentreCapacity() >= 200){
                        centreList.get(j).setCentreCapacity(200);
                    }
                } else {
                    System.out.println("error2");
                }

            }



            for (int j = 0; j < centreList.size(); j++) {
                if(centreList.get(j).getCentreType() == 2){
                    bootCampCount++;
                }
            }



            int centreType;



            if (i%2 == 0){
                if(bootCampCount < 2) {
                    centreType = r.nextInt(1, 4);
                } else {
                    int x = r.nextInt(1, 3);
                    if(x == 1){
                        centreType = 1;
                    } else {
                        centreType = 3;
                    }
                }
//                int centreType = 2;



                if(centreType == 1) {
                    int numberOfHub = r.nextInt(1, 4);
                    for (int j = 0; j < numberOfHub; j++) {
                        Centre centre = new Centre(idCount, centreCapacity, centreType, 0);
                        centreList.add(centre);
                        traineesInCentres.add(new ArrayList<Trainee>());
                        idCount++;
                    }
                } else if (centreType == 2){
                    Centre centre = new Centre(idCount, centreCapacity, centreType, 0);
                    centreList.add(centre);
                    traineesInCentres.add(new ArrayList<Trainee>());
                    idCount++;
                }else if (centreType == 3) {
                    Centre centre = new Centre(idCount, centreCapacity, centreType, r.nextInt(1, 6));
                    centreList.add(centre);
                    traineesInCentres.add(new ArrayList<Trainee>());
                    idCount++;
                }
            }



            int waitingListSize = waitingList.size();
            // If there are trainees in the waiting list
            if (waitingList.size() > 0){
                // For each trainee in the waiting list
                for (int j = 0; j < waitingListSize; j++) {
                    // Check capacity free in each traineesInCentres
                    for (int k = 0; k < centreList.size(); k++) {
                        int freeCapacity = centreList.get(k).getCentreCapacity() - centreList.get(k).getTotal();
                        // If there is free capacity in the centre, add the trainee to the centre
                        if(freeCapacity > 0  && waitingList.size() > 0 && centreList.get(k).getCentreType() != 3 && freeCapacity <= centreList.get(k).getCentreCapacity()){

//                            traineesInCentres.get(k).add(waitingList.get(0));
                            int traineeType = waitingList.get(0);
//                            waitingList.add(traineeType);
                            if(traineeType == 1){
                                centreList.get(k).setJavaCount(centreList.get(k).getJavaCount() + 1);
                            } else if (traineeType == 2){
                                centreList.get(k).setCsharpCount(centreList.get(k).getCsharpCount() + 1);
                            } else if (traineeType == 3){
                                centreList.get(k).setDataCount(centreList.get(k).getDataCount() + 1);
                            } else if (traineeType == 4){
                                centreList.get(k).setDevopsCount(centreList.get(k).getDevopsCount() + 1);
                            } else if (traineeType == 5){
                                centreList.get(k).setBusinessCount(centreList.get(k).getBusinessCount() + 1);
                            } else{
                                System.out.println("error3");
                            }
                            waitingList.remove(0);
                        } else if (freeCapacity > 0 && waitingList.size() > 0 && centreList.get(k).getCentreType() == 3){
                            int traineeType = waitingList.get(0);
                            if(traineeType == 1 && centreList.get(k).getStream() == 1){
                                centreList.get(k).setJavaCount(centreList.get(k).getJavaCount() + 1);
                                waitingList.remove(0);
                            } else if (traineeType == 2 && centreList.get(k).getStream() == 2){
                                centreList.get(k).setCsharpCount(centreList.get(k).getCsharpCount() + 1);
                                waitingList.remove(0);
                            } else if (traineeType == 3 && centreList.get(k).getStream() == 3){
                                centreList.get(k).setDataCount(centreList.get(k).getDataCount() + 1);
                                waitingList.remove(0);
                            } else if (traineeType == 4 && centreList.get(k).getStream() == 4){
                                centreList.get(k).setDevopsCount(centreList.get(k).getDevopsCount() + 1);
                                waitingList.remove(0);
                            } else if (traineeType == 5 && centreList.get(k).getStream() == 5){
                                centreList.get(k).setBusinessCount(centreList.get(k).getBusinessCount() + 1);
                                waitingList.remove(0);
                            }
                        }

                    }

                }
            }
            for (int j = 0; j < centreList.size(); j++) {
                if(centreList.get(j).getTotal() < 25){
                    centreList.get(j).setLowAttendanceMonths(centreList.get(j).getLowAttendanceMonths() + 1);
                    if(centreList.get(j).getLowAttendanceMonths() >= 3){
                        tempList.add(centreList.get(j).getCentreID());
                    }
                }
            }



            // if no place for next lad on waiting list, move to next person
            // fill centres until their current capacity before filling the next
            // refactor




//            for (int j = 0; j < tempList.size(); j++) {
//                for (int k = 0; k < centreList.size(); k++) {
//                    if(tempList.get(j) == centreList.get(k).getCentreID()){
//                        centreList.remove(k);
//                    }
//                }
//
//            }







        }
        List<Integer> temporaryList = tempList.stream().distinct().toList();
        System.out.println(temporaryList);

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
        System.out.println("\nList of centres:");
        System.out.println(centreList);
        System.out.println("There are currently: " + centreList.size() +  " centres open");
        System.out.println("\nList of trainees in each centre:");
        System.out.println(traineesInCentres);
        System.out.println("\nThere are this many centres with trainees in: " + traineesInCentres.stream().filter(x -> !x.isEmpty()).count());

    }
}
