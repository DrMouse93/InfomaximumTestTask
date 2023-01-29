package org.example;

import java.io.*;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);


    public void startMenu() {
        System.out.println("Enter the file path or 'exit'");

        String enter = scanner.nextLine();
        while (!enter.equals("exit")) {
            File file = new File(enter);
            if (!file.canRead()) {
                System.out.println("The file cannot be opened. Enter another the file path or 'exit'");
                enter = scanner.nextLine();
                continue;
            }

            if (file.getAbsolutePath().endsWith(".csv")) {
                Parsing parsing = new Parsing();
                parsing.dataOutput(parsing.parsingFromCSV(file));
            } else if (file.getAbsolutePath().endsWith(".xml")) {
                Parsing parsing = new Parsing();
                parsing.dataOutput(parsing.parsingFromXML(file));
            } else {
                System.out.println("Incorrect the file path. Try again!");
                enter = scanner.nextLine();
                continue;
            }
            System.out.println("Enter the file path or 'exit'");
            enter = scanner.nextLine();
        }
    }
}
