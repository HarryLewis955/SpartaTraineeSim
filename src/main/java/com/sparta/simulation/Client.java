package com.sparta.simulation;

public class Client {
    private Integer clientID;
    private int traineeRequirement;
    private int traineeType;
    private int clientCounter;
    private boolean isHappy;
    private int currentTrainees;
    private int randomTrainees;
    private int originalTraineesReq;

    public Client(Integer clientID, int traineeType,  int traineeRequirement,int  randomTrainees) {
        this.clientID = clientID;
        this.traineeRequirement = traineeRequirement;
        this.traineeType = traineeType;
        this.randomTrainees = randomTrainees;
        this.originalTraineesReq = traineeRequirement;
    }

    public Integer getDifference(){
        return traineeRequirement - currentTrainees;
    }
    public boolean isClientHappy(int currentTrainees, int traineeRequirement){
        if(clientCounter == 12){
            isHappy = currentTrainees == traineeRequirement;
        }
        return isHappy;
    }
    public Integer getClientID() {
        return clientID;
    }

    public int getOriginalTraineesReq() {
        return originalTraineesReq;
    }

    public void setOriginalTraineesReq(int originalTraineesReq) {
        this.originalTraineesReq = originalTraineesReq;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public int getTraineeRequirement() {
        return traineeRequirement;
    }

    public void setTraineeRequirement(int traineeRequirement) {
        this.traineeRequirement = traineeRequirement;
    }

    public int getTraineeType() {
        return traineeType;
    }

    public void setTraineeType(int traineeType) {
        this.traineeType = traineeType;
    }

    public int getClientCounter() {
        return clientCounter;
    }

    public void setClientCounter(int clientCounter) {
        this.clientCounter = clientCounter;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public int getCurrentTrainees() {
        return currentTrainees;
    }

    public void setCurrentTrainees(int currentTrainees) {
        this.currentTrainees = currentTrainees;
    }

    public int getRandomTrainees() {
        return randomTrainees;
    }

    public void setRandomTrainees(int randomTrainees) {
        this.randomTrainees = randomTrainees;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", traineeRequirement=" + traineeRequirement +
                ", originalRequirement="+ originalTraineesReq+
                ", traineeType=" + traineeType +
                ", clientCounter=" + clientCounter +
                ", isHappy=" + isHappy +
                ", currentTrainees=" + currentTrainees +
                ", randomTrainees=" + randomTrainees +
                '}';
    }
}
