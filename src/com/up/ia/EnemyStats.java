package com.up.ia;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;

public class EnemyStats {

    private String name;
    private int health, defense, strength, speed, range;
    private int maxHealth, maxDefense, maxStrength, maxSpeed, maxRange;
    private FieldCell lastposition;
    private FieldCell currentposition;

    public EnemyStats() {

    }

    public EnemyStats(String name, int health, FieldCell currentposition) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.currentposition = currentposition;
        this.lastposition = currentposition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (maxSpeed < speed)
            maxSpeed = speed;
        this.speed = speed;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        if (range <= ConfigurationManager.getInstance().getMaxRangeForWarrior()) {
            this.range = range;

            if (maxRange <= range) {
                maxRange = range;
            }
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }

    public int getMaxStrength() {
        return maxStrength;
    }

    public void setMaxStrength(int maxStrength) {
        this.maxStrength = maxStrength;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public FieldCell getLastposition() {
        return lastposition;
    }

    public void setLastposition(FieldCell lastposition) {
        this.lastposition = lastposition;
    }

    public FieldCell getCurrentposition() {
        return currentposition;
    }

    public void setCurrentposition(FieldCell currentposition) {
        this.currentposition = currentposition;
        comparePosition();
    }

    public void comparePosition() {
        if (currentposition.getX() != lastposition.getX() || currentposition.getY() != lastposition.getY()) {
            Movement move = new Movement(currentposition, lastposition);
            setSpeed(move.move().size() * 5);
        }
        //            lastposition = currentposition;
    }

    public void calculateRange(FieldCell myPosition) {
        int centerX = currentposition.getX();
        int centerY = currentposition.getY();

        //        int range = ??;

        int x = myPosition.getX();
        int y = myPosition.getY();

        setRange((int) Math.round(Math.sqrt((Math.pow(centerX - x, 2)) + (Math.pow(centerY - y, 2)))));

    }

    public void calculateRange(FieldCell to, FieldCell from) {
        int centerX = from.getX();
        int centerY = from.getY();

        //        int range = ??;

        int x = to.getX();
        int y = to.getY();
        double distance = BattleField.getInstance().calculateDistance(BattleField.getInstance().getFieldCell(x, y),
                BattleField.getInstance().getFieldCell(centerX, centerY));

        setRange((int) Math.round(Math.sqrt((Math.pow(centerX - x, 2)) + (Math.pow(centerY - y, 2)))));

    }

}
