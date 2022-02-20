package com.sparta.simulation;
import java.util.ArrayList;
import java.util.Random;

public class TraineeController {
    public static int[] createTrainee(int newTrainees, int javaCount, int csharpCount, int dataCount, int devopsCount, int businessCount,ArrayList<Integer> waitingList){
    //public static void createTrainee(int newTrainees, int javaCount, int csharpCount, int dataCount, int devopsCount, int businessCount,ArrayList<Integer> waitingList){
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
        return new int[] {javaCount, csharpCount, dataCount, devopsCount, businessCount};
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
