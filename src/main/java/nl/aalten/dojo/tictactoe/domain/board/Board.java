package nl.aalten.dojo.tictactoe.domain.board;

import java.util.Arrays;

public class Board {
    private PlayerMark[][] playerMarks;

    public Board() {
        initializePlayerMarks();
    }

    private void initializePlayerMarks() {
       this.playerMarks = new PlayerMark[3][3];
    }

    public void placeMark(Player player, Cell cell) throws IllegalStateException {
        throw new IllegalStateException("Not implemented yet");
    }

    public Cell determineNextBestMove(Player player) {
        throw new IllegalStateException("Not implemented yet");
    }

    public boolean hasWinner() {
        return checkForWinner() != null;
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

    public void reset() {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Returns a defensive copy of the array so users can not modify the original one
     * @return a clone of the original array
     */
    public PlayerMark[][] getPlayerMarks() {
        return Arrays.stream(playerMarks)
            .map(innerArray -> Arrays.copyOf(innerArray, innerArray.length))
            .toArray(PlayerMark[][]::new);
    }

}
