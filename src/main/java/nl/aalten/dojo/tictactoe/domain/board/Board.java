package nl.aalten.dojo.tictactoe.domain.board;

public class Board {
    private final PlayerMark[][] playerMarks = initializePlayerMarks();

    private PlayerMark[][] initializePlayerMarks() {
        return new PlayerMark[3][3];
    }

    public void placeMark(Player player, Cell cell) throws IllegalStateException {
        throw new IllegalStateException("Not implemented yet");
    }

    public Cell determineNextBestMove(Player player) {
        throw new IllegalStateException("Not implemented yet");
    }

    public Player checkForWinner() {
        throw new IllegalStateException("Not implemented yet");
    }

    public boolean isBoardFull() {
        throw new IllegalStateException("Not implemented yet");
    }

    public boolean hasPlacedMark(Player player) {
        throw new IllegalStateException("Not implemented yet");
    }

}
