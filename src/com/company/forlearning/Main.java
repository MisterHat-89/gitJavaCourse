package com.company.forlearning;

import com.company.forlearning.lesson1.Lesson1;

public class Main {

    public static void main(String[] args) {
        Lesson1 lesson1 = new Lesson1();
        float resultTask1 = lesson1.task1(1, 1, 1, 1);
        boolean resultTask2 = lesson1.task2(15, 1);
        String resultTask3 = lesson1.task3(-7);
        String resultTask4 = lesson1.task4("Alexey");
        String resultTask5 = lesson1.task5(1984);

        System.out.println("Result task 1 = " + resultTask1);
        System.out.println("Result task 2 = " + resultTask2);
        System.out.println("Result task 3 = " + resultTask3);
        System.out.println("Result task 4 = " + resultTask4);
        System.out.println("Result task 5 = " + resultTask5);
    }

}
