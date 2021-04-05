package com.company.forlearning;

import com.company.forlearning.lesson1.Lesson1;
import com.company.forlearning.Lesson2.Lesson2;

public class Main {

    static int n = 10; // For task 5

    public static void main(String[] args) {
        Lesson2 lesson2 = new Lesson2();
        int[] arrayTask1 = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        int[] arrayTask2 = new int[8];
        int[] arrayTask3 = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int[] arrayTask4 = new int[]{12, 22, 123, 8, 13, 24, 15, 32, 42, 99, 12, 10};
        int[][] arrayTask5 = new int[n][n];
        int[] arrayTask6 = new int[]{1,2,3,4,5};
        int[] arrayTask7 = new int[]{1,2,3,4,5};

        System.out.println("==================TASK 1==================");
        lesson2.task1(arrayTask1);
        printArray(arrayTask1);

        System.out.println();
        System.out.println("==================TASK 2==================");
        lesson2.task2(arrayTask2, 22);
        printArray(arrayTask2);

        System.out.println();
        System.out.println("==================TASK 3==================");
        lesson2.task3(arrayTask3);
        printArray(arrayTask3);

        System.out.println();
        System.out.println("==================TASK 4==================");
        printArray(arrayTask4);
        int max = lesson2.task4Max(arrayTask4);
        int min = lesson2.task4Min(arrayTask4, max);
        System.out.println();
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("==================TASK 5==================");
        lesson2.task5(arrayTask5, n);
        printArrayDouble(arrayTask5);
        System.out.println("==================TASK 6==================");
        lesson2.task6(arrayTask6, -14);
        System.out.println();
        lesson2.task6(arrayTask6, 13);
        System.out.println();
        System.out.println("==================TASK 7==================");
        lesson2.task7(arrayTask7, -14);
        printArray(arrayTask7);
        lesson2.task7(arrayTask7,13);
        System.out.println();
        printArray(arrayTask7);

        System.out.println();
        System.out.println("==================TASK BONUS==================");
        System.out.println(lesson2.bonus(3123988, "Kb"));
        System.out.println();
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void printArrayDouble(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
