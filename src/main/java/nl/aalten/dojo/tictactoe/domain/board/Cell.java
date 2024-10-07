package nl.aalten.dojo.tictactoe.domain.board;

public record Cell(int x, int y) {

    public Cell {
        if (isCoordinateOutOfBounds(x)) {
            throw new IllegalStateException("X-coordinate out of bounds. Maximum value is 2");
        }
        if (isCoordinateOutOfBounds(y)) {
            throw new IllegalStateException("Y-coordinate out of bounds. Maximum value is 2");
        }
    }

    private boolean isCoordinateOutOfBounds(int coordinate) {
        return coordinate < 0 || coordinate > 2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell cell = (Cell) obj;
        return x == cell.x && y == cell.y;
    }

}
