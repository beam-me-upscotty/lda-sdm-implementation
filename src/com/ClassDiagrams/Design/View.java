package com.ClassDiagrams.Design;

import static com.ClassDiagrams.Design.InputClient.getScanner;
import static java.lang.System.out;

public class View {
    private Controller controller;
    public View(Controller controller){
        this.controller = controller;
    }
    public void show(){
        mainView();
    }
    private void mainView(){
        while(true){
            out.println("Welcome to load Distribution Application");
            out.println("Please choose type of user checkCredentials");
            out.println("1. Administrator");
            out.println("2. Technician");
            out.println("3. Exit");
            switch (getScanner().nextInt()){
                case 1:
                    administratorLoginView();
                case 2:
                    technicianloginView();
                    break;
                case 3: out.println("Thank you for using LDA. See you again");
                    return;
                default: out.println("ERROR: Incorrect input");
            }
        }
    }
    private void administratorLoginView(){
        while(true) {
            out.println("Enter user name");
            String username = getScanner().next();
            out.println("Enter password");
            String password = getScanner().next();
            out.println("Enter admin password");
            String adminPassword = getScanner().next();
            if(controller.checkAdminUser(username,password,adminPassword) == null){
                out.println("Can't find User, want to try again?(1/0)");
                if(getScanner().nextInt()!=1)
                    return;
            }else{
                controller.adminViewController();
                return;
            }
        }
    }

    private void technicianloginView(){
        while(true) {
            out.println("Enter user name");
            String username = getScanner().next();
            out.println("Enter password");
            String password = getScanner().next();
            if(controller.checkTechnicianUser(username,password)== null){
                out.println("Can't find User, want to try again?(1/0)");
                if(getScanner().nextInt()!=1)
                    return;
            }else{
                controller.technicianViewController();
                return;
            }
        }
    }


}
