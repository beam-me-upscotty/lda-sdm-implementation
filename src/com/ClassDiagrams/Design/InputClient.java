package com.ClassDiagrams.Design;

import java.util.Scanner;

public class InputClient {
    private static Scanner scanner;
    public static Scanner getScanner(){
        if(scanner == null);
            scanner = new Scanner(System.in);
        return scanner;
    }
}
