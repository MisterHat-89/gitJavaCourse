package com.company.forlearning;

import java.util.*;

import com.company.forlearning.Lesson5.Animal.Animal;
import com.company.forlearning.Lesson5.Bird;
import com.company.forlearning.Lesson5.Cat;
import com.company.forlearning.Lesson5.Dog;
import com.company.forlearning.Lesson5.Horse;
import com.company.forlearning.Lesson6.DarkHero;
import com.company.forlearning.Lesson6.LightHero;


public class Main {

    private static int round = 1;

    public static void main(String[] args) throws InterruptedException {
        LightHero lightHero = new LightHero("LightHero", 15, 100, 15);
        DarkHero darkHero = new DarkHero("DarkHero", 10, 150, 5);

        while (!lightHero.isDead() || !darkHero.isDead()) {
            System.out.println("**** Round " + round + " ****");

            System.out.println(lightHero.getHeroInfo());
            System.out.println(darkHero.getHeroInfo());

            lightHero.attack(darkHero);

            if (darkHero.isDead()) {
                System.out.println("lightHero win");
                break;
            }

            darkHero.attack(lightHero);

            if (lightHero.isDead()) {
                System.out.println("darkHero win");
                break;
            }

            lightHero.healSelf();
            darkHero.healSelf();

            System.out.println(lightHero.getHeroInfo());
            System.out.println(darkHero.getHeroInfo());
            round++;

            Thread.sleep(1500);
        }
    }

}
