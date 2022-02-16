package com.sparta.simulation.test.centrecontrollertest;

import com.sparta.simulation.CentreController;
import com.sparta.simulation.Centre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CenterControllerCentreCapacityTest {

    int centreCapacity;
    int centreOpenTimes;
    final int centreOneFull = 100;
    final int centreTwoFull = 500;
    final int centreThreeFull = 200;
    ArrayList<Centre> centreArrayList;

    @BeforeEach
    void setUp() {
        centreArrayList = new ArrayList<>();
        Centre c1 = new Centre(1, 0, 1, 0);
        Centre c2 = new Centre(2, 0, 2, 0);
        Centre c3 = new Centre(3, 0, 3, 0);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);
    }


    @Test
    @DisplayName("test given centre capacity 40 and each centre open 1 time expected return 40")
    public void testGivenCentreCapacity40AndEachCentreOpen1TimeExpectedReturn40() {

        centreCapacity = 40;
        centreOpenTimes = 1;

        CentreController centerController = new CentreController();

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(2).getCentreCapacity());

    }

    @Test
    @DisplayName("test given centre capacity 40 and each centre open 2 time  expected return 80")
    public void testGivenCentreCapacity40AndEachCentreOpen2TimeExpectedReturn80() {

        centreCapacity = 40;
        centreOpenTimes = 2;

        CentreController centerController = new CentreController();

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(2).getCentreCapacity());

    }

    @Test
    @DisplayName("test given centre capacity 40 and each centre open 3 time expected centre 1 full")
    public void testGivenCentreCapacity40AndEachCentreOpen3TimExpectedCentre1Full() {

        centreCapacity = 40;
        centreOpenTimes = 3;

        CentreController centerController = new CentreController();

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreOneFull, centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(2).getCentreCapacity());

    }

    @Test
    @DisplayName("test given centre capacity 40 and each centre open 6 time expected centre 1 and 3 full")
    public void testGivenCentreCapacity40AndEachCentreOpen6TimExpectedCentre1And3Full() {

        centreCapacity = 40;
        centreOpenTimes = 6;

        CentreController centerController = new CentreController();

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreOneFull, centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreThreeFull, centreArrayList.get(2).getCentreCapacity());

    }

    @Test
    @DisplayName("test given centre capacity 40 and each centre open 13 time expected all centre full")
    public void testGivenCentreCapacity40AndEachCentreOpen13TimExpectedAllCentreFull() {

        centreCapacity = 40;
        centreOpenTimes = 13;

        CentreController centerController = new CentreController();

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreOneFull,centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreTwoFull,centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreThreeFull,centreArrayList.get(2).getCentreCapacity());
    }

    @Test
    @DisplayName("test given centre capacity 40 and more centre open 13 time expected all centre full")
    public void testGivenCentreCapacity40AndMoreCentreOpen13TimExpectedAllCentreFull() {

        centreCapacity = 40;
        centreOpenTimes = 13;

        Centre c4 = new Centre(4, 0, 3, 0);
        Centre c5 = new Centre(5, 0, 2, 0);
        Centre c6 = new Centre(6, 0, 1, 0);

        centreArrayList.add(c4);
        centreArrayList.add(c5);
        centreArrayList.add(c6);

        CentreController centerController = new CentreController();

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreOneFull,centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreTwoFull,centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreThreeFull,centreArrayList.get(2).getCentreCapacity());
        Assertions.assertEquals(centreThreeFull,centreArrayList.get(3).getCentreCapacity());
        Assertions.assertEquals(centreTwoFull,centreArrayList.get(4).getCentreCapacity());
        Assertions.assertEquals(centreOneFull,centreArrayList.get(5).getCentreCapacity());
    }


    @Test
    @DisplayName("test given centre capacity 40 and more centre open 5 time expected centre 1 and 3 full")
    public void testGivenCentreCapacity40AndMoreCentreOpen13TimExpectedCentre1And3Full() {

        centreCapacity = 40;
        centreOpenTimes = 5;

        Centre c4 = new Centre(4, 0, 3, 0);
        Centre c5 = new Centre(5, 0, 2, 0);
        Centre c6 = new Centre(6, 0, 1, 0);

        centreArrayList.add(c4);
        centreArrayList.add(c5);
        centreArrayList.add(c6);

        CentreController centerController = new CentreController();

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreOneFull,centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreThreeFull,centreArrayList.get(2).getCentreCapacity());
        Assertions.assertEquals(centreThreeFull, centreArrayList.get(3).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(4).getCentreCapacity());
        Assertions.assertEquals(centreOneFull,centreArrayList.get(5).getCentreCapacity());
    }

    @Test
    @DisplayName("test given invalid centre type")
    public void testGivenInvalidCentreType() {

        centreCapacity = 40;
        centreOpenTimes = 1;

        CentreController centerController = new CentreController();
        Centre c4 = new Centre(4, 0, 0, 0);
        Centre c5 = new Centre(5, 0, 4, 0);

        centreArrayList.add(c4);
        centreArrayList.add(c5);

        for (int i = 0; i < centreOpenTimes; i++) {
            centerController.centreCapacity(centreArrayList, 40);
        }

        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(0).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(1).getCentreCapacity());
        Assertions.assertEquals(centreCapacity * centreOpenTimes, centreArrayList.get(2).getCentreCapacity());
        Assertions.assertEquals(0, centreArrayList.get(3).getCentreCapacity());
        Assertions.assertEquals(0, centreArrayList.get(4).getCentreCapacity());
    }
}
