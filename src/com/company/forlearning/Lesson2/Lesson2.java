package com.company.forlearning.Lesson2;

import java.util.Map;

public class Lesson2 {
    /*
    1 Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
     */
    public static void task1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }
    }

    /*
    2 Задать пустой целочисленный массив размером 8. Написать метод, который
    c помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;*/
    public static void task2(int[] array, int n) {
        int j = 0;
        for (int i = 1; i <= n; i += 3) {
            array[j] = i;
            j++;
        }
    }

    /*
    3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ],
    написать метод,принимающий на вход массив и умножающий числа меньше 6 на 2;*/
    public static void task3(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] < 6) ? array[i] * 2 : array[i];
        }
    }

    /*
    4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;*/
    public static int task4Max(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            max = (array[i] > max) ? array[i] : max;
        }
        return max;
    }

    public static int task4Min(int[] array, int max) {
        int min = max;
        for (int i = 0; i < array.length; i++) {
            min = (array[i] < min) ? array[i] : min;
        }
        return min;
    }

    /*
    5* Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
    заполнить его диагональные элементы единицами, используя цикл(ы);*/
    public static void task5(int[][] array, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || (i + j == n - 1)) array[i][j] = 1;
            }
        }
    }

    /*
    6** Написать метод, которому на вход подаётся одномерный массив и число n (может быть положительным,
    или отрицательным), при этом метод должен циклически сместить все элементы массива на n позиций.
            [1,2,3,4,5], -2 => [3,4,5,1,2]
            [1,2,3,4,5], 2 => [4,5,1,2,3]
    */
    public static void task6(int[] array, int offSet) {
        int[] arrayBuf = new int[array.length];
        if (Math.abs(offSet) > array.length) offSet %= array.length;
        int ind = 0;
        for (int i = 0; i < array.length; i++) {
            if (offSet >= 0)
                ind = (i + offSet > array.length - 1) ? (i + offSet) - array.length : i + offSet;
            if (offSet < 0)
                ind = (i + offSet < 0) ? array.length + i + offSet : i + offSet;
            arrayBuf[ind] = array[i];
        }

        printArray(arrayBuf);

    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /*   7 *** Не пользоваться вспомогательным массивом при решении задачи 6. */
    public static void task7(int[] array, int offSet) {
        int n, ind, i, buf;
        n = ind = i = buf = 0;
        int bufInt = array[0];
        if (Math.abs(offSet) > array.length) offSet %= array.length;

        while (n < array.length) {

            if (offSet >= 0)
                ind = (i + offSet > array.length - 1) ? (i + offSet) - array.length : i + offSet;
            if (offSet < 0)
                ind = (i + offSet < 0) ? array.length - 1 + i + offSet : i + offSet;

            buf = array[ind];
            array[ind] = bufInt;
            bufInt = buf;
            i = ind;
            n++;
        }
    }

    /*
    8  Оператор Switch. На вход подаётся количество бит(Целое число) и измеряемая единица.
    Необходимо с помощью оператора switch определить - сколько измеряемоего значения в данном битовом числе.
    */
    public static String bonus(int number, String str) {
        float result = (float) number / 8;
        switch (str) {
            case "Tb":
                result /= 1024;
            case "Gb":
                result /= 1024;
            case "Mb":
                result /= 1024;
            case "Kb":
                result /= 1024;
                break;
            default:
                return "Введите правильные данные";
        }
        return "В " + number + " битах будет " + result + str;
    }


}
