package com.sparta.simulation.test.traineecontrollertest;

import com.sparta.simulation.model.TraineeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Random;

public class CreateTraineeTest {
    ArrayList<Integer> waitingList;
    Random r = new Random();
    int newTrainees;
    int javaCount;
    int csharpCount;
    int dataCount;
    int devopsCount;
    int businessCount;

    @BeforeEach
    void setUp() {
        waitingList = new ArrayList<>();
        javaCount = 0;
        csharpCount = 0;
        dataCount = 0;
        devopsCount = 0;
        businessCount = 0;
    }

    @ParameterizedTest
    @ValueSource(ints = {0,25,32,47,50,51,60,72,80,99,100})
    @DisplayName("Given new trainee input expect waiting list size should equal to input amount")
    public void givenNewTraineeInputExpectWaitingListSizeShouldEqualToInputAmount(int newTrainees){
        TraineeModel.createTraineeAddToWaitingList(newTrainees,waitingList);
        Assertions.assertEquals(newTrainees,waitingList.size());

    }
    
}
