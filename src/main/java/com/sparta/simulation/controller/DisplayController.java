package com.sparta.simulation.controller;
import com.sparta.simulation.model.Centre;
import com.sparta.simulation.model.Client;
import com.sparta.simulation.view.DisplayManager;
import java.util.ArrayList;

public class DisplayController {
    public void displayResults(ArrayList<Centre> centreList, ArrayList<Centre> closedCentres, ArrayList<Client> clientList, ArrayList<Client> closedClients, ArrayList<Integer> waitingList){
        DisplayManager dm = new DisplayManager();
        dm.displayOpenCentres(centreList);
        dm.numberOfFullCentres(centreList);
        dm.numberOfClosedCentres(closedCentres);
        dm.numberOnTraining(centreList);
        dm.numberWaiting(waitingList);
        dm.displayClients(clientList, closedClients);
    }
}
