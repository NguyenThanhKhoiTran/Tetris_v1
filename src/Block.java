/***************************************************
 * This class is used to create a block object that
 * will be used in the game.
 * 
 * @author Nguyen Thanh Khoi Tran
 * @date Dec 24th, 2024
 * @version proj_v01
 **************************************************/
public class Block {
    // Set the width and height of the block
    public final static int CELL_SIZE = 32;

    // Field to store block properties
    private TetrisShape shape;
    private int x;
    private int y;
    private int color;

    // Constructor
    public Block(TetrisShape shape, int x, int y, int color) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Accessor methods
    public TetrisShape getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public int[][] getCurrentShape() {
        return shape.getCoordinates();
    }

    // Mutator methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShape(TetrisShape shape) {
        this.shape = shape;
    }

    // Method to move the block
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // Method to rotate the block (clockwise)
    public void rotate() {
        shape.rotateClockwise();
    }

    // Method to rotate the block (counter-clockwise)
    public void rotateCounterClockwise() {
        shape.rotateCounterClockwise();
    }
}