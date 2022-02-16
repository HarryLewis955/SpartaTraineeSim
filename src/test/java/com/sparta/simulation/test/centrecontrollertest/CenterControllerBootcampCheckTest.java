package com.sparta.simulation.test.centrecontrollertest;

import com.sparta.simulation.CenterController;
import com.sparta.simulation.Centre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CenterControllerBootcampCheckTest {

    ArrayList<Centre> centreArrayList;
    final int bootCampCount = 0;

    @BeforeEach
    void setUp() {
        centreArrayList = new ArrayList<>();
    }

    @Test
    @DisplayName("test given 0 bootcamp in the list expected return 0")
    public void given0BootcampInTheListExpectedReturn0(){

        int outputBootCampCount;

        Centre c1 = new Centre(1, 0, 1, 0);
        Centre c2 = new Centre(2, 0, 3, 0);
        centreArrayList.add(c1);
        centreArrayList.add(c2);

        CenterController centerController = new CenterController();
        outputBootCampCount = centerController.bootcampCheck(centreArrayList,bootCampCount);

        Assertions.assertEquals(0,outputBootCampCount);

    }

    @Test
    @DisplayName("test given 1 bootcamp in the list expected return 1")
    public void given1BootcampInTheListExpectedReturn1(){

        int outputBootCampCount;

        Centre c1 = new Centre(1, 0, 1, 0);
        Centre c2 = new Centre(2, 0, 3, 0);
        Centre c3 = new Centre(3, 0, 2, 0);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);

        CenterController centerController = new CenterController();
        outputBootCampCount = centerController.bootcampCheck(centreArrayList,bootCampCount);

        Assertions.assertEquals(1,outputBootCampCount);

    }

    @Test
    @DisplayName("test given 2 bootcamp in the list expected return 2")
    public void given3BootcampInTheListExpectedReturn3(){

        int outputBootCampCount;

        Centre c1 = new Centre(1, 0, 1, 0);
        Centre c2 = new Centre(2, 0, 3, 0);
        Centre c3 = new Centre(3, 0, 2, 0);
        Centre c4 = new Centre(4, 0, 2, 0);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);
        centreArrayList.add(c4);

        CenterController centerController = new CenterController();
        outputBootCampCount = centerController.bootcampCheck(centreArrayList,bootCampCount);

        Assertions.assertEquals(2,outputBootCampCount);

    }

    @Test
    @DisplayName("test given 3 bootcamp in the list expected return 3")
    public void given2BootcampInTheListExpectedReturn2(){

        int outputBootCampCount;

        Centre c1 = new Centre(1, 0, 1, 0);
        Centre c2 = new Centre(3, 0, 3, 0);
        Centre c3 = new Centre(3, 0, 2, 0);
        Centre c4 = new Centre(4, 0, 2, 0);
        Centre c5 = new Centre(5, 0, 2, 0);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);
        centreArrayList.add(c4);
        centreArrayList.add(c5);


        CenterController centerController = new CenterController();
        outputBootCampCount = centerController.bootcampCheck(centreArrayList,bootCampCount);

        Assertions.assertEquals(3,outputBootCampCount);

    }




}
