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
    boolean isTrainingHubGenerated;
    boolean isTechCentreGenerated;
    int bootCampGeneratedTimes;


    @BeforeEach
    void setUp() {
        isBootCampGenerated = false;
        isTrainingHubGenerated = false;
        isTechCentreGenerated = false;
        bootCampGeneratedTimes = 0;
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
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
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
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
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
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
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
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(false,isBootCampGenerated);
    }

    @Test
    @DisplayName("Check three centre can be generated")
    public void checkThreeCentreCanBeGenerated(){

        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int outputCentreType = CenterController.centerTypeGen(bootCampCount, new Random());
            if(outputCentreType == 1){
                isTrainingHubGenerated = true;
            } else if(outputCentreType == 2){
                isBootCampGenerated = true;
            }else if(outputCentreType == 3){
                isTechCentreGenerated = true;
            }
        }
        //System.out.println("Bootcamp generated: " + bootCampGeneratedTimes);
        Assertions.assertEquals(true,isBootCampGenerated);
        Assertions.assertEquals(true,isTrainingHubGenerated);
        Assertions.assertEquals(true,isTechCentreGenerated);
    }

    @Test
    @DisplayName("Check if centreType out of range")
    public void checkIfCentreTypeOutOfRange(){

        boolean isCentreTypeOutOfRange = false;
        int experimentLoopTime = 50;

        for(int i = 0; i <experimentLoopTime; i++) {
            int outputCentreType = CenterController.centerTypeGen(bootCampCount, new Random());
            if(outputCentreType == 1){
                isTrainingHubGenerated = true;
            } else if(outputCentreType == 2){
                isBootCampGenerated = true;
            }else if(outputCentreType == 3){
                isTechCentreGenerated = true;
            }else
                isCentreTypeOutOfRange = true;
        }
        Assertions.assertEquals(false,isCentreTypeOutOfRange);
    }

}
