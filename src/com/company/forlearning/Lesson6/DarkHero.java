package com.company.forlearning.Lesson6;

import java.util.Random;

public class DarkHero extends SuperHero {

    float snakes = 3;

    public DarkHero(String name, int attack, int health, int defence) {
        super(name, attack, health, defence);
    }

    @Override
    public float attackBonus() {
        int rand = randomDamageAttack(5);
        float dealDamage = snakes + (snakes * rand);
        System.out.println(name + " нанёс дополнительный урон змеями >> " + dealDamage);
        return dealDamage;
    }

}
