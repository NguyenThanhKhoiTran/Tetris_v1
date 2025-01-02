import java.util.ArrayList;
import java.util.List;

public class Action {
    private final int ROW_STACK = 24;
    private final int COL_STACK = 10;

    /****************************************************************
     * Check if a block can move to a new position
     ****************************************************************/
    public boolean move(Block b, Block[][] board, int dx, int dy) {
        int[][] shape = b.getCurrentShape();
        int newX = b.getX() + dx;
        int newY = b.getY() + dy;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int x = newX + col;
                    int y = newY + row;

                    if (x < 0 || x >= COL_STACK || y >= ROW_STACK) {
                        return false;
                    }

                    if (y >= 0 && board[y][x] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*************************************************
     * Place a block on the board
     *************************************************/
    public void place(Block b, Block[][] board) {
        int[][] shape = b.getCurrentShape();
        int x = b.getX();
        int y = b.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    board[y + row][x + col] = b;
                }
            }
        }
    }

    /*************************************************
     * A method to detect the full line and clear it
     * as well as shift the rows down
     *************************************************/
    public void clearLines(Block[][] board, int score) {
        List<Integer> fullLines = new ArrayList<>();

        // Identify full lines
        for (int row = 0; row < ROW_STACK; row++) {
            boolean full = true;
            for (int col = 0; col < COL_STACK; col++) {
                if (board[row][col] == null) {
                    full = false;
                    break;
                }
            }
            if (full) {
                fullLines.add(row);
            }
        }

        // Calculate score based on the number of full lines
        int lineCleared = fullLines.size();

        if (lineCleared == 1) {
            score += 50;
        } else if (lineCleared == 2) {
            score += 100;
        } else if (lineCleared == 3) {
            score += 150;
        } else if (lineCleared >= 4) {
            score += 150 + 200 * (lineCleared - 3);
        }

        // Remove full lines and shift rows down
        for (int row : fullLines) {
            for (int r = row; r > 0; r--) {
                for (int col = 0; col < COL_STACK; col++) {
                    board[r][col] = board[r - 1][col];
                }
            }
        }
    }

    /***********************************
     * Check if the game is over
     ***********************************/
    public boolean gameOver(Block[][] board, Block b) {
        int[][] shape = b.getCurrentShape();
        int x = b.getX();
        int y = b.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = x + col;
                    int boardY = y + row;

                    // Check if the block is above the grid or overlaps existing blocks
                    if (boardY < 0 || (boardY >= 0 && board[boardY][boardX] != null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**********************************
     * A function to spawn a new block
     **********************************/
    public Block spawnBlock(Block[][] board) {
        TetrisShape[] shapes = TetrisShape.values();
        TetrisShape random = shapes[(int) (Math.random() * shapes.length)];

        // Randomly select a shape except the single one (use for other purposes)
        while (random == TetrisShape.SINGLE) {
            random = shapes[(int) (Math.random() * shapes.length)];
        }

        // Create a new block object
        Block newBlock = new Block(random.getCoordinates(), random.getColor());

        // Set the block's initial position at the top center
        int startX = (COL_STACK - newBlock.getCurrentShape()[0].length) / 2;
        newBlock.setX(startX);
        newBlock.setY(0);

        // Check if the game is over
        if (!move(newBlock, board, 0, 0))
            GameBoard.showNameAlert("GAME OVER", "THE BOARD IS FULL!");

        return newBlock;
    }
}
