package com.sparta.simulation.test.clientcontrollertest;

import com.sparta.simulation.Client;
import com.sparta.simulation.ClientController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AddToCounterTest {
    ArrayList<Client> clientList;
    ArrayList<Client> closedClients;
    ClientController clientController;
    int clientIdCount;
    ArrayList<Integer> bench;

    @BeforeEach
    void setUp() {
        clientList = new ArrayList<>();
        closedClients = new ArrayList<>();
        clientController = new ClientController();
        clientIdCount = 0;
        bench = new ArrayList<>();
    }

    @Test
    @DisplayName("Given 4 trainee in client with 5 requirement expect client will be unhappy after 12 months and removed from the list")

    public void Given4TraineeInClientWith5RequirementExpectClientWillBeUnhappyAfter12MonthsAndRemovedFromTheList() {
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);

        Client client = new Client(clientIdCount, 1, 5, 5);
        clientIdCount++;
        clientList.add(client);
        clientController.addToClient(clientList, bench);

        for(int i = 0; i<12;i++) {
            clientController.addToCounter(clientList, closedClients);
        }
        Assertions.assertEquals(0, clientList.size());
    }

    @Test
    @DisplayName("Given 4 trainee in client with 5 requirement expect client will still in the list")

    public void Given4TraineeInClientWith5RequirementExpectClientWillStillInTheList() {
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);

        Client client = new Client(clientIdCount, 1, 5, 5);
        clientIdCount++;
        clientList.add(client);
        clientController.addToClient(clientList, bench);

        for(int i = 0; i<11;i++) {
            clientController.addToCounter(clientList, closedClients);
        }
        Assertions.assertEquals(1, clientList.size());
    }

    @Test
    @DisplayName("Given 5 trainee in client with 5 requirement expect client will be happy after 12 months")

    public void Given5TraineeInClientWith5RequirementExpectClientWillBeHappyAfter12Months() {
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);
        bench.add(1);

        Client client = new Client(clientIdCount, 1, 5, 5);
        clientIdCount++;
        clientList.add(client);
        clientController.addToClient(clientList, bench);

        for(int i = 0; i<12;i++) {
            clientController.addToCounter(clientList, closedClients);
        }
        Assertions.assertEquals(1, clientList.size());
        Assertions.assertEquals(true,clientList.get(0).isHappy());
    }

    @Test
    @DisplayName("Given two client one reached the requirement one did not expect one client remain in the list after 12 months")

    public void GivenTwoClientOneReachedTheRequirementOneDidNotExpectOneClientRemainInTheListAfter12months() {
        bench.add(1);
        bench.add(1);

        bench.add(2);
        bench.add(2);
        bench.add(2);

        Client client1 = new Client(clientIdCount, 1, 5, 5);
        clientIdCount++;
        clientList.add(client1);
        Client client2 = new Client(clientIdCount, 2, 4, 5);
        clientIdCount++;
        clientList.add(client2);

        Assertions.assertEquals(2, clientList.size());

        clientController.addToClient(clientList, bench);

        for(int i = 0; i<12;i++) {
            clientController.addToCounter(clientList, closedClients);
        }

        Assertions.assertEquals(1, clientList.size());
    }

    @Test
    @DisplayName("Given two client one reached the requirement one did not expect all client remain in the list after 11 months")

    public void GivenTwoClientOneReachedTheRequirementOneDidNotExpectAllClientRemainInTheListAfter11months() {
        bench.add(1);
        bench.add(1);

        bench.add(2);
        bench.add(2);
        bench.add(2);

        Client client1 = new Client(clientIdCount, 1, 5, 5);
        clientIdCount++;
        clientList.add(client1);
        Client client2 = new Client(clientIdCount, 2, 4, 5);
        clientIdCount++;
        clientList.add(client2);

        Assertions.assertEquals(2, clientList.size());

        clientController.addToClient(clientList, bench);

        for(int i = 0; i<11;i++) {
            clientController.addToCounter(clientList, closedClients);
        }

        Assertions.assertEquals(2, clientList.size());
    }


}
