import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlockView extends Rectangle {
    public BlockView(Block b) {
        super(Block.CELL_SIZE, Block.CELL_SIZE, Color.web("#" + Integer.toHexString(b.getColor())));
        setTranslateX(b.getX() * Block.CELL_SIZE);
        setTranslateY(b.getY() * Block.CELL_SIZE);
    }

    public void update(Block b) {
        setTranslateX(b.getX() * Block.CELL_SIZE);
        setTranslateY(b.getY() * Block.CELL_SIZE);
    }
}