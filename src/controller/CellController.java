package controller;

import model.Blackboard;
import model.CellStatus;
import view.Cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellController implements ActionListener {

    private static CellController _instance;

    public static CellController getInstance() {
        if (_instance == null) _instance = new CellController();
        return _instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public CellStatus getCellStatus(Cell cell) { //getConditionofCell

        int aliveNbrs = Blackboard.getInstance().getNumberOfAliveNbrs(cell);
        boolean cellState = Blackboard.getInstance().getCellState(cell);
        CellStatus status = CellStatus.NOTHING;

        if (cellState && aliveNbrs <= 1) {
            status = CellStatus.ISOLATED;
        } else if (cellState && aliveNbrs >= 4) {
            status = CellStatus.OVERPOPULATED;
        } else if (cellState) {
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

}
