package com.sparta.simulation.view;

import com.sparta.simulation.Centre;
import com.sparta.simulation.Client;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayManager {
    public int getNumberOfMonths(){
        System.out.println("How many months would you like to run the simulation");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public int choiceOfDisplay(){
        System.out.println("Would you like the display to be shown continuously(1) or all at once(2)");
        Scanner scanner = new Scanner (System.in);
        int choice = scanner.nextInt();
        if (choice > 2)
            choice = 2;
        else if(choice <1)
            choice = 1;
        return choice;
    }
    public void displayOpenCentres(ArrayList<Centre> openCentres){
        int trainingHub= 0, bootcamp=0, techCentre=0, fullCentre=0;
        for(Centre centre : openCentres){
            if(centre.getCentreType()==1) {
                trainingHub += 1;
                if(centre.getCentreCapacity() == 100)
                    fullCentre += 1;

            }
            if(centre.getCentreType()==2){
                bootcamp +=1;
                if(centre.getCentreCapacity() == 500)
                    fullCentre += 1;

            }
            if(centre.getCentreType()==3){
                techCentre +=1;
                if(centre.getCentreCapacity() == 200)
                    fullCentre += 1;

            }
        }
        System.out.println("Total number of open centres: "+ openCentres.size());
        System.out.println("Breakdown for each type below");
        System.out.println("Training Hubs: "+ trainingHub);
        System.out.println("Bootcamps: "+ bootcamp);
        System.out.println("Tech Centres: "+ techCentre);
        System.out.println("===========================================");
    }
  public void numberOfFullCentres(ArrayList<Centre> openCentres) {

      int trainingHub = 0, bootcamp = 0, techCentre = 0, fullCentre = 0;
      for (Centre centre : openCentres) {
          if (centre.getCentreType() == 1) {

              if (centre.getCentreCapacity() == 100) {
                  fullCentre += 1;
                  trainingHub += 1;
              }
          }
          if (centre.getCentreType() == 2) {

              if (centre.getCentreCapacity() == 500) {
                  fullCentre += 1;
                  bootcamp += 1;
              }
          }
          if (centre.getCentreType() == 3) {

              if (centre.getCentreCapacity() == 200) {
                  techCentre += 1;
                  fullCentre += 1;
              }
          }
      }

      if (fullCentre != 0) {
          System.out.println("Total number of full centres: " + fullCentre);
          System.out.println("Breakdown for each type below");
          System.out.println("Training Hubs: " + trainingHub);
          System.out.println("Bootcamps: " + bootcamp);
          System.out.println("Tech Centres: " + techCentre);
          System.out.println("===========================================");
      }
  }


    public void numberOfClosedCentres(ArrayList<Centre> closedCentres){
        if(!closedCentres.isEmpty()){
            int trainingHub= 0;
            int bootcamp=0;
            int techCentre=0;
            for(Centre centre : closedCentres){
                if(centre.getCentreType()==1)
                    trainingHub +=1;
                if(centre.getCentreType()==2)
                    bootcamp +=1;
                if(centre.getCentreType()==3)
                    techCentre +=1;
            }
            System.out.println("Total number of closed centres: "+ closedCentres.size());
            System.out.println("Breakdown for each type below");
            System.out.println("Training Hubs: "+ trainingHub);
            System.out.println("Bootcamps: "+ bootcamp);
            System.out.println("Tech Centres: "+ techCentre);
            System.out.println("===========================================");

        } else{
            System.out.println("There are no closed centres.");
        }
    }

    public void numberOnTraining(ArrayList<Centre> openCentres) {
        int java = 0, csharp = 0, data = 0, devOps = 0, business = 0, total = 0;
        for(int i = 0; i < openCentres.size(); i++){
            java += openCentres.get(i).getJavaCount();
            csharp += openCentres.get(i).getCsharpCount();
            data += openCentres.get(i).getDataCount();
            devOps += openCentres.get(i).getDevopsCount();
            business += openCentres.get(i).getBusinessCount();
            total+= openCentres.get(i).getTotal();
        }

        System.out.println("Total number of trainees on training: " + total);
        System.out.println("Breakdown for each type below");
        System.out.println("Java: "  + java);
        System.out.println("C#: " + csharp);
        System.out.println("Data: " + data);
        System.out.println("DevOps: " + devOps);
        System.out.println("Business: " + business);
        System.out.println("===========================================");
    }
    public void numberWaiting(ArrayList<Integer> waitingList){
        if (!waitingList.isEmpty()) {
            int java = 0, csharp = 0, data = 0, devOps = 0, business = 0;
            for (Integer trainee : waitingList) {
                if (trainee == 1)
                    java += 1;
                if (trainee == 2)
                    csharp += 1;
                if (trainee == 3)
                    data += 1;
                if (trainee == 4)
                    devOps += 1;
                if (trainee == 5)
                    business += 1;
            }
            System.out.println("Total number of trainees waiting: " + waitingList.size());
            System.out.println("Breakdown for each type below");
            System.out.println("Java: " + java);
            System.out.println("C#: " + csharp);
            System.out.println("Data: " + data);
            System.out.println("DevOps: " + devOps);
            System.out.println("Business: " + business);
            System.out.println("===========================================");
        } else {
            System.out.println("The waiting list is empty");
            System.out.println("===========================================");
        }


    }

    public void displayClients(ArrayList<Client> clientList, ArrayList<Client>closedClient){
        if(!clientList.isEmpty() || !closedClient.isEmpty()){
            System.out.println("Total happy clients: "+clientList.size());
            System.out.println("Total unhappy clients: "+closedClient.size());
        }
    }

}
