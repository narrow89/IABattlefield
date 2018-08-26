package com.up.ia;

import com.up.ia.astar.AStar;
import com.up.ia.astar.Node;
import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.FieldCellType;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        BattleField.getInstance().initCells();

        int rows = ConfigurationManager.getInstance().getMapHeight();
        int cols = ConfigurationManager.getInstance().getMapWidth();

        List<Node> blockedNodes = new ArrayList<>();
        List<FieldCell> cells = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                FieldCell cell = BattleField.getInstance().getFieldCell(col, row);
                cells.add(cell);
//                System.out.println(cell.toString());
                if (cell.getFieldCellType().equals(FieldCellType.BLOCKED)) {
                    blockedNodes.add(new Node(row, col));
                }
            }
        }

        System.out.println(BattleField.getInstance().getFieldCell(0, 0).getFieldCellType());
        System.out.println(BattleField.getInstance().getFieldCell(0, 2).getFieldCellType());
        Node initialNode = new Node(0, 0);
        Node finalNode = new Node(rows-1, cols-1);

        AStar aStar = new AStar(rows, cols, initialNode, finalNode,1, 1.41f,cells);
        aStar.setBlocks(blockedNodes);

        //TODO: check cant turnos
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            System.out.println(node);
        }
    }
}
