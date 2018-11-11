package com.up.ia;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.WarriorData;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;
import ia.battle.core.actions.Skip;
import ia.exceptions.RuleException;
import ia.battle.core.Warrior;

public class Razziell extends Warrior {


    public Razziell(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
        super(name, health, defense, strength, speed, range);
    }

    @Override
    public Action playTurn(long tick, int actionNumber) {

//        int x = BattleField.getInstance().getEnemyData().getFieldCell().getX();
//        int y = BattleField.getInstance().getEnemyData().getFieldCell().getY();
        //TODO: check item cerca
        //TODO: Buscar item
        //TODO: tratar de conseguir stats enemigo
        //TODO: Chequear si estoy encerrado
        //TODO: Si estoy encerrado romper la pared
        //TODO: calcular cuanto tarda el enemigo en llegar hasta mi y cuanto yo hasta el

        WarriorData enemey = BattleField.getInstance().getEnemyData();
        if(enemey.getInRange()){
//            return new Attack(enemey.getFieldCell());
        }
        if(actionNumber == 0) {
            Movement movement = new Movement(enemey.getFieldCell(), getPosition(), this);
            return movement;
        }
        return new Skip();
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
