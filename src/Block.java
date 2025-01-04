import javafx.scene.paint.Color;

/***************************************************
 * This class is used to create a block object that
 * will be used in the game.
 * 
 * @author Nguyen Thanh Khoi Tran
 * @date Dec 24th, 2024
 * @version proj_v01
 **************************************************/
public class Block {
    // Field to store block properties
    private int[][] shape;
    private int x, y;
    private Color c;

    // Constructor
    public Block(int[][] shape, Color c) {
        this.shape = shape;
        this.c = c;
        this.x = 0;
        this.y = 0;
    }

    // Accessor methods
    public int[][] getCurrentShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return c;
    }

    // Mutator methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }
}