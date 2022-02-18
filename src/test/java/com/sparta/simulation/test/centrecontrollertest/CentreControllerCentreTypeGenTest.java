package com.sparta.simulation.test.centrecontrollertest;


import com.sparta.simulation.CentreController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class CentreControllerCentreTypeGenTest {

    int bootCampCount;
    boolean isBootCampGenerated;
    boolean isTrainingHubGenerated;
    boolean isTechCentreGenerated;
    Random r = new Random();

    @BeforeEach
    void setUp() {
        isBootCampGenerated = false;
        isTrainingHubGenerated = false;
        isTechCentreGenerated = false;
        bootCampCount = 0;
    }

    @Test
    @DisplayName("Given 0 bootcamp count expected maximum 2 bootcamp could generated")
    public void given0BootCampCountExpectedMaximum2BootcampCouldGenerated() {

        CentreController centreController = new CentreController();

        int experimentLoopTime = 50;

        for (int i = 0; i < experimentLoopTime; i++) {
            int centreType = r.nextInt(1,4);
            int outputCentreType = CentreController.centerTypeGen(bootCampCount, centreType);
            if (outputCentreType == 2) {
                isBootCampGenerated = true;
                bootCampCount++;
            }
        }
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(true, isBootCampGenerated);
        Assertions.assertEquals(2, bootCampCount);
    }

    @Test
    @DisplayName("Given 1 bootcamp count expected maximum 2 bootcamp could generated")
    public void given1BootCampCountExpectedMaximum2BootcampCouldGenerated(){

        bootCampCount = 1;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int centreType = r.nextInt(1,4);
            int outputCentreType = CentreController.centerTypeGen(bootCampCount, centreType);
            if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampCount++;
            }
        }
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(true,isBootCampGenerated);
        Assertions.assertEquals(2, bootCampCount);
    }


    @Test
    @DisplayName("Given 2 bootcamp count expect no bootcamp generated")
    public void given2BootCampCountExpectedNoBootcampGenerated(){

        bootCampCount = 2;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int centreType = r.nextInt(1,4);
            int outputCentreType = CentreController.centerTypeGen(bootCampCount, centreType);
            if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampCount++;
            }
        }
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(false,isBootCampGenerated);
        Assertions.assertEquals(2, bootCampCount);
    }

    @Test
    @DisplayName("Given 3 bootcamp count expected no bootcamp generated")
    public void given3BootCampCountExpectedNoBootcampGenerated(){

        bootCampCount = 3;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int centreType = r.nextInt(1,4);
            int outputCentreType = CentreController.centerTypeGen(bootCampCount, centreType);
            if(outputCentreType == 2){
                isBootCampGenerated = true;
            }
        }
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(false,isBootCampGenerated);
    }


    @Test
    @DisplayName("Check three centre can be generated")
    public void checkThreeCentreCanBeGenerated(){

        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int centreType = r.nextInt(1,4);
            int outputCentreType = CentreController.centerTypeGen(bootCampCount, centreType);
            if(outputCentreType == 1){
                isTrainingHubGenerated = true;
            } else if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampCount++;
            }else if(outputCentreType == 3){
                isTechCentreGenerated = true;
            }
        }
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(true,isBootCampGenerated);
        Assertions.assertEquals(true,isTrainingHubGenerated);
        Assertions.assertEquals(true,isTechCentreGenerated);
        Assertions.assertEquals(2, bootCampCount);
    }

    @Test
    @DisplayName("Check if centreType out of range")
    public void checkIfCentreTypeOutOfRange(){

        boolean isCentreTypeOutOfRange = false;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int centreType = r.nextInt(1,4);
            int outputCentreType = CentreController.centerTypeGen(bootCampCount, centreType);
            if(outputCentreType == 1){
                isTrainingHubGenerated = true;
            } else if(outputCentreType == 2){
                isBootCampGenerated = true;
                bootCampCount++;
            }else if(outputCentreType == 3){
                isTechCentreGenerated = true;
            }else
                isCentreTypeOutOfRange = true;
        }
        Assertions.assertEquals(false,isCentreTypeOutOfRange);
        Assertions.assertEquals(2, bootCampCount);
    }

}
