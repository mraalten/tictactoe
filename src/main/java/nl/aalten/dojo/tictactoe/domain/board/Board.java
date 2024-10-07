package nl.aalten.dojo.tictactoe.domain.board;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;
import static nl.aalten.dojo.tictactoe.domain.PlayerTurnSelector.PLAYER_O;
import static nl.aalten.dojo.tictactoe.domain.PlayerTurnSelector.PLAYER_X;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import nl.aalten.dojo.tictactoe.domain.Die;
import nl.aalten.dojo.tictactoe.domain.PlayerTurnSelector;

public class Board {
    private final List<WinLine> winLines = List.of(
        new WinLine(new Cell(0,0), new Cell(1,0), new Cell(2,0)),
        new WinLine(new Cell(0,1), new Cell(1,1), new Cell(2,1)),
        new WinLine(new Cell(0,2), new Cell(1,2), new Cell(2,2)),

        new WinLine(new Cell(0,0), new Cell(0,1), new Cell(0,2)),
        new WinLine(new Cell(1,0), new Cell(1,1), new Cell(1,2)),
        new WinLine(new Cell(2,0), new Cell(2,1), new Cell(2,2)),

        new WinLine(new Cell(0,0), new Cell(1,1), new Cell(2,2)),
        new WinLine(new Cell(0,2), new Cell(1,1), new Cell(2,0))
    );

    private PlayerMark[][] playerMarks;
    private final PlayerTurnSelector turnSelector = new PlayerTurnSelector(new Die());
    private final Random random = new Random();

    public Board() {
        initializePlayerMarks();
    }

    private void initializePlayerMarks() {
       this.playerMarks = new PlayerMark[3][3];
    }

    public void placeMark(Player player, Cell cell) throws IllegalStateException {
        if (getValueFromBoardFor(cell) != null) {
            throw new IllegalStateException("Place mark on cell " + cell + " not allowed since cell is already taken");
        }
        playerMarks[cell.x()][cell.y()] = player.mark();
    }

    public Cell determineNextBestMove(Player player) {
        if (!hasPlacedMark(player)) {
            return randomEmptyCellOnTheBoard();
        }
        return Stream.of(
            getEmptyCellOnWinLineWithMarksFor(player, 2),
            getEmptyCellOnWinLineWithMarksFor(turnSelector.getOtherPlayer(player), 2),
            getEmptyCellOnWinLineWithMarksFor(player, 1),
            getEmptyCellOnWinLineWithMarksFor(turnSelector.getOtherPlayer(player), 1)
        )
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst()
            .orElse(null);
    }

    private Cell randomEmptyCellOnTheBoard() {
        Cell cell = null;
        while (cell == null) {
            int x = random.nextInt(2);
            int y = random.nextInt(2);
            if (playerMarks[x][y] == null) {
                cell = new Cell(x, y);
            }
        }
        return cell;
    }

    private Optional<Cell> getEmptyCellOnWinLineWithMarksFor(Player player, int numberOfMarks) {
        return winLines.stream()
                .filter(winline -> getNumberOfMarksOnWinLine(player, winline) == numberOfMarks)
                .map(this::winLineHasEmptyCell)
                .flatMap(Optional::stream)
                .findFirst();
    }

    private long getNumberOfMarksOnWinLine(Player player, WinLine winline) {
        return stream(winline.cells()).filter(cell -> getValueFromBoardFor(cell) == player.mark()).count();
    }

    private Optional<Cell> winLineHasEmptyCell(WinLine winline) {
        return stream(winline.cells()).filter(cell -> getValueFromBoardFor(cell) == null).findFirst();
    }

    private PlayerMark getValueFromBoardFor(Cell cell) {
        return playerMarks[cell.x()][cell.y()];
    }

    public Optional<Player> getWinner() {
        final Optional<WinLine> winLineWithThreeMarksPlayerX = winLines.stream().filter(winline -> getNumberOfMarksOnWinLine(PLAYER_X, winline) == 3).findFirst();
        if (winLineWithThreeMarksPlayerX.isPresent()) {
            return Optional.of(PLAYER_X);
        }
        final Optional<WinLine> winLineWithThreeMarksPlayerO = winLines.stream().filter(winline -> getNumberOfMarksOnWinLine(PLAYER_O, winline) == 3).findFirst();
        if (winLineWithThreeMarksPlayerO.isPresent()) {
            return Optional.of(PLAYER_O);
        }
        return Optional.empty();
    }

    public boolean isBoardFull() {
        return stream(playerMarks)
                .allMatch(row -> stream(row)
                .allMatch(Objects::nonNull));
    }

    public boolean hasPlacedMark(Player player) {
        return stream(playerMarks)
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
        return stream(playerMarks)
            .map(innerArray -> copyOf(innerArray, innerArray.length))
            .toArray(PlayerMark[][]::new);
    }

}
