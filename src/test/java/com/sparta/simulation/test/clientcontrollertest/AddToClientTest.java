package com.sparta.simulation.test.clientcontrollertest;

import com.sparta.simulation.model.Client;
import com.sparta.simulation.model.ClientModel;
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
    ClientModel clientModel;

    @BeforeEach
    void setUp() {
        clientList = new ArrayList<>();
        bench = new ArrayList<>();
        clientIdCount = 0;
        r = new Random();
        clientModel = new ClientModel();
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

        clientModel.addToClient(clientList, bench);

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

        clientModel.addToClient(clientList, bench);

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

        clientModel.addToClient(clientList, bench);

        Assertions.assertEquals(2, bench.size());
        Assertions.assertEquals(5,clientList.get(0).getCurrentTrainees());

        clientModel.addToClient(clientList, bench);

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

        clientModel.addToClient(clientList, bench);

        Assertions.assertEquals(2,clientList.get(0).getCurrentTrainees());

        boolean isJavaTraineesInBench = false;

        for(int i = 0; i  <bench.size(); i++){
            if(bench.get(i) == TraineeType){
                isJavaTraineesInBench = true;
            }
        }
        Assertions.assertEquals(false, isJavaTraineesInBench);

    }

    @Test
    @DisplayName("Given 30 Java trainees with client require Max 20 student 5 per month expected 10 students remain in bench after 5 months")
    public void Given30JavaTraineesClientRequireMax20Student5PerMonthExpected10studentsRemainInBenchAfter5Months() {
        for(int i =0; i < 30; i ++){
            bench.add(1);
        }
        Client client = new Client(clientIdCount, 1, 20, 5);
        clientIdCount++;
        clientList.add(client);

        clientModel.addToClient(clientList, bench);
        Assertions.assertEquals(5,clientList.get(0).getCurrentTrainees());

        clientModel.addToClient(clientList, bench);
        Assertions.assertEquals(10,clientList.get(0).getCurrentTrainees());

        clientModel.addToClient(clientList, bench);
        Assertions.assertEquals(15,clientList.get(0).getCurrentTrainees());

        clientModel.addToClient(clientList, bench);
        Assertions.assertEquals(20,clientList.get(0).getCurrentTrainees());

        clientModel.addToClient(clientList, bench);
        Assertions.assertEquals(20,clientList.get(0).getCurrentTrainees());

        Assertions.assertEquals(10,bench.size());
    }




}
