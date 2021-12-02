package view;

import controller.CellController;
import model.Blackboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {

    public static final int CELLS_PER_ROW = 20;
    public static final int ROWS = 20;
    JButton startBtn;
    JButton pauseBtn;

    public Game(){
        super("Game of Life");

        setSize(500, 500);
        setLayout(new BorderLayout());

        initCells(this);
        initButtons(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Game();
    }

    private void initCells(JFrame frame){

        Container grid = new Container();
        grid.setLayout(new GridLayout(ROWS, CELLS_PER_ROW));

        Cell[][] cells = Blackboard.getInstance().getCells();
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < CELLS_PER_ROW; j++){
                grid.add(cells[i][j]);
            }
        }

        frame.add(grid, BorderLayout.CENTER);

    }

    private void initButtons(JFrame frame){

        JPanel buttonPanel = new JPanel();

        JButton increment = new JButton("Increment");
        increment.addActionListener(this);

        JButton reset = new JButton("Reset");
        reset.addActionListener(this);

        startBtn = new JButton("Start");
        startBtn.addActionListener(this);

        pauseBtn = new JButton("Pause");
        pauseBtn.addActionListener(this);

        buttonPanel.add(startBtn);
        buttonPanel.add(increment);
        buttonPanel.add(pauseBtn);
        buttonPanel.add(reset);

        frame.add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        CellController cellController = CellController.getInstance();

	switch (action) {
            case "Start":
                cellController.startIterating();
                startBtn.setEnabled(false);
                pauseBtn.setEnabled(true);
                break;
            case "Pause":
                cellController.pauseIteration();
                startBtn.setEnabled(true);
                pauseBtn.setEnabled(false);
                break;
            
		
	    default:
                break;
	}

    }
}
