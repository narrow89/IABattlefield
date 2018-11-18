package main;

import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.actions.Action;
import ia.exceptions.RuleException;

public class Dummy extends Warrior {

    public Dummy(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
        super(name, health, defense, strength, speed, range);
    }

    @Override
    public Action playTurn(long tick, int actionNumber) {
        return null;
    }

    @Override
    public void wasAttacked(int damage, FieldCell source) {

    }

    @Override
    public void enemyKilled() {

    }

    @Override
    public void worldChanged(FieldCell oldCell, FieldCell newCell) {

    }
}
