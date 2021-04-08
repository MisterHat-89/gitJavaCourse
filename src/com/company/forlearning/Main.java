package com.company.forlearning;

import java.util.*;

import com.company.forlearning.Lesson3.Lesson3;
import com.company.forlearning.Lesson4.Lesson4;
import com.company.forlearning.lesson1.Lesson1;
import com.company.forlearning.Lesson2.Lesson2;

public class Main {

    private static Random rand = new Random();
    private static String[] namePersonal = {"Ivan", "Alexey", "Sergey", "Georg", "Vitaliy", "Valentin", "Maxim", "Oleg", "Andrey"};
    private static String[] patronymicPersonal = {"Ivanovicn", "Alexeevich", "Sergeevich", "Georgevich", "Vitalievich", "Valentinovich", "Maximovich", "Olegovich", "Andreevich"};
    private static String[] lastNamePersonal = {"Sobolev", "Tihonov", "Dorofeev", "Terent'ev", "Ovchinnikov", "Seleznyov", "Panfilov", "Kopylov", "Vladimirov"};
    private static String[] positionPersonal = {"Security guard", "Controller", "Director", "Manager", "Seller", "Driver", "Loader", "Logistician", "System Administrator"};
    private static int numberPersonal = 15;
    private static Lesson4[] arrayPersonal = new Lesson4[numberPersonal];


    public static void main(String[] args) {
        String fio;
        String position;
        Long numberPhone;
        double salary;
        int age;

        for (int i = 0; i < numberPersonal; i++) {
            fio = namePersonal[(int) Math.floor(Math.random() * namePersonal.length)] + " "
                    + patronymicPersonal[(int) Math.floor(Math.random() * patronymicPersonal.length)] + " "
                    + lastNamePersonal[(int) Math.floor(Math.random() * lastNamePersonal.length)];
            position = positionPersonal[(int) Math.floor(Math.random() * positionPersonal.length)];
            numberPhone = generateNumber();
            salary = 10000 + (int) (Math.random() * 100000);
            age = 18 + (int) (Math.random() * 50);
            if (age < 18) age = 18;
            Lesson4 personal = new Lesson4(fio, position, numberPhone, salary, age);
            arrayPersonal[i] = personal;
        }

        task4();

        printAllPersonal();
        printPersonalOlder(40);
        addSalaryPersonalOlder(35);

    }

    private static void task4() {
        System.out.println("=-=-=-=-=-=-=-=-==-=-=Getting FIO and position=-=-=-=-=-=-=-=-=");
        System.out.println(arrayPersonal[0].getFio());
        System.out.println(arrayPersonal[0].getPosition());
        System.out.println("=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=");
    }

    private static long generateNumber() {
        return Math.abs(rand.nextInt() * rand.nextInt()) % 1000000000L + 79000000000L;
    }

    private static void printAllPersonal() {
        System.out.println("=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=");
        System.out.println();
        System.out.println("Сотрудники компании: ");
        System.out.println();
        for (int i = 0; i < numberPersonal; i++) {
            System.out.println("ID: " + arrayPersonal[i].getId());
            System.out.println("FIO: " + arrayPersonal[i].getFio());
            System.out.println("Phone:" + arrayPersonal[i].getPhone());
            System.out.println("Age:" + arrayPersonal[i].getAge());
            System.out.println("Position:" + arrayPersonal[i].getPosition());
            System.out.println("Salary:" + arrayPersonal[i].getSalary());
            System.out.println();
        }
        System.out.println("=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=");
    }

    private static void printPersonalOlder(int older) {
        System.out.println("=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=");
        System.out.println();
        System.out.println("Сотрудники, которым больше " + older + " лет");
        System.out.println();
        for (int i = 0; i < numberPersonal; i++) {
            if (arrayPersonal[i].getAge() > older) {
                System.out.println("ID: " + arrayPersonal[i].getId());
                System.out.println("FIO: " + arrayPersonal[i].getFio());
                System.out.println("Phone:" + arrayPersonal[i].getPhone());
                System.out.println("Age:" + arrayPersonal[i].getAge());
                System.out.println("Position:" + arrayPersonal[i].getPosition());
                System.out.println("Salary:" + arrayPersonal[i].getSalary());
                System.out.println();
            }
        }
        System.out.println("=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=");
    }

    private static void addSalaryPersonalOlder(int older) {
        System.out.println("=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=");
        System.out.println();
        System.out.println("Сотрудники, которым больше " + older + " лет и им повысили зарплату на 10 000");
        System.out.println();
        for (int i = 0; i < numberPersonal; i++) {
            if (arrayPersonal[i].getAge() > older) {
                arrayPersonal[i].setSalary(arrayPersonal[i].getSalary() + 10000);
                System.out.println("ID: " + arrayPersonal[i].getId());
                System.out.println("FIO: " + arrayPersonal[i].getFio());
                System.out.println("Phone:" + arrayPersonal[i].getPhone());
                System.out.println("Age:" + arrayPersonal[i].getAge());
                System.out.println("Position:" + arrayPersonal[i].getPosition());
                System.out.println("Salary:" + arrayPersonal[i].getSalary());
                System.out.println();
            }
        }
        System.out.println("=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=");
    }

}
