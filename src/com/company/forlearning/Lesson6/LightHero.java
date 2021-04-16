package com.company.forlearning.Lesson6;

import java.util.Random;


public class LightHero extends SuperHero {

    float lightSword = 5;

    public LightHero(String name, int attack, int health, int defence) {
        super(name, attack, health, defence);
    }

    @Override
    public float attackBonus() {
        int rand = randomDamageAttack(5);
        float dealDamage = lightSword + (lightSword * rand);
        System.out.println(name + " нанёс дополнительный урон световым мечом >> " + dealDamage);
        return dealDamage;
    }


}
