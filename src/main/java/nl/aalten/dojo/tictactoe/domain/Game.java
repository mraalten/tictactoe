package nl.aalten.dojo.tictactoe.domain;

import javax.swing.*;

import nl.aalten.dojo.tictactoe.domain.board.Board;
import nl.aalten.dojo.tictactoe.domain.board.Player;
import nl.aalten.dojo.tictactoe.domain.board.PlayerMark;
import nl.aalten.dojo.tictactoe.ui.Window;

public class Game {
    private final PlayerTurnSelector turnSelector = new PlayerTurnSelector();
    private final Player playerX = new Player(PlayerMark.X);
    private final Player playerO = new Player(PlayerMark.O);

    private final Window ticTacToeWindow;

    private Player playerOnTurn;

    public Game() {
        Board board = new Board();
        this.ticTacToeWindow = new Window(this);
    }

    public void start() {

    }

    private void determinePlayerToStart() {

    }

    public void reset() {
        JOptionPane.showMessageDialog(ticTacToeWindow, "Resetting the board");
    }

}
