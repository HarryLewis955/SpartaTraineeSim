package com.sparta.simulation;
import java.util.ArrayList;
import java.util.Random;

public class TraineeController {
    public static ArrayList<Integer> createTrainee(int newTrainees, int javaCount, int csharpCount, int dataCount, int devopsCount, int businessCount,ArrayList<Integer> waitingList){
        Random r = new Random();
        for(int j = 0; j < newTrainees; j++){
            int traineeType = r.nextInt(1,6);
            waitingList.add(traineeType);
            if (traineeType == 1){
                javaCount++;
            } else if(traineeType == 2){
                csharpCount++;
            }else if (traineeType == 3){
                dataCount++;
            }else if (traineeType == 4){
                devopsCount++;
            } else if(traineeType == 5){
                businessCount++;
            }else{
                System.out.println("error - Trainee creation");
            }
        }
        return waitingList;
    }
}
