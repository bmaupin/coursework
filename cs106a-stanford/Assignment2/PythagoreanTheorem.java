
/*
 * File: PythagoreanTheorem.java
 * Name:
 * Section Leader:
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import java.util.Scanner;

public class PythagoreanTheorem {
    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("Enter values to compute Pythagorean theoroem.");

        scanner = new Scanner(System.in);

        System.out.print("a: ");
        int a = scanner.nextInt();

        System.out.print("b: ");
        int b = scanner.nextInt();

        double c = Math.sqrt(a * a + b * b);

        System.out.println(String.format("c = %f", c));
    }
}
