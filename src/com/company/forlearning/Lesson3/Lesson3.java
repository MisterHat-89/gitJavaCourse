package com.company.forlearning.Lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Полностью разобраться с кодом, попробовать переписать с нуля;
 * ^ Усовершенствовать метод проверки победы (через циклы);
 * ^ Расширить поле до 5х5 и в качестве условий победы установить серию равной 4.
 * ^^ Проработать базовый искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */


public class Lesson3 {

    public static char[][] field;
    public static int fieldSizeX;
    public static int fieldSizeY;
    public static int series; // number of series for win

    public static char human = 'X';
    public static char ai = 'O';
    public static char empty = '_';

    public static String lineHuman = "";
    public static String lineAi = "";

    public static boolean turnOnCleverAI = false;
    public static int[] rememberStepAI = new int[2];

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random(); //[0;1)

    public static void createField() {
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = empty;
            }
        }
    }

    public static void viewField() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    public static void turnHuman() {
        int cellX;
        int cellY;

        do {
            System.out.println("Enter coordinates cell Y: ");
            cellY = scanner.nextInt() - 1;
            System.out.println("Enter coordinates cell X: ");
            cellX = scanner.nextInt() - 1;
        } while (!validCell(cellY, cellX) || !emptyCell(cellY, cellX));
        field[cellY][cellX] = human;
    }

    public static void turnAI() {
        int cellX = random.nextInt(fieldSizeX);
        int cellY = random.nextInt(fieldSizeY);

        if (turnOnCleverAI & emptyCell(rememberStepAI[0], rememberStepAI[1])) {
            field[rememberStepAI[0]][rememberStepAI[1]] = ai;
            turnOnCleverAI = false;
            return;
        } else {
            turnOnCleverAI = false;
            while (!emptyCell(cellY, cellX)) {
                cellX = random.nextInt(fieldSizeX); //[0;3)
                cellY = random.nextInt(fieldSizeY);
            }
            field[cellY][cellX] = ai;
        }
    }

    public static boolean winGame(char player, String seriesPlayer) {
        String str = "";
        String bufSeries = seriesPlayer.substring(0, series - 1);
        String bufStr = "";
        String strLeft = ""; //Диагональ слево-направо
        String strRight = ""; //Диагональ справо-налево
        /* Проверка горизонтали и вертикали */

        System.out.println(" bufSeries " + bufSeries);
        for (int i = 0; i < fieldSizeY; i++) {
            if (Arrays.toString(field[i]).replaceAll("[^A-Z_]", "").equals(seriesPlayer)) return true;
            bufStr = Arrays.toString(field[i]).replaceAll("[^A-Z_]", "");

            for (int j = 0; j < fieldSizeX; j++) {
                str += field[j][i];
                if (validDiag(str, seriesPlayer)) return true;
            }
            if (validDiag(str, bufSeries) & !turnOnCleverAI & bufStr.contains("_")) {
                rememberStepAI[0] = str.indexOf("_");
                rememberStepAI[1] = i;
                turnOnCleverAI = true;
            } else if (validDiag(bufStr, bufSeries) & !turnOnCleverAI & bufStr.contains("_")) {
                rememberStepAI[0] = i;
                rememberStepAI[1] = bufStr.indexOf("_");
                turnOnCleverAI = true;
            }
            str = "";
        }

        /* Проверка диагонали по строкам */
        int numberOfDiagonal = fieldSizeY - (series - 1);

        for (int j = numberOfDiagonal - 1; j > 0; j--) {
            for (int i = 0; i < fieldSizeY; i++) {
                if (i == fieldSizeY - 1) break;
                strLeft += field[j + i][i];
                strRight += field[fieldSizeY - 1 - i][i];
                if (validDiag(strLeft, seriesPlayer) || validDiag(strRight, seriesPlayer)) return true;
            }
            if (validDiag(strLeft, bufSeries) & !turnOnCleverAI & strLeft.contains("_")) {
                rememberStepAI[0] = j + strLeft.indexOf("_");
                rememberStepAI[1] = strLeft.indexOf("_");
                turnOnCleverAI = true;
            } else if (validDiag(strRight, bufSeries) & !turnOnCleverAI & strRight.contains("_")) {
                rememberStepAI[0] = fieldSizeY - 1 - str.indexOf("_");
                rememberStepAI[1] = strLeft.indexOf("_");
                turnOnCleverAI = true;
            }

            strLeft = "";
            strRight = "";
        }

        /* Проверка диагонали по столбцам */
        numberOfDiagonal = fieldSizeX - (series - 1);

        for (int j = 1; j <= numberOfDiagonal - 1; j++) {
            for (int i = 0; i < fieldSizeX; i++) {
                if (i == fieldSizeX - 1) break;
                strLeft += field[i][j + i];
                strRight += field[i][fieldSizeX - 1 - i];
                if (validDiag(strLeft, seriesPlayer) || validDiag(strRight, seriesPlayer)) return true;
            }

            if (validDiag(strLeft, bufSeries) & !turnOnCleverAI & strLeft.contains("_")) {
                rememberStepAI[0] = strLeft.indexOf("_");
                rememberStepAI[1] = j + strLeft.indexOf("_");
                turnOnCleverAI = true;
            } else if (validDiag(strRight, bufSeries) & !turnOnCleverAI & strRight.contains("_")) {
                rememberStepAI[0] = strLeft.indexOf("_");
                rememberStepAI[1] = fieldSizeY - 1 - str.indexOf("_");
                turnOnCleverAI = true;
            }
            strLeft = "";
            strRight = "";
        }

        strLeft = "";
        strRight = "";

        /* Проверка диагоналей */
        for (int i = 0; i < field.length; i++) {
            strLeft += field[i][i];
            strRight += field[i][field.length - 1 - i];
        }

        if (validDiag(strLeft, bufSeries) & !turnOnCleverAI & strLeft.contains("_")) {
            rememberStepAI[0] = strLeft.indexOf("_");
            rememberStepAI[1] = strLeft.indexOf("_");
            turnOnCleverAI = true;
        } else if (validDiag(strRight, bufSeries) & !turnOnCleverAI & strRight.contains("_")) {
            rememberStepAI[0] = strLeft.indexOf("_");
            rememberStepAI[1] = fieldSizeY - 1 - strRight.indexOf("_");
            turnOnCleverAI = true;
        }

        if (validDiag(strLeft, seriesPlayer) || validDiag(strRight, seriesPlayer)) return true;

        return false;
    }

    public static boolean draw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == empty) return false;
            }
        }
        return true;
    }

    public static boolean validDiag(String str, String strSeries) {
        return (str.contains(strSeries));
    }

    public static boolean validCell(int cellY, int cellX) {
        return cellX >= 0 && cellX < fieldSizeX && cellY >= 0 && cellY < fieldSizeY;
    }

    public static boolean emptyCell(int cellY, int cellX) {
        return field[cellY][cellX] == empty;
    }

    public static void choiceConfig() {
        System.out.println("Select difficulty level: ");
        System.out.println("1. 3х3 - series of 3");
        System.out.println("2. NxN - series of 4");
        System.out.println(">>> ");
        int levelGame = scanner.nextInt();
        int sizeOfUser;

        if (levelGame == 1) {
            fieldSizeX = 3;
            fieldSizeY = 3;
            series = 3;
        } else {
            System.out.println("Select size of field: ");
            sizeOfUser = scanner.nextInt();
            fieldSizeX = sizeOfUser;
            fieldSizeY = sizeOfUser;
            series = 4;
        }

        lineHuman = Character.toString(human).repeat(series);
        lineAi = Character.toString(ai).repeat(series);
    }

    public static void newGame() {
        char answer;
        do {
            System.out.println("=====================================");
            System.out.println("Want to play again? y/n ");
            answer = scanner.next().charAt(0);
            if (answer == 'y') game();
            else if (answer == 'n') break;

        }
        while (true);
    }

    public static void game() {

        choiceConfig();

        createField();
        viewField();

        while (true) {

            turnHuman();
            viewField();

            if (winGame(human, lineHuman)) {
                System.out.println("*****YOU WIN!*****");
                newGame();
                break;
            }

            if (draw()) {
                System.out.println("*****DRAW!*****");
                newGame();
                break;
            }

            turnAI();
            viewField();

            if (winGame(ai, lineAi)) {
                System.out.println("*****AI WIN!*****");
                newGame();
                break;
            }

            if (draw()) {
                System.out.println("*****DRAW!*****");
                newGame();
                break;
            }
        }
    }
}

