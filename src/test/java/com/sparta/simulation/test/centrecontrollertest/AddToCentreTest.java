package com.sparta.simulation.test.centrecontrollertest;

import com.sparta.simulation.Centre;
import com.sparta.simulation.CentreController;
import com.sparta.simulation.TraineeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AddToCentreTest {

    ArrayList<Integer> waitingList;
    ArrayList<Centre> centreList;
    CentreController centreController;

    int newTrainees;
    int javaCount;
    int csharpCount;
    int dataCount;
    int devopsCount;
    int businessCount;
    int[] monthlyTrainees;
    ArrayList<int[]> traineesInTraining;
    int idCount;
    int month;

    @BeforeEach
    void setUp() {
        centreController = new CentreController();
        waitingList = new ArrayList<>();
        centreList = new ArrayList<>();
        javaCount = 0;
        csharpCount = 0;
        dataCount = 0;
        devopsCount = 0;
        businessCount = 0;
        newTrainees = 0;
        idCount = 0;
        monthlyTrainees = new int[6];
        traineesInTraining = new ArrayList<>();
        month =0;
    }

    @Test
    @DisplayName("add 5 trainees to Boot Camp with 5 capacity expect all added into Boot Camp and waiting list should be empty")
    public void add5TraineesToBootCampWith5CapacityExpectAllAddedIntoHubAndEmptyWaitingList(){
        newTrainees = 5;
        int[] monthlyCount = TraineeController.createTrainee(newTrainees, javaCount,csharpCount,dataCount,devopsCount,businessCount,waitingList);

        centreController.createCentre(centreList, 5, 2,idCount,0,0);
        centreController.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees,traineesInTraining,month);

        Assertions.assertEquals(5,                                                          //Check 5 students added into the camp
                centreList.get(0).getJavaCount()
                + centreList.get(0).getCsharpCount()
                + centreList.get(0).getDataCount()
                + centreList.get(0).getDevopsCount()
                + centreList.get(0).getBusinessCount());

       Assertions.assertEquals(0, waitingList.size());

    }

    @Test
    @DisplayName("add 6 trainees to Boot Camp with 5 capacity expect 5 added into Boot Camp and waiting list should be 1")
    public void add6TraineesToBootCampWith5CapacityExpect5AddedIntoHubAnd1StudentInWaitingList(){
        newTrainees = 6;
        int[] monthlyCount = TraineeController.createTrainee(newTrainees, javaCount,csharpCount,dataCount,devopsCount,businessCount,waitingList);

        centreController.createCentre(centreList, 5, 2,idCount,0,0);
        centreController.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees,traineesInTraining,month);

        Assertions.assertEquals(5,                                                          //Check 5 students added into the camp
                centreList.get(0).getJavaCount()
                        + centreList.get(0).getCsharpCount()
                        + centreList.get(0).getDataCount()
                        + centreList.get(0).getDevopsCount()
                        + centreList.get(0).getBusinessCount());

        Assertions.assertEquals(1, waitingList.size());

    }

    @Test
    @DisplayName("add 7 trainees to 2 boot camp with total 7 capacity expect all added into Boot Camp and waiting list should be empty")
    public void add7TraineesTo2BootCampWithTotal7CapacityExpect5AddedIntoHubAndWaitingListEmpty(){
        newTrainees = 7;
        int[] monthlyCount = TraineeController.createTrainee(newTrainees, javaCount,csharpCount,dataCount,devopsCount,businessCount,waitingList);

        idCount = centreController.createCentre(centreList, 2, 2,idCount,0,0);
        idCount = centreController.createCentre(centreList, 5, 2,idCount,0,0);
        centreController.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees,traineesInTraining,month);

        Assertions.assertEquals(newTrainees, (centreList.get(0).getTotal()+ centreList.get(1).getTotal()));

        Assertions.assertEquals(0, waitingList.size());

    }

    @Test
    @DisplayName("add 50 trainees to 1 training hub with random 0 - 50 capacity expect hub full filled and rest will be on waiting list")
    public void add50TraineesTo1TrainingHubWith0To50CapacityExpectHubFullAndRestWillBeOnWaitingList(){
        newTrainees = 50;
        int[] monthlyCount = TraineeController.createTrainee(newTrainees, javaCount,csharpCount,dataCount,devopsCount,businessCount,waitingList);

        idCount = centreController.createCentre(centreList, 0, 1,idCount,1,0);      //random capacity for centreType 1
        centreController.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees,traineesInTraining,month);


        Assertions.assertEquals(centreList.get(0).getCentreCapacity(),
                centreList.get(0).getJavaCount()
                        + centreList.get(0).getCsharpCount()
                        + centreList.get(0).getDataCount()
                        + centreList.get(0).getDevopsCount()
                        + centreList.get(0).getBusinessCount());

        Assertions.assertEquals(newTrainees - centreList.get(0).getCentreCapacity(), waitingList.size());
        //System.out.println("Hub Capacity: " + centreList.get(0).getCentreCapacity());
        //System.out.println("Waiting List Size: " + waitingList.size());
    }

    @Test
    @DisplayName("add 50 trainees to 2 training hub with random 0 - 50 capacity expect hub full filled and rest will be on waiting list")
    public void add50TraineesTo2TrainingHubWith0To50CapacityExpectHubFullAndRestWillBeOnWaitingList(){
        newTrainees = 50;
        int[] monthlyCount = TraineeController.createTrainee(newTrainees, javaCount,csharpCount,dataCount,devopsCount,businessCount,waitingList);

        idCount = centreController.createCentre(centreList, 0, 1,idCount,2,0);      //random capacity 0-50 (inside method) for centreType 1
        centreController.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees,traineesInTraining,month);

        if(newTrainees >= centreList.get(0).getCentreCapacity() + centreList.get(1).getCentreCapacity()){
            Assertions.assertEquals(waitingList.size(), newTrainees - centreList.get(0).getCentreCapacity() - centreList.get(1).getCentreCapacity());
        }else{
            Assertions.assertEquals(0, waitingList.size());
        }
    }

    @Test
    @DisplayName("add 10 trainees 5 in Csharp stream into csharp tech center expected non-sharp student will be on waiting list")
    public void add10Trainees5InCsharpIntoCsharpTechCenterExpectedNonCSharpStudentWillBeOnWaitingList(){

        waitingList.add(2);
        waitingList.add(1);
        waitingList.add(2);
        waitingList.add(4);
        waitingList.add(5);
        waitingList.add(2);
        waitingList.add(3);
        waitingList.add(2);
        waitingList.add(1);
        waitingList.add(2);

        idCount = centreController.createCentre(centreList, 10, 3,idCount,0,2);

        centreController.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees,traineesInTraining,month);

        boolean isAnyStreamTwoStudentNotAdded = false;

        for(int i =0; i< waitingList.size();i++){
            if(waitingList.get(i) == 2){
                isAnyStreamTwoStudentNotAdded = true;
            }
        }

        Assertions.assertEquals(5, centreList.get(0).getTotal());                           //should be 5 stream 2 student added into centre
        Assertions.assertEquals(false, isAnyStreamTwoStudentNotAdded);
        //System.out.println("Waiting List: "+ waitingList.toString());
    }

}

