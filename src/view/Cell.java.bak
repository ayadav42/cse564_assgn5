package view;

import javax.swing.*;
import java.util.Objects;

public class Cell extends JButton {
    public final int x;
    public final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void updateState(boolean state) {
        if (state) {
            setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(this.getClass().getResource("yellow_icon.jpg"))));
        } else {
            setIcon(null);
        }
    }
}
