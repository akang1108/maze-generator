package info.akang.maze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

public class Maze {
    private final int height;
    private final int width;
    private final List<List<Cell>> cells;
    private final Set<Wall> walls;
    private final Map<Cell, Set<Wall>> wallLookup;

    protected Maze(int height, int width, List<List<Cell>> cells, Set<Wall> walls) {
        this.height = height;
        this.width = width;
        this.cells = cells;
        this.walls = walls;
        this.wallLookup = new HashMap<>();
        for (Wall wall: walls) {
            Set<Wall> cell1Walls = wallLookup.get(wall.getCell1());
            if (cell1Walls == null) {
                cell1Walls = new HashSet<>();
            }
            cell1Walls.add(wall);
            wallLookup.put(wall.getCell1(), cell1Walls);

            Set<Wall> cell2Walls = wallLookup.get(wall.getCell2());
            if (cell2Walls == null) {
                cell2Walls = new HashSet<>();
            }
            cell2Walls.add(wall);
            wallLookup.put(wall.getCell2(), cell2Walls);
        }

//        for (Entry<Cell, Set<Wall>> entry: this.wallLookup.entrySet()) {
//            System.out.println(entry.getKey().getX() + "," + entry.getKey().getY() + ": " + entry.getValue().size());
//        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public boolean wallRight(Cell cell) {
        return cellWallCheck(cell, (cell1, cell2) -> cell1.x() + 1 == cell2.x() && cell1.y() == cell2.y());
    }

    public boolean wallDown(Cell cell) {
        return cellWallCheck(cell, (cell1, cell2) -> cell1.x() == cell2.x() && cell1.y() + 1 == cell2.y());
    }

    public boolean cellWallCheck(Cell cell, BiPredicate<Cell, Cell> cellPredicate) {
        Set<Wall> cellWalls = this.wallLookup.get(cell);

        if (cellWalls == null) {
            return false;
        }

        for (Wall cellWall: cellWalls) {
            if (cellPredicate.test(cell, cellWall.getCell1()) || cellPredicate.test(cell, cellWall.getCell2())) {
                return true;
            }
        }
        return false;
    }

}
