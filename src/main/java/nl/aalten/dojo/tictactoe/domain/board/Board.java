package nl.aalten.dojo.tictactoe.domain.board;

import java.util.Arrays;
import java.util.Objects;
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
        // count number of marks per win line per player (both X and O)
        // if supplied player has already 2 marks on win line, determine whether 3rd option is still open. If open, place mark and win! => end
        // determine if 'other player' already has 2 marks on win line and whether 3rd option is still open. If open, place mark and block 'other player' => end
        // find win line with 1 mark for supplied player and find open mark on that win line. If open, place mark => end
        // find the first open place and place mark for supplied player => end
        throw new IllegalStateException("Not implemented yet");
    }

    public Optional<Player> getWinner() {
        throw new IllegalStateException("Not implemented yet");
    }

    public boolean isBoardFull() {
        return Arrays.stream(playerMarks)
                .allMatch(row -> Arrays.stream(row)
                .allMatch(Objects::nonNull));
    }

    public boolean hasPlacedMark(Player player) {
        return Arrays.stream(playerMarks)
                .flatMap(Arrays::stream)
                .anyMatch(mark -> mark == player.mark());
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
