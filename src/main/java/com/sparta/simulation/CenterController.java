package com.sparta.simulation;

import java.util.ArrayList;
import java.util.Random;

public class CenterController {

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
    public static int centerTypeGen(int bootCampCount, Random r) {
        int centreType;
        if (bootCampCount < 3) {
            centreType = r.nextInt(1, 4);
        } else {
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
//    public ArrayList<Centre> createCentre(ArrayList<Centre> centreList, int centreCapacity, int centreType, int idCount){
    public int createCentre(ArrayList<Centre> centreList, int centreCapacity, int centreType, int idCount){
        Random r = new Random();

        if(centreType == 1) {
            int numberOfHub = r.nextInt(1, 4);
            for (int j = 0; j < numberOfHub; j++) {
                Centre centre = new Centre(idCount, r.nextInt(0,50), centreType, 0);
                centreList.add(centre);
                idCount++;
            }
        } else if (centreType == 2){
            Centre centre = new Centre(idCount, centreCapacity, centreType, 0);
            centreList.add(centre);
            idCount++;
        }else if (centreType == 3) {
            Centre centre = new Centre(idCount, centreCapacity, centreType, r.nextInt(1, 6));
            centreList.add(centre);
            idCount++;
        }
//        return centreList;
        return idCount;
    }



    // check for low attendance


}
