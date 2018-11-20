package com.up.ia;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.FieldCellType;
import ia.battle.core.Warrior;
import ia.battle.core.actions.Move;
import com.up.ia.astar.Node;
import com.up.ia.astar.AStar;

import java.util.ArrayList;
import java.util.List;

public class Movement extends Move {
    private FieldCell nextMove;
    private FieldCell position;
    private Warrior warrior;
    private ArrayList<FieldCell> cells2;

    Movement(FieldCell to, FieldCell from) {
        nextMove = to;
        this.position = from;
    }

    public Movement(FieldCell fieldCell, FieldCell position, Warrior warrior) {
        this(fieldCell, position);
        this.warrior = warrior;
        calculateMove();
    }

    private void calculateMove() {
        double maxStepPoints = warrior.getSpeed() / 5;

//        System.out.println(warrior.getName() + " My Position " + this.position);
//        System.out.println(warrior.getName() + " Enemy Position " + this.nextMove);

        int xini, yini, xfin, yfin;
        xini = position.getX();
        yini = position.getY();

        xfin = nextMove.getX();
        yfin = nextMove.getY();

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

        Node initialNode = new Node(yini, xini);
        Node finalNode = new Node(yfin, xfin);

        AStar aStar = new AStar(rows, cols, initialNode, finalNode, 1, 1.41f, cells);
        aStar.setBlocks(blockedNodes);
        //        AStar aStar = IaEngine.getaStar();
        aStar.setInitialNode(initialNode);
        aStar.setFinalNode(finalNode);

        List<Node> path = aStar.findPath();
//        System.out.println("PATH");
//        System.out.println(path);
        cells2 = new ArrayList<>();
        double paths = 0;
        //        path.remove(0);
        for (Node node : path) {
            int row = node.getRow();
            int col = node.getCol();
            //            System.out.println(node);
            paths = paths + node.getCost();
            if (paths <= maxStepPoints) {
                cells2.add(BattleField.getInstance().getFieldCell(node.getCol(), node.getRow()));
                //                System.out.println(node);
            }
        }
//        System.out.println(cells2);
    }

    @Override
    public ArrayList<FieldCell> move() {
        return cells2;
    }
}
