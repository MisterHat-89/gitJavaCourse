package com.company.forlearning.Lesson6;

import java.util.Random;

public abstract class SuperHero {

    protected String name;
    protected int attack;
    protected float health;
    protected int defence;

    public SuperHero(String name, int attack, float health, int defence) {
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.defence = defence;
    }

    public void attack(SuperHero hero) {
        float dealDamage = this.attack - ((float) this.attack / hero.defence) + randomDamageAttack(2);
        if (!hero.isDead()) {
            if (isAttackBonus()) {
                hero.health -= dealDamage + attackBonus();
            } else {
                hero.health -= dealDamage;
            }
            if (hero.health < 0) hero.health = 0;
            System.out.println(this.name + " deal damage " + hero.name + " >> " + dealDamage
                    + " dmg. HP " + hero.name + " = " + hero.health);
        } else {
            System.out.println(hero.name + " is dead " + hero.isDead());
        }
    }

    public void healSelf() {
        float summaryHeal = 0.1f * this.defence;
        this.health += summaryHeal;
        System.out.println(this.name + " heal self " + summaryHeal + ". Now HP = " + this.health);
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public String getHeroInfo() {
        return name + " HP: " + health;
    }

    public boolean isAttackBonus(){
        int rand = randomDamageAttack(101);
        return (rand >= 95);
    }

    public int randomDamageAttack(int n){
        Random random = new Random();
        return random.nextInt(n);
    }

    public abstract float attackBonus();

}
