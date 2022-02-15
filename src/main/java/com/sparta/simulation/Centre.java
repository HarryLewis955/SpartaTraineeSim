package com.sparta.simulation;
public class Centre {

    private Integer centreID;
    private Integer centreCapacity;
    private Integer centreType;
    private Integer javaCount = 0;
    private Integer csharpCount = 0;
    private Integer dataCount = 0;
    private Integer devopsCount = 0;
    private Integer businessCount = 0;
    private Integer stream;
    private Integer lowAttendanceMonths = 0;



    public Centre(Integer centreID, Integer centreCapacity, Integer centreType, Integer stream){
        this.centreID = centreID;
        this.centreCapacity = centreCapacity;
        this.centreType = centreType;
        this.stream = stream;

    }

    public Integer getTotal(){
        return (javaCount + csharpCount + dataCount + devopsCount + businessCount);
    }


    public Integer getLowAttendanceMonths() {
        return lowAttendanceMonths;
    }

    public void setLowAttendanceMonths(Integer lowAttendanceMonths) {
        this.lowAttendanceMonths = lowAttendanceMonths;
    }

    public Integer getCentreID() {
        return centreID;
    }

    public void setCentreID(Integer centreID) {
        this.centreID = centreID;
    }

    public Integer getCentreType() {
        return centreType;
    }

    public void setCentreType(Integer centreType) {
        this.centreType = centreType;
    }

    public Integer getCentreCapacity() {
        return centreCapacity;
    }

    public void setCentreCapacity(Integer centreCapacity) {
        this.centreCapacity = centreCapacity;
    }

    public Integer getStream() {
        return stream;
    }

    public void setStream(Integer stream) {
        this.stream = stream;
    }

    public Integer getJavaCount() {
        return javaCount;
    }

    public void setJavaCount(Integer javaCount) {
        this.javaCount = javaCount;
    }

    public Integer getCsharpCount() {
        return csharpCount;
    }

    public void setCsharpCount(Integer csharpCount) {
        this.csharpCount = csharpCount;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public Integer getDevopsCount() {
        return devopsCount;
    }

    public void setDevopsCount(Integer devopsCount) {
        this.devopsCount = devopsCount;
    }

    public Integer getBusinessCount() {
        return businessCount;
    }

    public void setBusinessCount(Integer businessCount) {
        this.businessCount = businessCount;
    }

    @Override
    public String toString() {
        return "Centre ID: " + centreID + " | Capacity: " + centreCapacity + " | Centre Type " + centreType;
    }


}
