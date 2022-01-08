package info.akang.maze;

import java.util.Objects;

public class Wall {
    private final Cell cell1;
    private final Cell cell2;

    public Wall(Cell cell1, Cell cell2) {
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    public Cell getCell1() {
        return cell1;
    }

    public Cell getCell2() {
        return cell2;
    }

    /**
     * Wall is considered equal as long as the 2 cells are equal, doesn't matter which order.
     *
     * @param o - wall to compare with
     * @return boolean indicating object equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wall wall = (Wall) o;

        return
                Objects.equals(cell1, wall.cell1) && Objects.equals(cell2, wall.cell2) ||
                Objects.equals(cell1, wall.cell2) && Objects.equals(cell2, wall.cell1);
    }

    @Override
    public int hashCode() {
        int result = cell1 != null ? cell1.hashCode() : 0;
        result = 31 * result + (cell2 != null ? cell2.hashCode() : 0);
        return result;
    }
}
