package com.up.ia;

import java.util.HashMap;

public interface IMananger {
    HashMap<String, EnemyStats> enemys = new HashMap<>();

    HashMap<String, EnemyStats> getEnemys();
}
