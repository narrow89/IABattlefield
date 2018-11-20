package com.up.ia;

import ia.battle.core.ConfigurationManager;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

import java.util.HashMap;
import java.util.Random;

public class Mananger extends WarriorManager implements IMananger {

    //    IaEngine iaEngine;
    //    HashMap<String, EnemyStats> enemys;

    //    public Mananger() {
    //        enemys = new HashMap<>();
    //    }

    @Override
    public String getName() {
        return "MyMananger";
    }

    @Override
    public Warrior getNextWarrior() throws RuleException {
        //        iaEngine = IaEngine.getInstance();
        int maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
        int q = Math.round(maxPoints / 20);
        int health, defense, strength, speed, range;

        health = q * 7;
        defense = q * 3;
        range = q * 3;

        int maxRange = ConfigurationManager.getInstance().getMaxRangeForWarrior();
        if (range > maxRange)
            range = maxRange;

        strength = q * 3;
        speed = q * 4;

        int sum = health + defense + strength + speed + range;
        strength += maxPoints - sum;

        //        return new Razziell("Razz" + (new Random().nextInt(100)), 20, 20, 20, 20, 20);
        return new Razziell("Razz", health, defense, strength, speed, range, this);
    }

    public HashMap<String, EnemyStats> getEnemys() {
        return enemys;
    }
}
