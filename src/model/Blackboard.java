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
                this.cells[i][j] = new Cell(i, j, CellController.getInstance());
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

    public void toggleCellState(Cell cell) {

        this.state[cell.x][cell.y] = !this.state[cell.x][cell.y];
        cell.updateState(this.state[cell.x][cell.y]);

    }

    public boolean[][] getState() {
        return this.state;
    }

    public void setState(boolean[][] state) {
        this.state = state;
    }

    public int getNumberOfAliveNbrs(Cell cell) {

        int count = 0;
        int row = cell.x;
        int col = cell.y;
        int n = cells.length - 1;
        int m = cells[0].length - 1;

        //top-left
        if (row > 0 && col > 0 && this.state[row - 1][col - 1]) count += 1;

        //top
        if (row > 0 && this.state[row - 1][col]) count += 1;

        //top-right
        if (row > 0 && col < m && this.state[row - 1][col + 1]) count += 1;

        //right
        if (col < m && this.state[row][col + 1]) count += 1;

        //bottom-right
        if (row < n && col < m && this.state[row + 1][col + 1]) count += 1;

        //bottom
        if (row < n && this.state[row + 1][col]) count += 1;

        //bottom-left
        if (row < n && col > 0 && this.state[row + 1][col - 1]) count += 1;

        //left
        if (col > 0 && this.state[row][col - 1]) count += 1;

        return count;
    }

    public boolean getCellState(Cell cell) {
        return this.state[cell.x][cell.y];
    }

    public void resetState() {

        //iterate over all cells and set their state to false
        this.state = new boolean[cells.length][cells[0].length];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                cells[i][j].updateState(false);
            }
        }

    }
}
