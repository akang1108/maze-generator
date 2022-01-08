package info.akang.maze;

public class MazeGeneratorOptions {
    private int height;
    private int width;

    public MazeGeneratorOptions(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
