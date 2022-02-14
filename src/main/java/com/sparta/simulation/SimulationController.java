package com.sparta.simulation;

import java.util.ArrayList;
import java.util.Random;

public class SimulationController {
    public static void main(String[] args) {

        int months = 24;// user to define this

        for (int i = 0; i < months; i++) {
            Random r = new Random();
            int newTrainees = r.nextInt(50,100);

            for (int j = 0; j < newTrainees; j++) {
                ArrayList<Trainee> waitingList = new ArrayList();
                waitingList.add(new Trainee());


            }












        }
















    }

}
