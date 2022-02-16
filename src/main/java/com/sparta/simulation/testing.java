package com.sparta.simulation;

import java.util.ArrayList;
import java.util.Collections;

public class testing {
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(5);
        l.add(7);
        l.add(4);
        l.add(4);
        l.add(5);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(1);
        l.add(9);
        l.add(8);
        l.add(6);
        l.add(2);
        l.add(4);
        l.add(7);
        l.add(4);
        System.out.println(l);

        Collections.rotate(l.subList(0 ,l.indexOf(1) + 1), 1);
        System.out.println(l);

        for(int i=0;i<10;i++) {
            System.out.println("Inside the Fist loop "+i);
            for(int j=0;j<10;j++) {
                System.out.println("Inside the second loop "+j);
            }
        }


        System.out.println(0%2);

    }
}
