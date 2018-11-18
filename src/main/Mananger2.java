package main;

import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

public class Mananger2 extends WarriorManager {
    @Override
    public String getName() {
        return "MyMananger";
    }

    @Override
    public Warrior getNextWarrior() throws RuleException {
        return new Dummy("Dummy",20,20,20,20,20);
    }
}
