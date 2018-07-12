package com.company;
import java.util.Scanner;
import java.util.Arrays;

/*
@author Trey Manuszak 7/11/18

A Ducci sequence is a sequence of n-tuples of integers, sometimes known as "the Diffy game",
because it is based on sequences. Given an n-tuple of integers (a_1, a_2, ... a_n)
the next n-tuple in the sequence is formed by taking the absolute differences of neighboring integers.
Ducci sequences are named after Enrico Ducci (1864-1940), the Italian mathematician credited with their discovery.

Some Ducci sequences descend to all zeroes or a repeating sequence.
An example is (1,2,1,2,1,0) -> (1,1,1,1,1,1) -> (0,0,0,0,0,0).
*/

public class Main {

    public static int[][] addDucciArray (int length, int size, int[][] oldArray) {
        int[][] newArray = new int[length][size];
        int newValue = 0;

        /*Makes a new 2d array with the last sequence being all 0's*/
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < size; j++) {
                newArray[i][j] = oldArray[i][j];
            }
        }

        /*Fills in the new sequence*/
        for (int i = 0; i < size; i++) {

            if (i < (size - 1)) {
                newValue = java.lang.Math.abs(oldArray[length - 2][i] - oldArray[length - 2][i + 1]);
                newArray[length - 1][i] = newValue;
            }

            if (i == (size - 1)) {
                newValue = java.lang.Math.abs(oldArray[length - 2][i] - oldArray[length - 2][0]);
                newArray[length - 1][i] = newValue;
            }
        }

        return newArray;
    }

    public static void ducciSequence () {
        Scanner stdin = new Scanner(System.in);
        boolean loop = false; //If the sequence loops
        boolean converge = false; //If the sequence converges to all 0's
        int value;
        int iterations = 1;

        System.out.println("Enter number of elements: ");
        int elements = stdin.nextInt();
        int length = iterations;
        int[][] ducciArray = new int[length][elements]; //[0][elements]

        for (int i = 0; i < elements; i++) {
            System.out.println("Enter integer for element " + (i + 1) + ": ");
            ducciArray[iterations - 1][i] = stdin.nextInt();
        }



        while (!loop && !converge) {
            iterations++;
            length++;
            ducciArray = addDucciArray(length, elements, ducciArray);
            loop = testLoop(length, elements, ducciArray);
            if (!loop) {
                converge = testConverge(length, elements, ducciArray);
            }
        }

        printArray(ducciArray);

        if (loop) {
            System.out.println("The sequence loops and takes " + iterations + " iterations.");
        } else {
            System.out.println("The sequence converged after " + iterations + " iterations.");
        }

    }

    public static void printArray (int[][] ducciArray) {
        System.out.println(Arrays.deepToString(ducciArray));
    }

    public static boolean testLoop (int length, int size, int[][] ducciArray) {

        int ticker = 0;

        for (int i = 0; i < length - 1; i++) {

           for (int j = 0; j < size; j++) {

               if (ducciArray[i][j] == ducciArray[length - 1][j]) {
                   ticker++;
               } else {
                   ticker = 0;
               }
           }

           if (ticker == size) {
               return true;
           }

        }
        return false;
    }

    public static boolean testConverge (int length, int size, int[][] ducciArray) {

        for (int i = 0; i < size; i++) {

            if (ducciArray[length - 1][i] != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ducciSequence();
    }
}
