package com.company.forlearning.Lesson5.Animal;

/* 
        Домашнее задание Java1. Level1. Step5
        1. Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.
        2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому
        методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
        3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м.,
        Птица 5 м.,; прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
        4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат.
        (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
        5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может
        быть 400 м., у другой 600 м.
*/

public class Animal {

    protected String name;
    protected float limitationRun;
    protected float limitationSwim;
    protected float limitationJump;

    public Animal(String name, float limitationRun, float limitationSwim, float limitationJump) {
        this.name = name;
        this.limitationRun = limitationRun;
        this.limitationSwim = limitationSwim;
        this.limitationJump = limitationJump;
    }

    public String run(float value) {
        return (value <= limitationRun) ? name + " пробежал! " : name + " не пробежал! ";
    }

    public String swim(float value) {
        return (value <= limitationSwim) ? name + " проплыл! " : name + " не проплыл! ";
    }

    public String jump(float value) {
        return (value <= limitationJump) ? name + " перепрыгнул! " : name + " не перепрыгнул! ";
    }

}
