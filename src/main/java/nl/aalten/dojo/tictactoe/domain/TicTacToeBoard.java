package nl.aalten.dojo.tictactoe.domain;

import javax.swing.*;

import nl.aalten.dojo.tictactoe.ui.BoardPanel;

public class TicTacToeBoard {
    private final BoardPanel boardPanel;
    private PlayerMark[][] playerMarks = initializePlayerMarks();

    private PlayerMark[][] initializePlayerMarks() {
        return new PlayerMark[3][3];
    }

    private final Player playerX = new Player(PlayerMark.X);
    private final Player playerO = new Player(PlayerMark.O);

    private Player playerOnTurn;

    public TicTacToeBoard(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public void placeMark(Player player, BoardCell cell) {

    }

    public BoardCell determineNextMove(Player player) {
        return new BoardCell(0, 0);
    }

    public Player checkForWinner() {
        return null;
    }

    public boolean isBoardFull() {
        return true;
    }

    public void resetBoard() {
        JOptionPane.showMessageDialog(boardPanel, "Resetting the board");
    }

    public void startGame() {
    }

    private void redrawBoard() {
        this.boardPanel.drawBoard(playerMarks);
    }

    private void determinePlayerToStart() {

    }

}
