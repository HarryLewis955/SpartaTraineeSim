package com.sparta.simulation.test.centrecontrollertest;

import com.sparta.simulation.model.Centre;
import com.sparta.simulation.model.CentreModel;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CloseCentreTest {

    List<Integer> temporaryList;
    ArrayList<Integer> centresToClose;
    List<Integer> listWithoutDuplicates;
    ArrayList<Centre> centreArrayList;
    ArrayList<Integer> waitingList;
    ArrayList<Centre> closedCentres;
    CentreModel centreModel;

    @BeforeEach
    void setUp() {
        centresToClose = new ArrayList<>();
        listWithoutDuplicates = new ArrayList<>();
        centreArrayList = new ArrayList<>();
        waitingList = new ArrayList<>();
        closedCentres = new ArrayList<>();
        centreModel = new CentreModel();
    }

    @Test
    @DisplayName("Given 4 centres created with 2 centres low attendance with 24 students expected closed after 3 month and 48 student return to waiting list")
    public void twoLowAttendanceCentreShouldClosedAfter3MonthAnd48StudentReturnedWaitingList(){

        Centre c1 = new Centre(0,50,1,0);
        Centre c2= new Centre(1,50,2,0);
        Centre c3 = new Centre(2,50,3,1);
        Centre c4 = new Centre(3,50,1,0);
        c1.setJavaCount(24);
        c2.setDataCount(25);
        c3.setCsharpCount(24);
        c4.setDevopsCount(25);
        centreArrayList.add(c1);
        centreArrayList.add(c2);
        centreArrayList.add(c3);
        centreArrayList.add(c4);

        int month = 3;
        for (int i = 0; i<month;i++) {
            CentreModel.checkAttend(centreArrayList, centresToClose);
        }
        temporaryList = centresToClose.stream().distinct().toList();

        new LinkedList<>(temporaryList).descendingIterator().forEachRemaining(listWithoutDuplicates :: add);

        centreModel.closeCentre(listWithoutDuplicates, centreArrayList, closedCentres, waitingList);

        Assertions.assertEquals(48, waitingList.size());                                                //24 + 24 student should return to waiting list

    }

    @Test
    @DisplayName("Given 1 training hub created with 20 students of different stream expected closed after 3 month and all students return to waiting list with correct stream number")
    public void Given1TrainingHubExpectedAll20StudentReturnToWaitingListWithCorrectStreamNumber(){

        Centre c1 = new Centre(0,50,1,0);
        c1.setJavaCount(2);
        c1.setCsharpCount(3);
        c1.setDataCount(4);
        c1.setDevopsCount(5);
        c1.setBusinessCount(6);
        centreArrayList.add(c1);

        int output1 = 0;
        int output2 = 0;
        int output3 = 0;
        int output4 = 0;
        int output5 = 0;

        int month = 3;
        for (int i = 0; i<month;i++) {
            CentreModel.checkAttend(centreArrayList, centresToClose);
        }
        temporaryList = centresToClose.stream().distinct().toList();

        new LinkedList<>(temporaryList).descendingIterator().forEachRemaining(listWithoutDuplicates :: add);

        centreModel.closeCentre(listWithoutDuplicates, centreArrayList, closedCentres, waitingList);

        Assertions.assertEquals(20, waitingList.size());
        for (int i=0;i<waitingList.size();i++){
            if(waitingList.get(i) == 1){
                output1++;
            }else if(waitingList.get(i) == 2){
                output2++;
            }else if(waitingList.get(i) == 3){
                output3++;
            }else if(waitingList.get(i) == 4){
                output4++;
            }else if(waitingList.get(i) == 5){
                output5++;
            }
        }
        Assertions.assertEquals(2, output1);
        Assertions.assertEquals(3, output2);
        Assertions.assertEquals(4, output3);
        Assertions.assertEquals(5, output4);
        Assertions.assertEquals(6, output5);

    }

    @Test
    @DisplayName("Given 1 boot camp created with 20 students of different stream expected closed after 3 month and all students return to waiting list with correct stream number")
    public void given1BootCampExpectedAll20StudentReturnToWaitingListWithCorrectStreamNumber(){

        Centre c1 = new Centre(0,50,2,0);
        c1.setJavaCount(2);
        c1.setCsharpCount(3);
        c1.setDataCount(4);
        c1.setDevopsCount(5);
        c1.setBusinessCount(6);
        centreArrayList.add(c1);

        int output1 = 0;
        int output2 = 0;
        int output3 = 0;
        int output4 = 0;
        int output5 = 0;

        int month = 3;
        for (int i = 0; i<month;i++) {
            CentreModel.checkAttend(centreArrayList, centresToClose);
        }
        temporaryList = centresToClose.stream().distinct().toList();

        new LinkedList<>(temporaryList).descendingIterator().forEachRemaining(listWithoutDuplicates :: add);

        centreModel.closeCentre(listWithoutDuplicates, centreArrayList, closedCentres, waitingList);

        Assertions.assertEquals(20, waitingList.size());
        for (int i=0;i<waitingList.size();i++){
            if(waitingList.get(i) == 1){
                output1++;
            }else if(waitingList.get(i) == 2){
                output2++;
            }else if(waitingList.get(i) == 3){
                output3++;
            }else if(waitingList.get(i) == 4){
                output4++;
            }else if(waitingList.get(i) == 5){
                output5++;
            }
        }
        Assertions.assertEquals(2, output1);
        Assertions.assertEquals(3, output2);
        Assertions.assertEquals(4, output3);
        Assertions.assertEquals(5, output4);
        Assertions.assertEquals(6, output5);
    }



    @AfterEach
    void After(){
        listWithoutDuplicates.clear();
        centresToClose.clear();
    }



}
