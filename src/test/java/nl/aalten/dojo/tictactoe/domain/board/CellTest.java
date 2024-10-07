package nl.aalten.dojo.tictactoe.domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CellTest {

    @ParameterizedTest
    @ValueSource(ints = { -1, 3})
    void placeMarkShouldThrowExceptionWhenXBoundaryIsExceeded(int xCoordinate) {
        assertThatThrownBy(
                () -> new Cell(xCoordinate,2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("X-coordinate out of bounds. Maximum value is 2");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void placeMarkShouldThrowExceptionWhenYBoundaryIsExceeded(int yCoordinate) {
        Board board = new Board();

        assertThatThrownBy(
                () -> new Cell(2, yCoordinate))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Y-coordinate out of bounds. Maximum value is 2");
    }

    @Test
    void shouldDetermineCellsAreEqual() {
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(0,0);
        assertTrue(cell1.equals(cell1));
        assertTrue(cell1.equals(cell2));
    }

    @Test
    void shouldDetermineCellsAreNotEqualDueToDifferentValues() {
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(0,1);
        assertFalse(cell1.equals(cell2));
    }

    @Test
    void shouldDetermineCellsAreNotEqualDueToDifferentClassTypes() {
        Cell cell1 = new Cell(0,0);
        assertFalse(cell1.equals("not a Cell"));
    }

    @Test
    void shouldDetermineCellsAreNotEqualDueToComparingWithNullValue() {
        Cell cell1 = new Cell(0,0);
        assertFalse(cell1.equals(null));
    }

}
