package com.up.ia;

import ia.battle.core.ConfigurationManager;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

import java.util.HashMap;
import java.util.Random;

public class Mananger2 extends WarriorManager implements IMananger {

    @Override
    public String getName() {
        return "MyMananger2";
    }

    @Override
    public Warrior getNextWarrior() throws RuleException {
        int maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
        int q = maxPoints / 20;
        int health, defense, strength, speed, range;

        health = q * 5;
        defense = q * 5;
        range = q * 5;
        strength = q * 2;
        speed = q * 3;

        //        return new Razziell("Razz" + (new Random().nextInt(100)), 20, 20, 20, 20, 20);
        return new Razziell("Dun", health, defense, strength, speed, range, this);
        //        return new Dummy("Dummy", 20, 20, 20, 20, 20);
    }

    @Override
    public HashMap<String, EnemyStats> getEnemys() {
        return enemys;
    }
}
