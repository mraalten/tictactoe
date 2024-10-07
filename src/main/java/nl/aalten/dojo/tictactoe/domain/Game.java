package nl.aalten.dojo.tictactoe.domain;

import nl.aalten.dojo.tictactoe.domain.board.Board;
import nl.aalten.dojo.tictactoe.domain.board.Cell;
import nl.aalten.dojo.tictactoe.domain.board.Player;
import nl.aalten.dojo.tictactoe.ui.Window;

public class Game {
    private final PlayerTurnSelector turnSelector = new PlayerTurnSelector(new Die());
    private final Window window;
    private final Board board;

    private Player playerOnTurn;

    public Game() {
        this.board = new Board();
        this.window = new Window(this);
    }

    public void start() {
        playerOnTurn = turnSelector.determinePlayerToStart();

        while (board.getWinner().isEmpty() && !board.isBoardFull()) {
            pause();
            final Cell bestNextMove = board.determineNextBestMove(playerOnTurn);
            placeMark(bestNextMove);
            switchPlayer();
        }

        if (board.getWinner().isPresent()) {
            window.showMessage("Player " + board.getWinner().get().mark() + " won! Game over");
        } else {
            window.showMessage("No winner! Game over");
        }
    }

    private void switchPlayer() {
        this.playerOnTurn = turnSelector.getOtherPlayer(playerOnTurn);
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
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
