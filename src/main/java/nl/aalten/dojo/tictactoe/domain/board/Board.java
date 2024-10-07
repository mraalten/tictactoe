package nl.aalten.dojo.tictactoe.domain.board;

import java.util.Arrays;
import java.util.Optional;

public class Board {
    private PlayerMark[][] playerMarks;

    public Board() {
        initializePlayerMarks();
    }

    private void initializePlayerMarks() {
       this.playerMarks = new PlayerMark[3][3];
    }

    public void placeMark(Player player, Cell cell) throws IllegalStateException {
        if (playerMarks[cell.x()][cell.y()] != null) {
            throw new IllegalStateException("Place mark on cell " + cell + " not allowed since cell is already taken");
        }
        playerMarks[cell.x()][cell.y()] = player.mark();
    }

    public Cell determineNextBestMove(Player player) {
        throw new IllegalStateException("Not implemented yet");
    }

    public Optional<Player> getWinner() {
        throw new IllegalStateException("Not implemented yet");
    }

    public boolean isBoardFull() {
        throw new IllegalStateException("Not implemented yet");
    }

    public boolean hasPlacedMark(Player player) {
        throw new IllegalStateException("Not implemented yet");
    }

    public void reset() {
        initializePlayerMarks();
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
