package info.akang.maze;

public class Main {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGeneratorDepthFirst();
        Maze maze = mazeGenerator.generate(3, 3);

        MazeDisplay mazeDisplay = new MazeDisplaySimple();
        String display = mazeDisplay.display(maze);
        System.out.println(display);
    }
}
