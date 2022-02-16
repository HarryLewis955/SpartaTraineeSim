package com.sparta.simulation.test.centrecontrollertest;

import com.sparta.simulation.CenterController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class CenterControllerCentreTypeGenTest {

    int bootCampCount;
    boolean isBootCampGenerated;
    int bootCampGeneratedTimes;

    @BeforeEach
    void setUp() {
        boolean isBootCampGenerated = false;
        int bootCampGeneratedTimes = 0;
    }

    @Test
    @DisplayName("Given 0 bootcamp count expected bootcamp could generated")
    public void given0BootCampCountExpectedBootcampCouldGenerated(){

        bootCampCount = 0;
        bootCampGeneratedTimes = 0;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int outputCentreType = CenterController.centerTypeGen(bootCampCount, new Random());
            if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampGeneratedTimes ++;
            }
        }
        System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(true,isBootCampGenerated);

    }

    @Test
    @DisplayName("Given 1 bootcamp count expected bootcamp could generated")
    public void given1BootCampCountExpectedBootcampCouldGenerated(){

        bootCampCount = 1;
        bootCampGeneratedTimes = 0;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int outputCentreType = CenterController.centerTypeGen(bootCampCount, new Random());
            if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampGeneratedTimes ++;
            }
        }
        System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(true,isBootCampGenerated);
    }

    @Test
    @DisplayName("Given 2 bootcamp count expected no bootcamp generated")
    public void given2BootCampCountExpectedNoBootcampGenerated(){

        bootCampCount = 2;
        bootCampGeneratedTimes = 0;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int outputCentreType = CenterController.centerTypeGen(bootCampCount, new Random());
            if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampGeneratedTimes ++;
            }
        }
        System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(false,isBootCampGenerated);
    }

    @Test
    @DisplayName("Given 3 bootcamp count expected no bootcamp generated")
    public void given3BootCampCountExpectedNoBootcampGenerated(){

        bootCampCount = 3;
        bootCampGeneratedTimes = 0;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int outputCentreType = CenterController.centerTypeGen(bootCampCount, new Random());
            if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampGeneratedTimes ++;
            }
        }
        System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(false,isBootCampGenerated);
    }

}
