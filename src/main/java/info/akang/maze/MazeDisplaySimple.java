package info.akang.maze;

import java.util.ArrayList;
import java.util.List;

public class MazeDisplaySimple implements MazeDisplay {
//    private static final String WALL = "■";
    private static final String WALL = "█";
//    private static final String WALL = "*";
    private static final String OPEN = " ";

    public String display(Maze maze) {
        List<List<String>> grid = createGrid(maze);
        String disp = gridToString(grid);

        return disp;
    }

    private List<List<String>> createGrid (Maze maze) {
        List<List<String>> grid = new ArrayList<>();

        // 1st row wall
        grid.add(createWallRow(maze));

        for (int row = 0; row < maze.getHeight(); row++) {
            List<String> r = new ArrayList<>();

            // 1st col wall
            r.add(WALL);

            for (int col = 0; col < maze.getWidth() - 1; col++) {
                Cell cell = maze.getCells().get(row).get(col);
                r.add(OPEN);

                r.add(maze.wallRight(cell) ? WALL : OPEN);
            }

            // Last col wall
            r.add(OPEN);
            r.add(WALL);
            grid.add(r);
        }

        // Last row wall
        grid.add(createWallRow(maze));

        return grid;
    }

    private List<String> createWallRow(Maze maze) {
        List<String> wallRow = new ArrayList<>();
        for (int col = 0; col < maze.getWidth() * 2 + 1; col++) {
            wallRow.add(WALL);
        }
        return wallRow;
    }

    private String gridToString(List<List<String>> grid) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row <  grid.size(); row++) {
            List<String> r = grid.get(row);
            for (int col = 0; col < r.size(); col++) {
                sb.append(r.get(col));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

//-----
//- - -
//-----
//- - -
//-----

