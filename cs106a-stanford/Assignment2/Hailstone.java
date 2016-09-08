
/*
 * File: Hailstone.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import java.util.Scanner;

public class Hailstone {
    private static Scanner scanner;

    public static void main(String[] args) {
        int value = readNumberFromUser();
        doHailstoneSequence(value);
    }

    private static int readNumberFromUser() {
        System.out.print("Enter a number: ");
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void doHailstoneSequence(int value) {
        int count = 0;
        while (value != 1) {
            if (value % 2 == 0) {
                // Value is even
                System.out.print(String.format("%d is even so I take half: ", value));
                value = value / 2;
                System.out.println(value);

            } else {
                // Value is odd
                System.out.print(String.format("%d is odd, so I make 3n + 1: ", value));
                value = 3 * value + 1;
                System.out.println(value);
            }
            count++;
        }

        System.out.println(String.format("The process took %d to reach 1", count));
    }
}
