package controller;

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
}
