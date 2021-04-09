package com.company.forlearning.Lesson5;

import com.company.forlearning.Lesson5.Animal.Animal;

public class Bird extends Animal {

    public Bird() {
        super("Птичка", 5f, 0f, 0.2f);
    }

    @Override
    public String swim(float value) {
        return name + " не умеет плавать!";
    }

}
