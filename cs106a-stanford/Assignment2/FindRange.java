
/*
 * File: FindRange.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindRange {
    private static final int SENTINEL = 0;

    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("This program finds the largest and smallest numbers.");

        List<Integer> values = getValuesFromUser();
        printExtrema(values);
    }

    private static List<Integer> getValuesFromUser() {
        List<Integer> values = new ArrayList<Integer>();
        scanner = new Scanner(System.in);

        while (true) {
            System.out.print("? ");
            int value = scanner.nextInt();

            if (value == SENTINEL) {
                break;
            }

            values.add(value);
        }

        return values;
    }

    private static void printExtrema(List<Integer> values) {
        if (values.size() == 0) {
            System.out.println("No values have been entered.");

        } else {
            int largestValue = 0;
            int smallestValue = 0;

            for (int i = 0; i < values.size(); i++) {
                if (i == 0) {
                    largestValue = values.get(i);
                    smallestValue = largestValue;

                } else {
                    if (values.get(i) < smallestValue) {
                        smallestValue = values.get(i);
                    }

                    if (values.get(i) > largestValue) {
                        largestValue = values.get(i);
                    }
                }
            }

            System.out.println(String.format("smallest: %d", smallestValue));
            System.out.println(String.format("largest: %d", largestValue));
        }
    }
}
