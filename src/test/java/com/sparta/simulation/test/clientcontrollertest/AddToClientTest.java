package com.sparta.simulation.test.clientcontrollertest;

import com.sparta.simulation.CentreController;
import com.sparta.simulation.Client;
import com.sparta.simulation.ClientController;
import com.sparta.simulation.TraineeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Random;

public class AddToClientTest {
    ArrayList<Client> clientList;
    ArrayList<Integer> bench;
    int clientIdCount;
    Random r;
    ClientController clientController;

    @BeforeEach
    void setUp() {
        clientList = new ArrayList<>();
        bench = new ArrayList<>();
        clientIdCount = 0;
        r = new Random();
        clientController = new ClientController();
    }

    @Test
    @DisplayName("7 trainees in the bench expected all java trainees added to java client")
    public void given7TraineesInTheBenchExpectedAllJavaTraineesAddedToJavaClient() {
        bench.add(1);
        bench.add(2);
        bench.add(3);
        bench.add(4);
        bench.add(5);
        bench.add(1);
        bench.add(1);

        //int clientStream = r.nextInt(1,6);
        //int clientRequirement = r.nextInt(15,51);
        //int clientMonthlyTrainees = r.nextInt(1,clientRequirement);

        Client client = new Client(clientIdCount, 1, 20, 5);
        clientIdCount++;
        clientList.add(client);

        clientController.addToClient(clientList, bench);

        Assertions.assertEquals(4, bench.size());
        Assertions.assertEquals(3,clientList.get(0).getCurrentTrainees());

        boolean isJavaTraineesInBench = false;

        for(int i = 0; i  <bench.size(); i++){
            if(bench.get(i) == 1){
                isJavaTraineesInBench = true;
            }
        }
        Assertions.assertEquals(false, isJavaTraineesInBench);
    }

    @Test
    @DisplayName("7 Java trainees in the bench expected 5 java trainees added to java client with 5 requirement per month")
    public void given7TraineesInTheBenchExpected5JavaTraineesAddedToJavaClientWith5RequirementPerMonth() {

        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);

        //int clientStream = r.nextInt(1,6);
        //int clientRequirement = r.nextInt(15,51);
        //int clientMonthlyTrainees = r.nextInt(1,clientRequirement);

        Client client = new Client(clientIdCount, 1, 20, 5);
        clientIdCount++;
        clientList.add(client);

        clientController.addToClient(clientList, bench);

        Assertions.assertEquals(2, bench.size());
        Assertions.assertEquals(5,clientList.get(0).getCurrentTrainees());

    }


    @Test
    @DisplayName("7 Java trainees in the bench expected 5 java trainees added to java client with 5 requirement per month and full filled after 2 months")
    public void given7TraineesInTheBenchExpected5JavaTraineesAddedToJavaClintWith5RequirementPerMonthAndFullFilledAfter2Months() {

        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);

        //int clientStream = r.nextInt(1,6);
        //int clientRequirement = r.nextInt(15,51);
        //int clientMonthlyTrainees = r.nextInt(1,clientRequirement);

        Client client = new Client(clientIdCount, 1, 20, 5);
        clientIdCount++;
        clientList.add(client);

        clientController.addToClient(clientList, bench);

        Assertions.assertEquals(2, bench.size());
        Assertions.assertEquals(5,clientList.get(0).getCurrentTrainees());

        clientController.addToClient(clientList, bench);

        Assertions.assertEquals(0, bench.size());
        Assertions.assertEquals(7,clientList.get(0).getCurrentTrainees());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @DisplayName("given 2 trainees in each trainee type expected 2 could be assigned base on the client type")
    public void given2TraineesInEachTraineeTypeExpected2CouldBeAssignedBaseOnTheClientType(int TraineeType){

        bench.add(1);
        bench.add(2);
        bench.add(3);
        bench.add(4);
        bench.add(5);
        bench.add(1);
        bench.add(2);
        bench.add(3);
        bench.add(4);
        bench.add(5);

        Client client = new Client(clientIdCount, TraineeType, 20, 5);
        clientIdCount++;
        clientList.add(client);

        clientController.addToClient(clientList, bench);

        Assertions.assertEquals(2,clientList.get(0).getCurrentTrainees());

        boolean isJavaTraineesInBench = false;

        for(int i = 0; i  <bench.size(); i++){
            if(bench.get(i) == TraineeType){
                isJavaTraineesInBench = true;
            }
        }
        Assertions.assertEquals(false, isJavaTraineesInBench);

    }



}
