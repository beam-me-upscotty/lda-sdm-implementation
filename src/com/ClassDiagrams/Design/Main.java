package com.ClassDiagrams.Design;

public class Main{

    public static void main(String[] args) {
        DataRepository dataRepository = new DataRepository();
        Controller controller = new Controller(dataRepository);
        View view = new View(controller);
        view.show();
    }
}