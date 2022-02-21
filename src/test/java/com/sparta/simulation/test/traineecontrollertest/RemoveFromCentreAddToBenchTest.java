package com.sparta.simulation.test.traineecontrollertest;

import com.sparta.simulation.model.Centre;
import com.sparta.simulation.model.CentreModel;
import com.sparta.simulation.model.TraineeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RemoveFromCentreAddToBenchTest {
    CentreModel centreModel;
    ArrayList<int[]> traineesInTraining;
    int[] monthlyTrainees;

    ArrayList<Integer> waitingList;
    ArrayList<Centre> centreList;
    ArrayList<Integer> bench;

    int newTrainees;
    int javaCount;
    int csharpCount;
    int dataCount;
    int devopsCount;
    int businessCount;

    int idCount;
    int month;

    @BeforeEach
    void setUp() {
        centreModel = new CentreModel();
        traineesInTraining = new ArrayList<>();
        monthlyTrainees = new int[6];

        centreModel = new CentreModel();
        javaCount = 0;
        csharpCount = 0;
        dataCount = 0;
        devopsCount = 0;
        businessCount = 0;
        newTrainees = 0;
        idCount = 0;
        monthlyTrainees = new int[6];
        traineesInTraining = new ArrayList<>();
        month =0;

        waitingList = new ArrayList<>();
        centreList = new ArrayList<>();
        bench = new ArrayList<>();
    }


    @Test
    @DisplayName("Expect all 5 trainee in the centre go to the bench after 3 month")
    public void expectAll5TraineeInTheCentreGoToTheBenchAfter3Month() {

        newTrainees = 5;
        TraineeModel.createTraineeAddToWaitingList(newTrainees,waitingList);

        centreModel.createCentre(centreList, 5, 2,idCount,0,0);
        centreModel.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees);
        traineesInTraining.add(monthlyTrainees);

        month = 4;
        for(int i = 0; i < month; i++){
            TraineeModel.removeFromCentreAddToBench(traineesInTraining, bench, centreList);
        }

        Assertions.assertEquals(newTrainees, bench.size());
    }

    @Test
    @DisplayName("Expect all 10 trainee in two centre go to the bench after 3 month")
    public void expectAll10TraineeIn2CentreGoToTheBenchAfter3Month() {

        newTrainees = 10;
        TraineeModel.createTraineeAddToWaitingList(newTrainees,waitingList);

        centreModel.createCentre(centreList, 5, 2,idCount,0,0);
        centreModel.createCentre(centreList, 5, 2,idCount,0,0);

        centreModel.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees);
        traineesInTraining.add(monthlyTrainees);

        month = 4;
        for(int i = 0; i < month; i++){
            TraineeModel.removeFromCentreAddToBench(traineesInTraining, bench, centreList);
        }

        Assertions.assertEquals(newTrainees, bench.size());
    }

    @Test
    @DisplayName("Expect all 10 trainee in two different centre go to the bench after 3 month")
    public void expectAll10TraineeIn2DifferentCentreGoToTheBenchAfter3Month() {

        newTrainees = 10;
        TraineeModel.createTraineeAddToWaitingList(newTrainees, waitingList);

        centreModel.createCentre(centreList, 5, 2,idCount,0,0);
        centreModel.createCentre(centreList, 5, 1,idCount,1,0);

        centreModel.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees);
        traineesInTraining.add(monthlyTrainees);

        month = 4;
        for(int i = 0; i < month; i++){
            TraineeModel.removeFromCentreAddToBench(traineesInTraining, bench, centreList);
        }

        Assertions.assertEquals(newTrainees, bench.size());
    }

    @Test
    @DisplayName("Expect all 10 trainee in two different centre stay in the centre on 3rd month")
    public void expectAll10TraineeIn2DifferentCentreStayInTheCentreOn3rdMonth() {

        newTrainees = 10;
        TraineeModel.createTraineeAddToWaitingList(newTrainees, waitingList);

        centreModel.createCentre(centreList, 5, 2,idCount,0,0);
        centreModel.createCentre(centreList, 5, 1,idCount,1,0);

        centreModel.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees);
        traineesInTraining.add(monthlyTrainees);

        month = 3;
        for(int i = 0; i < month; i++){
            TraineeModel.removeFromCentreAddToBench(traineesInTraining, bench, centreList);
        }

        Assertions.assertEquals(0, bench.size());
    }

    @Test
    @DisplayName("Expect all 5 trainee in the centre go to the bench after 3 month and stream are match")
    public void expectAll5TraineeInTheCentreGoToTheBenchAfter3MonthAndStreamAreMatch() {

        newTrainees = 5;
        TraineeModel.createTraineeAddToWaitingList(newTrainees, waitingList);
        int JavaTrainees = 0;
        int CSharpTrainees = 0;
        int DataTrainees = 0;
        int DevOpsTrainees = 0;
        int BusinessTrainees = 0;

        centreModel.createCentre(centreList, 5, 2,idCount,0,0);
        centreModel.addToCentre(waitingList, centreList, waitingList.size(),monthlyTrainees);
        traineesInTraining.add(monthlyTrainees);

        JavaTrainees = centreList.get(0).getJavaCount();
        CSharpTrainees =  centreList.get(0).getCsharpCount();
        DataTrainees =  centreList.get(0).getDataCount();
        DevOpsTrainees =  centreList.get(0).getDevopsCount();
        BusinessTrainees =  centreList.get(0).getBusinessCount();

        month = 4;
        for(int i = 0; i < month; i++){
            TraineeModel.removeFromCentreAddToBench(traineesInTraining, bench, centreList);
        }

        int BenchJavaTrainees = 0;
        int BenchCSharpTrainees = 0;
        int BenchDataTrainees = 0;
        int BenchDevOpsTrainees = 0;
        int BenchBusinessTrainees = 0;

        for(int i=0;i<bench.size();i++){
            if(bench.get(i)==1){
                BenchJavaTrainees++;
            }else if((bench.get(i)==2)){
                BenchCSharpTrainees++;
            }else if((bench.get(i)==3)){
                BenchDataTrainees++;
            }
            else if((bench.get(i)==4)){
                BenchDevOpsTrainees++;
            }
            else if((bench.get(i)==5)){
                BenchBusinessTrainees++;
            }

        }

        Assertions.assertEquals(newTrainees, bench.size());


        Assertions.assertEquals(BenchJavaTrainees,JavaTrainees);
        Assertions.assertEquals(BenchCSharpTrainees,CSharpTrainees);
        Assertions.assertEquals(BenchDataTrainees,DataTrainees);
        Assertions.assertEquals(BenchDevOpsTrainees,DevOpsTrainees);
        Assertions.assertEquals(BenchBusinessTrainees,BusinessTrainees);

    }


}
