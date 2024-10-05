package nl.aalten.dojo.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

import nl.aalten.dojo.tictactoe.domain.TicTacToeBoard;

public class TicTacToeWindow extends JFrame {
    private final BoardPanel boardPanel = new BoardPanel();
    private TicTacToeBoard board = new TicTacToeBoard(boardPanel);
    private final Button resetGameButton = new Button("Reset game", board);

    public TicTacToeWindow() {
        drawWindow();
        board.startGame();
//        boardPanel.startGame();
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
