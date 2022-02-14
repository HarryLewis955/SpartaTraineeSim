package com.sparta.simulation;

public class Trainee {
    private Integer traineeID;

    public Trainee(Integer traineeID){
        this.traineeID = traineeID;
    }

    public Integer getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(Integer traineeID) {
        this.traineeID = traineeID;
    }

    @Override
    public String toString() {
        return "Trainee " + traineeID;
    }

}
