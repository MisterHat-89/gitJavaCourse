package com.company.forlearning;

import java.util.*;

import com.company.forlearning.Lesson5.Animal.Animal;
import com.company.forlearning.Lesson5.Bird;
import com.company.forlearning.Lesson5.Cat;
import com.company.forlearning.Lesson5.Dog;
import com.company.forlearning.Lesson5.Horse;

public class Main {

    public static float run;
    public static float swim;
    public static float jump;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bird bird = new Bird();
        Cat cat = new Cat();
        Dog dog = new Dog();
        Horse horse = new Horse();

        Animal[] animals = {
                bird,
                cat,
                dog,
                horse
        };

        System.out.println("Введите дистанцию для бега >> ");
        run = scanner.nextFloat();
        System.out.println("Введите дистанцию для плавания >> ");
        swim = scanner.nextFloat();
        System.out.println("Введите высоту препятствия для прыжка >> ");
        jump = scanner.nextFloat();

        for (Animal animal : animals) {
            System.out.println(animal.run(run));
            System.out.println(animal.swim(swim));
            System.out.println(animal.jump(jump));
        }
    }

}
