package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Parsing {
    public Map<Address, Integer> parsingFromCSV(File file) {
        Map<Address, Integer> data = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            while (reader.ready()) {
                String[] str = reader.readLine().split(";");
                String city = str[0].replaceAll("\"", "");
                String street = str[1].replaceAll("\"", "");
                int house = Integer.parseInt(str[2]);
                int floor = Integer.parseInt(str[3]);
                Address address = new Address(city, street, house, floor);

                if (data.containsKey(address)) {
                    data.put(address, data.get(address) + 1);
                } else {
                    data.put(address, 1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public Map<Address, Integer> parsingFromXML(File file) {
        Map<Address, Integer> data = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                String city = "";
                String street = "";
                int house = 0;
                int floor = 0;

                String line = reader.readLine().replaceAll("[a-zA-Z=<>/]", "")
                        .trim().replaceAll("\" \"", ";").replaceAll("\"", "");

                String[] lineArr = line.split(";");
                if (lineArr.length == 4) {
                    city = lineArr[0];
                    street = lineArr[1];
                    house = Integer.parseInt(lineArr[2]);
                    floor = Integer.parseInt(lineArr[3]);
                }

                if (city.equals("")) continue;

                Address address = new Address(city, street, house, floor);

                if (data.containsKey(address)) {
                    data.put(address, data.get(address) + 1);
                } else {
                    data.put(address, 1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public void dataOutput(Map<Address, Integer> data) {

        if (data == null || data.isEmpty()) {
            System.out.println("File is empty!");
        } else {
            int firstFloor = 0;
            int secondFloor = 0;
            int thirdFloor = 0;
            int forthFloor = 0;
            int fifthFloor = 0;

            Map<Address, Integer> duplicates = new HashMap<>();

            for (Map.Entry<Address, Integer> entry : data.entrySet()) {
                if (entry.getValue() > 1) {
                    duplicates.put(entry.getKey(), entry.getValue());
                }

                switch (entry.getKey().getFloor()) {
                    case 1 -> firstFloor += entry.getValue();
                    case 2 -> secondFloor += entry.getValue();
                    case 3 -> thirdFloor += entry.getValue();
                    case 4 -> forthFloor += entry.getValue();
                    case 5 -> fifthFloor += entry.getValue();
                }
            }

            if (!duplicates.isEmpty()) {
                System.out.println("Duplicate address:");
                for (Map.Entry<Address, Integer> entry : duplicates.entrySet()) {
                    System.out.println(entry.getKey().toString() + " - " + entry.getValue() + " entries.");
                }
            }

            System.out.println("First floor = " + firstFloor +
                    "\nSecond floor = " + secondFloor +
                    "\nThird floor = " + thirdFloor +
                    "\nFourth floor = " + forthFloor +
                    "\nFifth floor = " + fifthFloor);
        }
    }
}
