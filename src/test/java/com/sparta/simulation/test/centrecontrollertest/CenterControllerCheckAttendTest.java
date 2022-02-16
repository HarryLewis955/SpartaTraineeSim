package com.sparta.simulation.test.centrecontrollertest;

import com.sparta.simulation.Centre;
import com.sparta.simulation.CentreController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CenterControllerCheckAttendTest {

    ArrayList<Centre> centreArrayList;
    ArrayList<Centre> removedCentreArrayList;
    int experimentMonth;

    @BeforeEach
    void setUp() {
        centreArrayList = new ArrayList<>();
        removedCentreArrayList = new ArrayList<>();
        experimentMonth = 0;
    }


    @Test
    @DisplayName("Given 25 students in each centre expect no centre should be removed after 3 months")
    public void given25StudentsInEachCentreExpectNoCentreShouldBeRemovedAfter3Months() {

        Centre c1 = new Centre(0,50,1,0);
        Centre c2= new Centre(1,50,2,0);
        Centre c3 = new Centre(2,50,3,1);
        c1.setJavaCount(25);
        c2.setDataCount(25);
        c3.setCsharpCount(25);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);

        experimentMonth = 3;

        for(int i =0;i<experimentMonth;i++) {
            CentreController.checkAttend(centreArrayList, removedCentreArrayList);
        }

        //System.out.println(centreArrayList.size());
        //System.out.println(removedCentreArrayList.size());
        Assertions.assertEquals(3,centreArrayList.size());
        Assertions.assertEquals(0,removedCentreArrayList.size());

    }

    @Test
    @DisplayName("Given 24 students in each centre expect all centre should be removed after 3 months")
    public void given24StudentsInEachCentreExpectAllShouldBeRemovedAfter3Months() {

        Centre c1 = new Centre(0,50,1,0);
        Centre c2= new Centre(1,50,2,0);
        Centre c3 = new Centre(2,50,3,1);
        c1.setJavaCount(24);
        c2.setDataCount(24);
        c3.setCsharpCount(24);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);

        experimentMonth = 3;

        for(int i =0;i<experimentMonth;i++) {
            CentreController.checkAttend(centreArrayList, removedCentreArrayList);
        }

        Assertions.assertEquals(3,centreArrayList.size());                                      //No removal method yet, otherwise it should be 0
        Assertions.assertEquals(3,removedCentreArrayList.size());

    }

    @Test
    @DisplayName("Given 0 students in each centre expect all centre should be removed after 3 months")
    public void given0StudentsInEachCentreExpectAllShouldBeRemovedAfter3Months() {

        Centre c1 = new Centre(0,50,1,0);
        Centre c2= new Centre(1,50,2,0);
        Centre c3 = new Centre(2,50,3,1);
        c1.setJavaCount(0);
        c2.setDataCount(0);
        c3.setCsharpCount(0);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);

        experimentMonth = 3;

        for(int i =0;i<experimentMonth;i++) {
            CentreController.checkAttend(centreArrayList, removedCentreArrayList);
        }

        Assertions.assertEquals(3,centreArrayList.size());                                      //No removal method yet, otherwise it should be 0
        Assertions.assertEquals(3,removedCentreArrayList.size());

    }

    @Test
    @DisplayName("Given total 25 students from each stream expect no centre should be removed after 3 months")
    public void givenTotal25StudentsFromEachStreamExpectNoCentreShouldBeRemovedAfter3Months() {

        Centre c1 = new Centre(0,50,1,0);
        c1.setJavaCount(5);
        c1.setDataCount(5);
        c1.setCsharpCount(5);
        c1.setBusinessCount(5);
        c1.setDevopsCount(5);
        centreArrayList.add(c1);

        experimentMonth = 3;

        for(int i =0;i<experimentMonth;i++) {
            CentreController.checkAttend(centreArrayList, removedCentreArrayList);
        }

        Assertions.assertEquals(1,centreArrayList.size());                                      //No removal method yet, otherwise it should be 0
        Assertions.assertEquals(0,removedCentreArrayList.size());

    }

    @Test
    @DisplayName("Given 24 students in each centre expect no centre should be removed after 2 months")
    public void given24StudentsInEachCentreExpectNoCentreShouldBeRemovedAfter2Months() {

        Centre c1 = new Centre(0,50,1,0);
        Centre c2= new Centre(1,50,2,0);
        Centre c3 = new Centre(2,50,3,1);
        c1.setJavaCount(24);
        c2.setDataCount(24);
        c3.setCsharpCount(24);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);

        experimentMonth = 2;

        for(int i =0;i<experimentMonth;i++) {
            CentreController.checkAttend(centreArrayList, removedCentreArrayList);
        }

        Assertions.assertEquals(3,centreArrayList.size());                                      //No removal method yet, otherwise it should be 0
        Assertions.assertEquals(0,removedCentreArrayList.size());

    }
}
