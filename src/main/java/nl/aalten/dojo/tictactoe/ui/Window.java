package nl.aalten.dojo.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

import nl.aalten.dojo.tictactoe.domain.Game;

public class Window extends JFrame {
    private final BoardPanel boardPanel;
    private final ResetGameButton resetGameButton;

    public Window(Game game) {
        this.boardPanel = new BoardPanel();
        this.resetGameButton = new ResetGameButton();
        this.resetGameButton.addActionListener(e -> game.reset());
        drawWindow();
    }

    private void drawWindow() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(boardPanel, BorderLayout.CENTER);
        add(resetGameButton, BorderLayout.SOUTH);
        setVisible(true);
    }


}
