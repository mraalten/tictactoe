package nl.aalten.dojo.tictactoe.domain.board;

public record Cell(int x, int y) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell cell = (Cell) obj;
        return x == cell.x && y == cell.y;
    }

    @Override
    public String toString() {
        return "Cell(" + "x=" + x + ", y=" + y + ')';
    }

    public boolean isXCoordinateWithinBoundaries() {
        return isCoordinateWithinBoundaries(x);
    }

    public boolean isYCoordinateWithinBoundaries() {
        return isCoordinateWithinBoundaries(y);
    }

    private boolean isCoordinateWithinBoundaries(int coordinate) {
        return coordinate >= 0 && coordinate < 3;
    }

}
