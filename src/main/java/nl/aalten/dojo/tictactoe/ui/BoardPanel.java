package nl.aalten.dojo.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

import nl.aalten.dojo.tictactoe.domain.PlayerMark;

public class BoardPanel extends JPanel  {
    private JButton[][] buttons;

    public BoardPanel() {
        initializeBoard();
    }

    private void initializeBoard() {
        removeAll();
        setLayout(new GridLayout(3, 3));
        drawBoard();
    }

    private void drawBoard() {
        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = button();
                add(buttons[row][col]);
            }
        }
    }

    public void drawBoard(PlayerMark[][] playerMarks) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                String text = playerMarks[row][col] == null ? "" : playerMarks[row][col].value;
                buttons[row][col].setText(text);
                add(buttons[row][col]);
            }
        }
    }

    private JButton button() {
        JButton button = new JButton("");
        button.setFont(new Font("Arial", Font.PLAIN, 60));
        button.setFocusPainted(false);
        return button;
    }


}
