package nl.aalten.dojo.tictactoe.domain.board;

public class Board {
    private final PlayerMark[][] playerMarks = initializePlayerMarks();

    private PlayerMark[][] initializePlayerMarks() {
        return new PlayerMark[3][3];
    }

    public void placeMark(Player player, Cell cell) {

    }

    public Cell determineNextMove(Player player) {
        return new Cell(0, 0);
    }

    public Player checkForWinner() {
        return null;
    }

    public boolean isBoardFull() {
        return true;
    }

}
