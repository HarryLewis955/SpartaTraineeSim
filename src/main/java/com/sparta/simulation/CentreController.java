package com.sparta.simulation;

import java.util.*;

public class CentreController {

    // Increase capacities up to their max
    public void centreCapacity(ArrayList<Centre> centreList, int centreCapacity){
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
    }

    // check how many bootcamps there are
    public int bootcampCheck(ArrayList<Centre> centreList, int bootCampCount){
        for (int j = 0; j < centreList.size(); j++) {
            if(centreList.get(j).getCentreType() == 2){
                bootCampCount++;
            }
        }
        return bootCampCount;
    }

    // generate centre type randomly,
    public static int centerTypeGen(int bootCampCount, int centreType) {
        Random r = new Random();
         if (bootCampCount >= 2) {
            int x = r.nextInt(1, 3);
            if (x == 1) {
                centreType = 1;
            } else {
                centreType = 3;
            }
        }
        return centreType;
    }

    // create centre
    public int createCentre(ArrayList<Centre> centreList, int centreCapacity, int centreType, int idCount, int numberOfTrainingHub, int techCentreStream){
        Random r = new Random();
        if(centreType == 1) {
//            int numberOfHub = r.nextInt(1, 4);
            for (int j = 0; j < numberOfTrainingHub; j++) {
                Centre centre = new Centre(idCount, r.nextInt(0,51), centreType, 0);
                centreList.add(centre);
                idCount++;
            }
        } else if (centreType == 2){
            Centre centre = new Centre(idCount, centreCapacity, centreType, 0);
            centreList.add(centre);
            idCount++;
        }else if (centreType == 3) {
            Centre centre = new Centre(idCount, centreCapacity, centreType, techCentreStream);
            centreList.add(centre);
            idCount++;
        }
        return idCount;
    }

    // check for low attendance
    public static void checkAttend(ArrayList<Centre> centreList, ArrayList<Integer> tempList){
        for (int j = 0; j < centreList.size(); j++) {
            if(centreList.get(j).getTotal() < 25){
                centreList.get(j).setLowAttendanceMonths(centreList.get(j).getLowAttendanceMonths() + 1);
                if(centreList.get(j).getLowAttendanceMonths() >= 3){
                    tempList.add(centreList.indexOf(centreList.get(j)));
                }
            }
        }
    }

    // takes trainee from waiting list and adds to centre

    public ArrayList<Centre> addToCentre(ArrayList<Integer> waitingList, ArrayList<Centre> centreList, int waitingListSize, int[] monthlyTrainees, ArrayList<int[]> traineesInTraining, int i){
        if (waitingList.size() > 0){
            // For each trainee in the waiting list
            for (int j = 0; j < waitingListSize; j++) {
                for(int l = 0; l < centreList.size(); l++){
                    // Check capacity free in each traineesInCentres
//                    for (int k = 0; k < centreList.size(); k++) {
                        int freeCapacity = centreList.get(l).getCentreCapacity() - centreList.get(l).getTotal();
                        // If there is free capacity in the centre, add the trainee to the centre
                        if(freeCapacity > 0  && waitingList.size() > 0 && centreList.get(l).getCentreType() != 3 && freeCapacity <= centreList.get(l).getCentreCapacity()){
                            // move to method

                            addCentreOneTwo(centreList,waitingList, l, monthlyTrainees, traineesInTraining, i);

//                            break;
                        } else if (freeCapacity > 0 && waitingList.size() > 0 && centreList.get(l).getCentreType() == 3){
                            CentreController centre3Controller = new CentreController();
                            centre3Controller.addToCentre3(waitingList, centreList, l, monthlyTrainees, traineesInTraining, i);
//                            break;

                        }
//                    }
                }
            }
        }

        return centreList;
    }


// add to centres 1 & 2
    public void addCentreOneTwo(ArrayList<Centre> centreList, ArrayList<Integer> waitingList, int l, int[] monthlyTrainees, ArrayList<int[]> traineesInTraining, int i){
        int traineeType = waitingList.get(0);
        if(traineeType == 1){
            centreList.get(l).setJavaCount(centreList.get(l).getJavaCount() + 1);
        } else if (traineeType == 2){
            centreList.get(l).setCsharpCount(centreList.get(l).getCsharpCount() + 1);
        } else if (traineeType == 3){
            centreList.get(l).setDataCount(centreList.get(l).getDataCount() + 1);
        } else if (traineeType == 4){
            centreList.get(l).setDevopsCount(centreList.get(l).getDevopsCount() + 1);
        } else if (traineeType == 5){
            centreList.get(l).setBusinessCount(centreList.get(l).getBusinessCount() + 1);
        } else{
            System.out.println("error3");
        }
        monthlyTrainees[waitingList.get(0) - 1]++;
//        monthlyTrainees[5] = i;
        //monthlyTrainees.();
//        Arrays.fill(monthlyTrainees, 0);
        waitingList.remove(0);
    }

// add to centre 3
    public void addToCentre3(ArrayList<Integer> waitingList, ArrayList<Centre> centreList, int l, int[] monthlyTrainees, ArrayList<int[]> traineesInTraining, int i){

        int traineeType = waitingList.get(0);
        if(traineeType == 1 && centreList.get(l).getStream() == 1){
            centreList.get(l).setJavaCount(centreList.get(l).getJavaCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
//            monthlyTrainees[5] = i;
            waitingList.remove(0);

        } else if (traineeType == 2 && centreList.get(l).getStream() == 2){
            centreList.get(l).setCsharpCount(centreList.get(l).getCsharpCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
//            monthlyTrainees[5] = i;
            waitingList.remove(0);
        } else if (traineeType == 3 && centreList.get(l).getStream() == 3){
            centreList.get(l).setDataCount(centreList.get(l).getDataCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
//            monthlyTrainees[5] = i;
            waitingList.remove(0);
        } else if (traineeType == 4 && centreList.get(l).getStream() == 4){
            centreList.get(l).setDevopsCount(centreList.get(l).getDevopsCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
//            monthlyTrainees[5] = i;

            waitingList.remove(0);
        } else if (traineeType == 5 && centreList.get(l).getStream() == 5){
            centreList.get(l).setBusinessCount(centreList.get(l).getBusinessCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
//            monthlyTrainees[5] = i;
            waitingList.remove(0);
        } else {
            Collections.rotate(waitingList.subList(0 ,waitingList.indexOf(centreList.get(l).getStream()) + 1), 1);
        }
    }

    public void closeCentre(List<Integer> listWithoutDuplicates, ArrayList<Centre> centreList, ArrayList<Centre> closedCentres, ArrayList<Integer> waitingList){
        for (int j = 0; j < listWithoutDuplicates.size(); j++) {
            int k = listWithoutDuplicates.get(j);
            closedCentres.add(centreList.get(k));
            for (int l = 0; l < centreList.get(k).getJavaCount(); l++) {
                waitingList.add(1);
                Collections.rotate(waitingList, 1);
            }
            for (int l = 0; l < centreList.get(k).getCsharpCount(); l++) {
                waitingList.add(2);
                Collections.rotate(waitingList, 1);
            }
            for (int l = 0; l < centreList.get(k).getDataCount(); l++) {
                waitingList.add(3);
                Collections.rotate(waitingList, 1);
            }
            for (int l = 0; l < centreList.get(k).getDevopsCount(); l++) {
                waitingList.add(4);
                Collections.rotate(waitingList, 1);
            }
            for (int l = 0; l < centreList.get(k).getBusinessCount(); l++) {
                waitingList.add(5);
                Collections.rotate(waitingList, 1);
            }
            centreList.remove(k);
        }
    }




}
