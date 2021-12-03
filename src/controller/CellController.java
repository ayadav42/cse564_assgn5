package controller;

import model.Blackboard;
import model.CellStatus;
import view.Cell;
import view.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class CellController implements ActionListener {

    private static CellController _instance;
    private Timer timer;

    public static CellController getInstance() {
        if (_instance == null) _instance = new CellController();
        return _instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cell cell = (Cell) e.getSource();
        Blackboard.getInstance().toggleCellState(cell);
    }

    public void startIterating() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                incrementState();
            }
        };
        this.timer = new Timer();
        timer.schedule(timerTask, 0, 500);

    }

    public void reset(){
        pauseIteration();
        Blackboard.getInstance().resetState();
    }

    public CellStatus getCellStatus(Cell cell) { //getConditionofCell

        int aliveNbrs = Blackboard.getInstance().getNumberOfAliveNbrs(cell);
        boolean cellState = Blackboard.getInstance().getCellState(cell);
        CellStatus status = CellStatus.NOTHING;

        if (cellState && aliveNbrs <= 1) {
            status = CellStatus.ISOLATED;
        } else if (cellState && aliveNbrs >= 4) {
            status = CellStatus.OVERPOPULATED;
        } else if (cellState && (aliveNbrs == 2 || aliveNbrs == 3)) {
            // && (aliveNbrs == 2 || aliveNbrs == 3)
            status = CellStatus.WILL_SURVIVE;
        } else if (aliveNbrs == 3) {
            status = CellStatus.SPAWNED;
        }

        return status;

    }


    public void updateCellState(boolean[][] state, Cell cell) {

        boolean[][] oldState = Blackboard.getInstance().getState();
        CellStatus status = getCellStatus(cell);
        boolean newCellState = false;

        switch (status) {
            case ISOLATED:
                newCellState = false;
                break; //cell dies
            case OVERPOPULATED:
                newCellState = false;
                break; //cell dies
            case WILL_SURVIVE:
                newCellState = true;
                break;
            case SPAWNED:
                newCellState = true;
                break;
            case NOTHING:
                newCellState = oldState[cell.x][cell.y];
                break;
        }

        state[cell.x][cell.y] = newCellState;
        cell.updateState(newCellState);
    }

	public void pauseIteration() {
        if(this.timer != null){
            this.timer.cancel();
        }
    }

    public void incrementState() {

        boolean[][] newState = new boolean[Game.ROWS][Game.CELLS_PER_ROW];
        Cell[][] cells = Blackboard.getInstance().getCells();

        for (Cell[] rowOfCells : cells) {
            for (Cell cell : rowOfCells) {
                updateCellState(newState, cell);
            }
        }

        Blackboard.getInstance().setState(newState);

    }
}
