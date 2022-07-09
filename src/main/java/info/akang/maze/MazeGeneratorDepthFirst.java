package info.akang.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This implementation is following iterative algorithm:
 *      https://en.wikipedia.org/wiki/Maze_generation_algorithm#Iterative_implementation
 *
 * Choose the initial cell, mark it as visited and push it to the stack
 * While the stack is not empty
 *      Pop a cell from the stack and make it a current cell
 *      If the current cell has any neighbours which have not been visited
 *          Push the current cell to the stack
 *          Choose one of the unvisited neighbours
 *          Remove the wall between the current cell and the chosen cell
 *          Mark the chosen cell as visited and push it to the stack
 *
 */
public class MazeGeneratorDepthFirst implements MazeGenerator {
    @Override
    public Maze generate(int height, int width) {
        if (height < 1 || height > 50) {
            throw new IllegalArgumentException("Height must be between 1 and 50 (inclusive)");
        }

        if (width < 1 || width > 50) {
            throw new IllegalArgumentException("With must be between 1 and 50 (inclusive)");
        }

        //
        // Create all the cells and all the walls;
        //
        List<List<Cell>> cells = new ArrayList<>();
        Set<Wall> walls = new HashSet<>();
        Cell lastCell = null;

        for (int y = 0; y < height; y++) {
            List<Cell> cellsRow = new ArrayList<>();

            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(x, y);
                cellsRow.add(cell);

                if (lastCell != null) {
                    walls.add(new Wall(lastCell, cell));
                }

                lastCell = cell;
            }

            cells.add(cellsRow);
        }


        //
        // Now do stuff
        //
        /*
         * Choose the initial cell, mark it as visited and push it to the stack
         * While the stack is not empty
         *      Pop a cell from the stack and make it a current cell
         *      If the current cell has any neighbours which have not been visited
         *          Push the current cell to the stack
         *          Choose one of the unvisited neighbours
         *          Remove the wall between the current cell and the chosen cell
         *          Mark the chosen cell as visited and push it to the stack
         */
        Stack<Cell> stack = new Stack<>();
        Set<Cell> visited = new HashSet<>();
        Cell cell = cells.get(0).get(0);
        visited.add(cell);
        stack.push(cell);

        while (!stack.isEmpty()) {
            cell = stack.pop();

            // Find unvisited neighbors
            Set<Cell> neighbors = getNeighbors(cell, height, width);
            neighbors.removeAll(visited);

            if (neighbors.size() > 0) {
                stack.push(cell);
                Cell neighbor = neighbors.stream().findFirst().get();
                walls.remove(new Wall(cell, neighbor));
                visited.add(neighbor);
                stack.push(neighbor);
            }
        }

        return new Maze(height, width, cells, walls);
    }

    private Set<Cell> getNeighbors(Cell cell, int height, int width) {
        int x = cell.x();
        int y = cell.y();

        Cell[] possibleNeighbors = {
            new Cell(x - 1, y - 1), new Cell(x, y - 1), new Cell(x + 1, y - 1),
            new Cell(x - 1, y    ),                     new Cell(x + 1, y    ),
            new Cell(x - 1, y + 1), new Cell(x, y + 1), new Cell(x + 1, y + 1)
        };

        Set<Cell> neighbors = new HashSet<>();

        for (Cell c: possibleNeighbors) {
            if (c.x() >= 0 && c.x() < width && c.y() >=0 && c.y() < height) {
                neighbors.add(c);
            }
        }

        return neighbors;
    }

}
