package com.ClassDiagrams.Design;

import com.ClassDiagrams.Design.Models.*;

import java.util.Scanner;
import static com.ClassDiagrams.Design.InputClient.getScanner;
import static java.lang.System.out;

public class Controller {
    private final DataRepository repository;
    private User activeUser;

    public Controller(DataRepository repository){
        this.repository = repository;
    }


    public Admin checkAdminUser(String username, String password, String adminPassword) {
        for(Admin a: repository.getAdmins()){
            if(a.login(username,password,adminPassword)) {
                out.println(a+" has logged in");
                activeUser = a;
                return a;
            }
        }
        return null;
    }

    public Technician checkTechnicianUser(String username, String password) {
        for(Technician t: repository.getTechnicians()){
            if(t.login(username,password)){
                out.println(t+" has logged in");
                activeUser = t;
                return t;
            }
        }
        return null;
    }

    public void adminViewController(){
        while(true) {
            out.println("Welcome "+activeUser.getName());
            out.println("Menu");
            out.println("1. Edit Transformers");
            out.println("2. Edit Buildings");
            out.println("3. Add Technician users");
            out.println("4. Initialize connections");
            out.println("5. Toggle a Transformer");
            out.println("6. Shift Building");
            out.println("7. Log out");
            out.print("Enter your choice");
            switch (getScanner().nextInt()) {
                case 1:
                    out.println("Choose a transformer to edit");
                    showTransformerListing();
                    repository.getTransformers().get(getScanner().nextInt()).modify();
                    break;
                case 2:
                    out.println("Choose a building to edit");
                    showBuildingListing();
                    buildingModifyViewController(getScanner().nextInt());
                    break;
                case 3:
                    out.println("Add a technician");
                    String name = getScanner().next();
                    String email = getScanner().next();
                    String pass = getScanner().next();
                    System.out.println("Technician has been added successfully.");
                    repository.getTechnicians().add(new Technician(repository.getTechnicians().size(),name, email, pass));
                    break;
                case 4:
                    System.out.println("Connections have been initialized successfully.");
                    repository.initializeConnections();
                    break;
                case 5:
                    out.println("Choose a transformer to toggle");
                    showTransformerListing();
                    toggleATransformer(getScanner().nextInt());
                    break;
                case 6:
                    out.println("Choose a building to edit");
                    showBuildingListing();
                    int b = getScanner().nextInt();
                    out.println("Choose a transformer to connect this building to.");
                    showTransformerListing();
                    int t = getScanner().nextInt();
                    buildingShiftProcedure(b,t);
                case 7:
                    activeUser.logout();
                    System.out.println("User has been logged out successfully.");
                    return;
                default: out.println("Incorrect input");
            }
        }
    }

    public void technicianViewController() {
        while(true) {
            out.println("Welcome "+activeUser.getName());
            out.println("Menu");
            out.println("1. Initialize connections");
            out.println("2. Toggle a Transformer");
            out.println("3. Shift a building");
            out.println("4. Log out");
            out.print("Enter your choice");
            switch (getScanner().nextInt()) {
                case 1:
                    System.out.println("Connections have been initialized successfully.");
                    repository.initializeConnections();
                    break;
                case 2:
                    out.println("Choose a transformer to toggle");
                    showTransformerListing();
                    toggleATransformer(getScanner().nextInt());
                    break;
                case 3:
                    out.println("Choose a building to edit");
                    showBuildingListing();
                    int b = getScanner().nextInt();
                    out.println("Choose a transformer to connect this building to.");
                    showTransformerListing();
                    int t = getScanner().nextInt();
                    buildingShiftProcedure(b,t);
                case 4:
                    activeUser.logout();
                    System.out.println("User has been logged out successfully.");
                    return;
                default:
                    out.println("Incorrect input");
            }
        }
    }

    private void showTransformerListing() {
        for(Transformer t: repository.getTransformers()){
            out.print(t);
        }
    }

    private void buildingModifyViewController(int buildingChoice) {
        Building building = repository.getBuildings().get(buildingChoice);
        Scanner sc = getScanner();
        System.out.println("Which field would you like to modify?");
        System.out.print("1. Name\n2. Load\n3. Location\nEnter your choice: ");
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.print("Enter the new name: ");
                String b1 = sc.nextLine();
                System.out.println("The name of the building has been changed from "+ building.getName() + "to "+b1);
                building.setName(sc.nextLine());
                break;

            case 2: System.out.print("Enter new Load:");
                float tempLoad = sc.nextFloat();
                Transformer connectedTransformer = repository.getActiveConnection(building).getTransformer();
                if(connectedTransformer.getLoad() - building.getLoad() + tempLoad > 0.8 * connectedTransformer.getkVA())
                    System.out.println("Error! Transformer overloaded!");
                else
                {
                    System.out.println("The load of " + building.getName() + "has been changed from " + building.getLoad() + "to" + tempLoad);
                    connectedTransformer.setLoad(connectedTransformer.getLoad() - building.getLoad() + tempLoad);
                    building.setLoad(tempLoad);

                }
                break;
            case 3:
                System.out.print("Enter the new latitude: ");
                double l = sc.nextDouble();
                System.out.println("The latitude of " + building.getName() + "has been changed to " + l);
                building.setLatitude(sc.nextDouble());
                System.out.print("Enter the new longitude: ");
                l = sc.nextDouble();
                System.out.println("The longitude of " + building.getName() + "has been changed to " + l);
                building.setLongitude(sc.nextDouble());
                break;
            default:System.out.println("Error! new choice.");
        }
    }

    private void buildingShiftProcedure(int b, int t) {
        if(repository.getConnectionFor(repository.getBuildings().get(b),repository.getTransformers().get(t)).isStatus())
            out.println("Already Connected");
        else{
            if(repository.getTransformers().get(t).getkVA()*0.8 >repository.getTransformers().get(t).getLoad() + repository.getBuildings().get(b).getLoad()){
                out.println("Feasibility Test complete.");
                out.println("Shifting ["+ repository.getBuildings().get(b)+"] to ["+repository.getTransformers().get(t));
                repository.getActiveConnection(repository.getBuildings().get(b)).setStatus(false);
                repository.getConnectionFor(repository.getBuildings().get(b),repository.getTransformers().get(t)).setStatus(true);
                repository.getTransformers().get(t).setLoad(repository.getTransformers().get(t).getLoad()+repository.getBuildings().get(b).getLoad());
                out.println("Shifting complete");
                out.println(repository.getBuildings().get(b) + " is shifted to "+repository.getBuildings().get(t));
            }
        }
    }

    private void toggleATransformer(int i) {
        repository.getTransformers().get(i).toggle();
        out.println(repository.getTransformers().get(i) + " is "+ (repository.getTransformers().get(i).getStatus()?"on":"off"));
        if(repository.getTransformers().get(i).getStatus()){
            for(int b = 0;b < repository.getBuildings().size();b++){
                if(repository.getBuildings().get(b).getDefaultTransformer().equals(repository.getTransformers().get(i)))
                    buildingShiftProcedure(b,i);
            }
        }else {
            for (int t = 0; t < repository.getTransformers().size(); t++) {
                if(t != i) {
                    for (int b = 0; b < repository.getBuildings().size(); b++) {
                        if (repository.getBuildings().get(b).getDefaultTransformer().equals(repository.getTransformers().get(i)))
                            buildingShiftProcedure(b, i);
                    }
                }
            }
        }
    }

    private void showBuildingListing() {
        for(Building t : repository.getBuildings()){
            out.print(t);
        }
    }

}