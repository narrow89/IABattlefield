package com.up.ia;

import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

import java.util.Random;

public class Mananger extends WarriorManager {
    @Override
    public String getName() {
        return "MyMananger";
    }

    @Override
    public Warrior getNextWarrior() throws RuleException {
        return new Razziell("Razz"+(new Random().nextInt(100)), 20, 20, 20, 20, 20);
    }
}
