package nl.aalten.dojo.tictactoe.domain;

import nl.aalten.dojo.tictactoe.domain.board.Board;
import nl.aalten.dojo.tictactoe.domain.board.Cell;
import nl.aalten.dojo.tictactoe.domain.board.Player;
import nl.aalten.dojo.tictactoe.ui.Window;

public class Game {
    private final PlayerTurnSelector turnSelector = new PlayerTurnSelector();
    private final Window window;
    private final Board board;

    private Player playerOnTurn;

    public Game() {
        this.board = new Board();
        this.window = new Window(this);
    }

    public void start() {
        playerOnTurn = turnSelector.determinePlayerToStart();

        while (!board.hasWinner() && !board.isBoardFull()) {
            final Cell bestNextMove = board.determineNextBestMove(playerOnTurn);
            placeMark(bestNextMove);
            switchPlayer();
            pause();
        }

        window.showMessage("Game over");
    }

    private void switchPlayer() {
        playerOnTurn = (playerOnTurn == PlayerTurnSelector.playerO) ? PlayerTurnSelector.playerX : PlayerTurnSelector.playerO;
    }

    private void placeMark(Cell bestNextMove) {
        board.placeMark(playerOnTurn, bestNextMove);
        window.drawBoard(board);
    }

    public void reset() {
        window.showMessage("Resetting the board");
        resetBoard();
        start();
    }

    private void resetBoard() {
        board.reset();
        window.drawBoard(board);
    }

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
