package com.company.forlearning.Lesson5;

import com.company.forlearning.Lesson5.Animal.Animal;

public class Cat extends Animal {

    public Cat() {
        super("Котик", 200f, 0f, 2f);
    }

    @Override
    public String swim(float value) {
        return name + " не умеет плавать!";
    }

}
