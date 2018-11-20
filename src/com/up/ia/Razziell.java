package com.up.ia;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorData;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;
import ia.battle.core.actions.Skip;
import ia.exceptions.RuleException;

import java.util.ArrayList;
import java.util.List;

public class Razziell extends Warrior {

    private IMananger mananger;
    boolean ignoreItems = false;
    boolean atacked = false;

    List<FieldCell> attackedFrom = new ArrayList<>();

    public Razziell(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
        super(name, health, defense, strength, speed, range);
    }

    public Razziell(String name, int health, int defense, int strength, int speed, int range, IMananger mananger)
            throws RuleException {
        super(name, health, defense, strength, speed, range);
        this.mananger = mananger;
    }

    @Override
    public Action playTurn(long tick, int actionNumber) {
        WarriorData enemey = BattleField.getInstance().getEnemyData();
        EnemyStats myEnemy = mananger.getEnemys().get(enemey.getName());
        if (myEnemy == null)
            myEnemy = new EnemyStats(enemey.getName(), enemey.getHealth(), enemey.getFieldCell());

        if (atacked) {
            atacked = false;
            for (FieldCell fieldCell : attackedFrom) {
                myEnemy.setRange(Math.round(BattleField.getInstance().calculateDistance(fieldCell, getPosition())));
            }
        }

        //        int x = BattleField.getInstance().getEnemyData().getFieldCell().getX();
        //        int y = BattleField.getInstance().getEnemyData().getFieldCell().getY();

        //TODO: tratar de conseguir stats enemigo
        //TODO: Chequear si estoy encerrado
        //TODO: Si estoy encerrado romper la pared
        //TODO: calcular cuanto tarda el enemigo en llegar hasta mi y cuanto yo hasta el

        if (actionNumber == 0
                && BattleField.getInstance().calculateDistance(getPosition(), enemey.getFieldCell()) > 4) {
            //            Movement moveToItem = null;
            Movement moveToItem = toSpecial();
            Movement moveToEnemy = getMove(enemey.getFieldCell());
            if (moveToItem != null) {
                if (ignoreItems || moveToItem.move().size() > moveToEnemy.move().size()) {
                    return moveToEnemy;
                } else {
                    return moveToItem;
                }
            } else {
                return moveToEnemy;
            }
        }
        if (enemey.getInRange()) {
            return new Attack(enemey.getFieldCell());
        }
        return new Skip();
    }

    public Movement toSpecial() {
        ArrayList<FieldCell> specialItems = BattleField.getInstance().getSpecialItems();
        int steps = 9999;
        FieldCell destiny = null;
        for (FieldCell fieldCell : specialItems) {
            Movement movement = getMove(fieldCell);
            if (movement.move().size() < steps) {
                steps = movement.move().size();
                destiny = fieldCell;
            }
        }
        if (steps == 1) {
            ignoreItems = true;
        }
        return (destiny != null) ? getMove(destiny) : null;
    }

    public Movement getMove(FieldCell destiny) {
        return new Movement(destiny, getPosition(), this);
    }

    public Movement getMove(FieldCell from, FieldCell destiny) {
        return new Movement(destiny, from, this);
    }

    @Override
    public void wasAttacked(int damage, FieldCell source) {
        WarriorData hunter = BattleField.getInstance().getHunterData();
        if (source.getX() != hunter.getFieldCell().getX() && source.getY() != hunter.getFieldCell().getY()) {

            this.attackedFrom.add(source);
            atacked = true;
        }
        //TODO Calculate Stregnh
    }

    @Override
    public void enemyKilled() {
        ignoreItems = false;
    }

    @Override
    public void worldChanged(FieldCell oldCell, FieldCell newCell) {

    }

    public boolean useSpecialItem() {
        return true;
    }

}
