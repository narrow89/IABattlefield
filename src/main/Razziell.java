package main;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorData;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;
import ia.battle.core.actions.Move;
import ia.battle.core.actions.Skip;
import ia.exceptions.RuleException;

import java.util.ArrayList;

public class Razziell extends Warrior {

    boolean ignoreItems = false;

    public Razziell(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
        super(name, health, defense, strength, speed, range);
    }

    @Override
    public Action playTurn(long tick, int actionNumber) {

        //        int x = BattleField.getInstance().getEnemyData().getFieldCell().getX();
        //        int y = BattleField.getInstance().getEnemyData().getFieldCell().getY();

        //TODO: tratar de conseguir stats enemigo
        //TODO: Chequear si estoy encerrado
        //TODO: Si estoy encerrado romper la pared
        //TODO: calcular cuanto tarda el enemigo en llegar hasta mi y cuanto yo hasta el

        WarriorData enemey = BattleField.getInstance().getEnemyData();

        if (enemey.getInRange()) {
            return new Attack(enemey.getFieldCell());
        }
        if (actionNumber == 0) {
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
