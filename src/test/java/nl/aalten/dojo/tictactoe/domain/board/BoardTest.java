package nl.aalten.dojo.tictactoe.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {
    private final Player playerX = new Player(PlayerMark.X);
    private final Player playerO = new Player(PlayerMark.O);

    @Nested
    class PlaceMark {

        @Test
        void shouldThrowExceptionWhenCellIsAlreadyOccupied() {
            Board board = new Board();
            final Cell cell = new Cell(0, 0);

            board.placeMark(playerX, cell);

            assertThatThrownBy(
                    () -> board.placeMark(playerX, cell))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Place mark on cell " + cell + " not allowed since cell is already taken");
        }

    }

    @Nested
    class DetermineNextBestMove {

        @Nested
        class PlayerHasTwoMarksOnWinningLine {

            @Nested
            class HorizontalWinningLines {

                @Test
                void FirstTwoCells() {
                    IntStream.range(0, 3).forEach(row -> {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(0, row));
                        board.placeMark(playerX, new Cell(1, row));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(2, row));
                    });
                }

                @Test
                void LastTwoCells() {
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
                void FirstTwoCells() {
                    IntStream.range(0, 3).forEach(col -> {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(col, 0));
                        board.placeMark(playerX, new Cell(col, 1));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(col, 2));
                    });
                }

                @Test
                void LastTwoCells() {
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
                    void FirstTwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(0, 0));
                        board.placeMark(playerX, new Cell(1, 1));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(2, 2));
                    }

                    @Test
                    void LastTwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(1, 1));
                        board.placeMark(playerX, new Cell(2, 2));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(0, 0));
                    }

                }

                @Nested
                class BottomLeftTopRight {

                    @Test
                    void FirstTwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(2, 0));
                        board.placeMark(playerX, new Cell(1, 1));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(0, 2));
                    }

                    @Test
                    void TwoCells() {
                        Board board = new Board();
                        board.placeMark(playerX, new Cell(1, 1));
                        board.placeMark(playerX, new Cell(0, 2));

                        assertThat(board.determineNextBestMove(playerX)).isEqualTo(new Cell(2, 0));
                    }

                }

            }

        }

    }

    @Nested
    class GetWinner {

        @Test
        void shouldNotHaveAWinnerSinceNoThreeMarksInARow() {
            Board board = new Board();
            assertThat(board.getWinner()).isEqualTo(Optional.empty());
        }

        @Test
        void playerXHasWonSinceThreeMarksInARow() {
            Board board = new Board();
            board.placeMark(playerX, new Cell(0,0));
            board.placeMark(playerX, new Cell(0,1));
            board.placeMark(playerX, new Cell(0,2));

            assertThat(board.getWinner().get()).isEqualTo(playerX);
        }

    }

    @Nested
    class BoardFull {

        @Test
        void shouldReturnFalseBecauseThereAreEmptySpotsOnTheBoard() {
            Board board = new Board();
            board.placeMark(playerX, new Cell(0,0));
            assertThat(board.isBoardFull()).isEqualTo(false);
        }

        @Test
        void shouldReturnTrueBecauseSpotsAreTakenOnTheBoard() {
            Board board = new Board();
            IntStream.range(0, 3).forEach(x ->
                    IntStream.range(0, 3).forEach(y -> {
                        board.placeMark(playerX, new Cell(x,y));
                    })
            );
            assertThat(board.isBoardFull()).isEqualTo(true);

        }

    }

    @Nested
    class HasPlacedMark {

        @Test
        void shouldReturnFalseSincePlayerHasNotPlacedAnyMarkYet() {
            Board board = new Board();
            assertThat(board.hasPlacedMark(playerX)).isEqualTo(false);
        }

        @Test
        void shouldReturnTrueSincePlayerHasPlayedAtLeastOneMark() {
            Board board = new Board();

            board.placeMark(playerX, new Cell(0,0));
            assertThat(board.hasPlacedMark(playerX)).isEqualTo(true);

            board.placeMark(playerO, new Cell(1,1));
            board.placeMark(playerO, new Cell(1,2));
            assertThat(board.hasPlacedMark(playerX)).isEqualTo(true);
        }

    }

}
