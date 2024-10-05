package nl.aalten.dojo.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

import nl.aalten.dojo.tictactoe.domain.TicTacToeBoard;

public class Button extends JButton {

    public Button(String text, TicTacToeBoard board) {
        setText(text);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setFocusPainted(false);
        addActionListener(e -> board.resetBoard());
    }
}
