package nl.aalten.dojo.tictactoe.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BoardTest {
    private final Player playerX = new Player(PlayerMark.X);
    private final Player playerO = new Player(PlayerMark.O);

    @Nested
    class PlaceMark {
        @ParameterizedTest
        @ValueSource(ints = {-1, 3})
        public void placeMarkShouldThrowExceptionWhenXBoundaryIsExceeded(int xCoordinate) {
            Board board = new Board();

            assertThatThrownBy(
                    () -> board.placeMark(playerX, new Cell(xCoordinate,2)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("X-coordinate out of bounds. Maximum value is 2");
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 3})
        public void placeMarkShouldThrowExceptionWhenYBoundaryIsExceeded(int yCoordinate) {
            Board board = new Board();

            assertThatThrownBy(
                    () -> board.placeMark(playerX, new Cell(2, yCoordinate)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Y-coordinate out of bounds. Maximum value is 2");
        }

        @Test
        public void shouldThrowExceptionWhenCellIsAlreadyOccupied() {
            final Cell cell = new Cell(0, 0);
            Board board = new Board();

            board.placeMark(playerX, cell);

            assertThatThrownBy(
                    () -> board.placeMark(playerX, cell))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Place mark on cell " + cell + " not allowed since cell is already taken");
        }

    }

    @Nested
    public class DetermineNextBestMove {

        @Test
        public void shouldDetermineFirstMoveWhenBoardIsEmpty() {
            Board board = new Board();
            assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(0, 0));
        }

        @Nested
        class PlayerHasTwoMarksOnWinningLine {

            @Nested
            class HorizontalWinningLines {

                @Test
                public void FirstTwoCells() {
                    IntStream.range(0, 3).forEach(row -> {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(0, row));
                        board.placeMark(playerX, new Cell(1, row));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(2, row));
                    });
                }

                @Test
                public void LastTwoCells() {
                    IntStream.range(0, 3).forEach(row -> {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(1, row));
                        board.placeMark(playerX, new Cell(2, row));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(0, row));
                    });
                }

            }

            @Nested
            class VerticalWinningLines {

                @Test
                public void FirstTwoCells() {
                    IntStream.range(0, 3).forEach(col -> {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(col, 0));
                        board.placeMark(playerX, new Cell(col, 1));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(col, 2));
                    });
                }

                @Test
                public void LastTwoCells() {
                    IntStream.range(0, 3).forEach(col -> {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(col, 1));
                        board.placeMark(playerX, new Cell(col, 2));
                    });
                }

            }

            @Nested
            class DiagonalWinningLine {

                @Nested
                class TopLeftBottomRight {

                    @Test
                    public void FirstTwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(0, 0));
                        board.placeMark(playerX, new Cell(1, 1));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(2, 2));
                    }

                    @Test
                    public void LastTwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(1, 1));
                        board.placeMark(playerX, new Cell(2, 2));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(0, 0));
                    }

                }

                @Nested
                class BottomLeftTopRight {

                    @Test
                    public void FirstTwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(2, 0));
                        board.placeMark(playerX, new Cell(1, 1));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(0, 2));
                    }

                    @Test
                    public void TwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(1, 1));
                        board.placeMark(playerX, new Cell(0, 2));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(2, 0));
                    }

                }

            }

        }

    }

}
