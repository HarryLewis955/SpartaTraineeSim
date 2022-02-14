package com.sparta.simulation;
public class Centre {

    private Integer centreID;
    private Integer centreCapacity;

    public Centre(Integer centreID, Integer centreCapacity){
        this.centreID = centreID;
        this.centreCapacity = centreCapacity;


    }

    public Integer getCentreID() {
        return centreID;
    }

    public void setCentreID(Integer centreID) {
        this.centreID = centreID;
    }

    public Integer getCentreCapacity() {
        return centreCapacity;
    }

    public void setCentreCapacity(Integer centreCapacity) {
        this.centreCapacity = centreCapacity;
    }

    @Override
    public String toString() {
        return "Centre ID: " + centreID + " | Capacity: " + centreCapacity;
    }


}
