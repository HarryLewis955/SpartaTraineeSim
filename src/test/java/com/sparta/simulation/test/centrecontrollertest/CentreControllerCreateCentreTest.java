package com.sparta.simulation.test.centrecontrollertest;

import com.sparta.simulation.CentreController;
import com.sparta.simulation.Centre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class CentreControllerCreateCentreTest {

    ArrayList<Centre> centreArrayList;
    int idCount;
    Random r = new Random();

    @BeforeEach
    void setUp() {
        centreArrayList = new ArrayList<>();
        idCount = 0;

    }

    @Test
    @DisplayName("test if only 1 to 3 training hub created each time")
    public void testIfOnly1to3TrainingHubCreatedEachTime() {

        CentreController centerController = new CentreController();

        int experimentLoopTimes = 20;

        for (int i = 0; i < experimentLoopTimes; i++) {
            idCount = 0;
            centreArrayList.clear();

            boolean isOutOfRange = false;
            int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
            int techCentreStream = r.nextInt(1,6); // only for centre type 3
            idCount = centerController.createCentre(centreArrayList, 50, 1, idCount, numberOfTrainingHub, techCentreStream);

            if (idCount > 3 || idCount <= 0) {
                isOutOfRange = true;
            }

            Assertions.assertEquals(centreArrayList.size(), idCount);
            Assertions.assertEquals(false, isOutOfRange);

            //System.out.println("Training Hub Generated: " + idCount);
            //System.out.println("List size: " + centreArrayList.size());
        }
    }

        @Test
        @DisplayName("test if idCount order is correct")
        public void testIfIdCountOrderIsCorrect(){
            CentreController centerController = new CentreController();

            int experimentLoopTimes = 20;

            for(int i =0;i<experimentLoopTimes;i++) {

                boolean isOutOfRange = false;
                int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
                int techCentreStream = r.nextInt(1,6); // only for centre type 3
                idCount = centerController.createCentre(centreArrayList, 50, 1, idCount, numberOfTrainingHub, techCentreStream);


                Assertions.assertEquals(centreArrayList.size(), idCount);

                System.out.println("idCount: " + idCount);
                System.out.println("Generated in list: " + centreArrayList.size());
        }

    }

    @Test
    @DisplayName("generate bootcamp 20 times expected arraylist size and idCount should be 20")
    public void generateBootcamp20TimesExpectedArraylistSizeAndIdCountShouldBe20() {

        CentreController centerController = new CentreController();

        int experimentLoopTimes = 20;

        for(int i =0;i<experimentLoopTimes;i++) {

            int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
            int techCentreStream = r.nextInt(1,6); // only for centre type 3
            idCount = centerController.createCentre(centreArrayList, 50, 2, idCount, numberOfTrainingHub, techCentreStream);

            Assertions.assertEquals(centreArrayList.size(), idCount);

            //System.out.println("idCount: " + idCount);
            //System.out.println("Generated in list: " + centreArrayList.size());
        }

        Assertions.assertEquals(experimentLoopTimes, idCount);

    }


    @Test
    @DisplayName("generate Tech Centre 20 times expected arraylist size and idCount should be 20")
    public void generateTechCentre20TimesExpectedArraylistSizeAndIdCountShouldBe20() {

        CentreController centerController = new CentreController();

        int experimentLoopTimes = 20;

        for(int i =0;i<experimentLoopTimes;i++) {

            int numberOfTrainingHub = r.nextInt(1, 4); //only for centre type 1
            int techCentreStream = r.nextInt(1,6); // only for centre type 3
            idCount = centerController.createCentre(centreArrayList, 50, 2, idCount, numberOfTrainingHub, techCentreStream);

            Assertions.assertEquals(centreArrayList.size(), idCount);

            //System.out.println("idCount: " + idCount);
            //System.out.println("Generated in list: " + centreArrayList.size());
        }

        Assertions.assertEquals(experimentLoopTimes, idCount);

    }



}

