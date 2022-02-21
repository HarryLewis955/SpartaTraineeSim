package com.sparta.simulation.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class CentreModel {
    public static Logger logger = LogManager.getLogger("Controller - Centre Controller");

    // Increase amount centres can take for that month, only up to each centres max
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
                logger.fatal("Centre type must be 1-3");
            }
        }
    }

    // Check how many boot-camps there are
    public int bootcampCheck(ArrayList<Centre> centreList, int bootCampCount){
        bootCampCount = 0;
        for (int j = 0; j < centreList.size(); j++) {
            if(centreList.get(j).getCentreType() == 2){
                bootCampCount++;
            }
        }
        return bootCampCount;
    }

    // Generate centre type randomly, chance for centres 1 and 3 chances when there are already two bootcamps
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

    // Create centre
    public int createCentre(ArrayList<Centre> centreList, int centreCapacity, int centreType, int idCount, int numberOfTrainingHub, int techCentreStream){
        Random r = new Random();
        if(centreType == 1) {
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

    // Check centres for low attendance (<25), after 3 consecutive months of low attendance, the centre shuts
    public static void checkAttend(ArrayList<Centre> centreList, ArrayList<Integer> tempList){
        for (int j = 0; j < centreList.size(); j++) {
            if(centreList.get(j).getTotal() < 25){
                centreList.get(j).setLowAttendanceMonths(centreList.get(j).getLowAttendanceMonths() + 1);
                if(centreList.get(j).getLowAttendanceMonths() >= 3){
                    tempList.add(centreList.indexOf(centreList.get(j)));
                }
            } else {
                centreList.get(j).setLowAttendanceMonths(0);
            }
        }
    }

    // Takes trainee from waiting list and adds to centre
    public ArrayList<Centre> addToCentre(ArrayList<Integer> waitingList, ArrayList<Centre> centreList, int waitingListSize, int[] monthlyTrainees){
        if (waitingList.size() > 0){
            for (int j = 0; j < waitingListSize; j++) {
                for(int l = 0; l < centreList.size(); l++){
                    for (int k = 0; k < centreList.size(); k++) { // two loops to ensure waiting list properly gets processed
                        int freeCapacity = centreList.get(l).getCentreCapacity() - centreList.get(l).getTotal();
                        // If there is free capacity in the centre, add the trainee to the centre
                        if(freeCapacity > 0  && waitingList.size() > 0 && centreList.get(l).getCentreType() != 3 && freeCapacity <= centreList.get(l).getCentreCapacity()){
                            addCentreOneTwo(centreList,waitingList, l, monthlyTrainees);
                        } else if (freeCapacity > 0 && waitingList.size() > 0 && centreList.get(l).getCentreType() == 3){
                            CentreModel centre3Controller = new CentreModel();
                            centre3Controller.addToCentre3(waitingList, centreList, l, monthlyTrainees);
                        }
                    }
                }
            }
        }
        return centreList;
    }


// Add to centre's 1 & 2
    private void addCentreOneTwo(ArrayList<Centre> centreList, ArrayList<Integer> waitingList, int l, int[] monthlyTrainees){
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
            logger.fatal("The waiting list should only contain values 1-5");
        }
        monthlyTrainees[waitingList.get(0) - 1]++;
        waitingList.remove(0);
    }

// Add to centre 3, needs separate method as can only take one training stream
    private void addToCentre3(ArrayList<Integer> waitingList, ArrayList<Centre> centreList, int l, int[] monthlyTrainees){
        int traineeType = waitingList.get(0);
        // Always removes index 0 of the waiting list to ensure first in first out
        if(traineeType == 1 && centreList.get(l).getStream() == 1){
            centreList.get(l).setJavaCount(centreList.get(l).getJavaCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
            waitingList.remove(0);
        } else if (traineeType == 2 && centreList.get(l).getStream() == 2){
            centreList.get(l).setCsharpCount(centreList.get(l).getCsharpCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
            waitingList.remove(0);
        } else if (traineeType == 3 && centreList.get(l).getStream() == 3){
            centreList.get(l).setDataCount(centreList.get(l).getDataCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
            waitingList.remove(0);
        } else if (traineeType == 4 && centreList.get(l).getStream() == 4){
            centreList.get(l).setDevopsCount(centreList.get(l).getDevopsCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
            waitingList.remove(0);
        } else if (traineeType == 5 && centreList.get(l).getStream() == 5){
            centreList.get(l).setBusinessCount(centreList.get(l).getBusinessCount() + 1);
            monthlyTrainees[waitingList.get(0) - 1]++;
            waitingList.remove(0);
        } else {
            // I think this is cool if you're reading this
            // It rotates next value that can be placed into a centre to index 0 if index 0 can't be placed, keeps waiting list order the same
            Collections.rotate(waitingList.subList(0 ,waitingList.indexOf(centreList.get(l).getStream()) + 1), 1);
        }
    }

    // Close centre after they have 3 months of low attendance
    public void closeCentre(List<Integer> listWithoutDuplicates, ArrayList<Centre> centreList, ArrayList<Centre> closedCentres, ArrayList<Integer> waitingList){
        for (int j = 0; j < listWithoutDuplicates.size(); j++) {
            int k = listWithoutDuplicates.get(j);
            closedCentres.add(centreList.get(k));
            logger.info("Closing centre " + "ID: " + centreList.get(k).getCentreID());
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
