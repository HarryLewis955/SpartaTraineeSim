package com.sparta.simulation.model;
import java.util.ArrayList;
import java.util.Random;

import com.sparta.simulation.model.Centre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TraineeModel {
    public static Logger logger = LogManager.getLogger("Controller - Trainee Controller");
    public static void createTraineeAddToWaitingList(int newTrainees, ArrayList<Integer> waitingList){
   logger.info("Creating new trainees");
        Random r = new Random();
        for(int j = 0; j < newTrainees; j++){
            int traineeType = r.nextInt(1,6);
            waitingList.add(traineeType);
        }
    }

    public static void removeFromCentreAddToBench(ArrayList<int[]> traineesInTraining, ArrayList<Integer> bench, ArrayList<Centre> centreList){
        for (int j = 0; j < traineesInTraining.size(); j++) {
            if (traineesInTraining.get(j)[5] == 3) {
                for (int l = 0; l < 5; l++) {
                    for (int k = 0; k < traineesInTraining.get(j)[l]; k++) {
                        bench.add(l + 1);
                        for (int m = centreList.size() - 1; m >= 0 ; m--) {
                            if (centreList.get(m).getJavaCount() > 0 && l + 1 == 1){
                                centreList.get(m).setJavaCount(centreList.get(m).getJavaCount() - 1);
                                break;
                            } else if (centreList.get(m).getCsharpCount() > 0 && l + 1 == 2){
                                centreList.get(m).setCsharpCount(centreList.get(m).getCsharpCount() - 1);
                                break;
                            } else if (centreList.get(m).getDataCount() > 0 && l + 1 == 3){
                                centreList.get(m).setDataCount(centreList.get(m).getDataCount() - 1);
                                break;
                            } else if (centreList.get(m).getDevopsCount() > 0 && l + 1 == 4){
                                centreList.get(m).setDevopsCount(centreList.get(m).getDevopsCount() - 1);
                                break;
                            } else if (centreList.get(m).getBusinessCount() > 0 && l + 1 == 5){
                                centreList.get(m).setBusinessCount(centreList.get(m).getBusinessCount() - 1);
                                break;
                            }
                        }
                    }
                }
            }
            traineesInTraining.get(j)[5]++;
        }
    }

}
