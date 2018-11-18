package main;

import astar.AStar;
import astar.Node;
import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.FieldCellType;

import java.util.ArrayList;
import java.util.List;

public class IaEngine implements Runnable {

    static IaEngine iaEngine;
    static Thread t = null;
    static AStar aStar = null;

    static int count = 0;
    static String text;

    private IaEngine() {
        if (t == null) {
            t = new Thread(this);
            t.setName("Engine");
            t.start();
        }
    }

    public static IaEngine getInstance() {
        if (iaEngine == null) {
            createEngine();
        }
        return iaEngine;

    }

    private static void createEngine() {
        iaEngine = new IaEngine();
        iaEngine.renderMap();
    }

    public void run() {

    }

    private static void renderMap() {
        //TODO Render map
        int rows = ConfigurationManager.getInstance().getMapHeight();
        int cols = ConfigurationManager.getInstance().getMapWidth();

        List<Node> blockedNodes = new ArrayList<>();
        List<FieldCell> cells = new ArrayList<>();

        //Add Blocked cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                FieldCell cell = BattleField.getInstance().getFieldCell(col, row);
                cells.add(cell);
                if (cell.getFieldCellType().equals(FieldCellType.BLOCKED)) {
                    blockedNodes.add(new Node(row, col));
                }
            }
        }

        Node initialNode = new Node(0, 0);
        Node finalNode = new Node(0, 0);

        aStar = new AStar(rows, cols, initialNode, finalNode, 1, 1.41f, cells);
        aStar.setBlocks(blockedNodes);
    }

    public static AStar getaStar() {
        if (aStar == null) {
            renderMap();
        }
        return aStar;
    }

    private void checkEnemyPosition() {
        //TODO Detect if enemy move, and try to calculate enemy speed
    }

}
