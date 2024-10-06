package nl.aalten.dojo.tictactoe.ui;

import javax.swing.*;
import java.awt.*;


public class ResetGameButton extends JButton {

    public ResetGameButton() {
        setText("Reset game");
        setFont(new Font("Arial", Font.PLAIN, 20));
        setFocusPainted(false);
    }
}
