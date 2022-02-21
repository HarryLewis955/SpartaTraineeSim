package com.sparta.simulation.model;
import com.sparta.simulation.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Collections;

public class ClientModel {
    public static Logger logger = LogManager.getLogger("Controller - Client Controller");
    public void addToClient(ArrayList<Client> clientList, ArrayList<Integer> bench){
        // Loop through clients and give them the correct trainees from the bench
        for (int j = 0; j < clientList.size(); j++) {
            int needed = clientList.get(j).getDifference();
            int streamNeeded = clientList.get(j).getTraineeType();
            int occurrences = Collections.frequency(bench, streamNeeded);
            int min = Math.min(occurrences, clientList.get(j).getRandomTrainees());
            for (int k = 0; k < min; k++) {
                if (clientList.get(j).getTraineeRequirement() > clientList.get(j).getCurrentTrainees()) {
                    if (needed > 0) {
                        clientList.get(j).setCurrentTrainees(clientList.get(j).getCurrentTrainees() + 1);
                        bench.remove(bench.indexOf(streamNeeded));
                    }
                }
            }
        }
    }

    public void addToCounter( ArrayList<Client> clientList, ArrayList<Client> closedClients){
        for (int j = 0; j < clientList.size(); j++) {
            clientList.get(j).setClientCounter(clientList.get(j).getClientCounter() + 1);
            if(clientList.get(j).getClientCounter() == 12 && clientList.get(j).getCurrentTrainees() == clientList.get(j).getTraineeRequirement()){
                clientList.get(j).setClientCounter(0);
                clientList.get(j).setTraineeRequirement(clientList.get(j).getOriginalTraineesReq()+clientList.get(j).getTraineeRequirement());
                clientList.get(j).setHappy(true);

            } else if (clientList.get(j).getClientCounter() == 12 && clientList.get(j).getCurrentTrainees() != clientList.get(j).getTraineeRequirement()){
                closedClients.add(clientList.get(j));
                clientList.get(j).setHappy(false);
                logger.info("Removing client " + clientList.get(j).getClientID() + " as they are not happy anymore :(");
                clientList.remove(clientList.get(j));
            }
        }
    }

}
