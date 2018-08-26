package com.up.ia;

import com.up.ia.astar.AStar;
import com.up.ia.astar.Node;
import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.FieldCellType;
import ia.battle.test.TestFieldCell;
import ia.exceptions.BattleException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        BattleField.getInstance().initCells();

        int rows = ConfigurationManager.getInstance().getMapHeight();
        int cols = ConfigurationManager.getInstance().getMapWidth();

        List<Node> blockedNodes = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                FieldCell cell = BattleField.getInstance().getFieldCell(col, row);
//                System.out.println(cell.toString());
                if (cell.getFieldCellType().equals(FieldCellType.NORMAL)) {
                    blockedNodes.add(new Node(row, col));
                }
            }
        }


        Node initialNode = new Node(0, 0);
        Node finalNode = new Node(rows - 1, cols - 1);

        AStar aStar = new AStar(rows, cols, initialNode, finalNode);

        aStar.setBlocks(blockedNodes);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            System.out.println(node);
        }


    }
}
