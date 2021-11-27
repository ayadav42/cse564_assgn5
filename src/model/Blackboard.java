package model;

import controller.CellController;
import view.Cell;
import view.Game;

public class Blackboard {

    private static Blackboard _instance;

    private final Cell[][] cells;

    private boolean[][] state;

    public Blackboard() {

        this.cells = new Cell[Game.ROWS][Game.CELLS_PER_ROW];
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                this.cells[i][j] = new Cell();
            }
        }

        this.state = new boolean[Game.ROWS][Game.CELLS_PER_ROW];

    }

    public static Blackboard getInstance() {
        if (_instance == null) _instance = new Blackboard();
        return _instance;
    }

    public Cell[][] getCells() {
        return cells;
    }

}
