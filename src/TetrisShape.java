import javafx.scene.paint.Color;

/****************************************************
 * This enumeratation defines the shape of the block,
 * including support for rotation
 * 
 * @author Nguyen Thanh Khoi Tran
 * @date Dec 24th, 2024
 * @version proj_v01
 ****************************************************/
public enum TetrisShape implements Shape {
        T(new int[][] {
                        { 0, 1, 0 },
                        { 1, 1, 1 },
                        { 0, 0, 0 }
        }, Color.CYAN),

        Z1(new int[][] {
                        { 0, 1, 1, 1 },
                        { 1, 1, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 }
        }, Color.YELLOW),

        Z2(new int[][] {
                        { 1, 1, 0 },
                        { 0, 1, 1 },
                        { 0, 0, 0 }
        }, Color.PURPLE),

        Z3(new int[][] {
                        { 1, 1, 1, 0 },
                        { 0, 0, 1, 1 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 }
        }, Color.GREEN),

        Z4(new int[][] {
                        { 0, 1, 1 },
                        { 1, 1, 0 },
                        { 0, 0, 0 }
        }, Color.RED),

        L1(new int[][] {
                        { 1, 0, 0 },
                        { 1, 1, 1 },
                        { 0, 0, 0 }
        }, Color.BLUE),

        L2(new int[][] {
                        { 0, 0, 1 },
                        { 1, 1, 1 },
                        { 0, 0, 0 }
        }, Color.ORANGE),

        I1(new int[][] {
                        { 1, 1, 1, 1 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 }
        }, Color.PINK),

        I2(new int[][] {
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
        }, Color.LIGHTBLUE),

        O(new int[][] {
                        { 1, 1 },
                        { 1, 1 }
        }, Color.LIGHTGREEN),

        U(new int[][] {
                        { 1, 0, 1 },
                        { 1, 1, 1 },
                        { 0, 0, 0 }
        }, Color.LIGHTYELLOW),

        PLUS(new int[][] {
                        { 0, 1, 0 },
                        { 1, 1, 1 },
                        { 0, 1, 0 }
        }, Color.LIGHTCORAL),

        /*******
         * #..\*
         * ##.\ *
         * ##.\*
         *******/
        BONUS1(new int[][] {
                        { 1, 0, 0 },
                        { 1, 1, 0 },
                        { 1, 1, 0 }
        }, Color.LIGHTSALMON),

        /*******
         * ..#\*
         * ###\*
         * .#.\*
         *******/
        BONUS2(new int[][] {
                        { 0, 0, 1 },
                        { 1, 1, 1 },
                        { 0, 1, 0 }
        }, Color.LIGHTSKYBLUE),

        /*******
         * ## \*
         * .# \*
         * .##\*
         *******/
        BONUS3(new int[][] {
                        { 1, 1, 0 },
                        { 0, 1, 0 },
                        { 0, 1, 1 }
        }, Color.LIGHTSTEELBLUE),

        SINGLE(new int[][] {
                        { 1 }
        }, Color.BLACK);

        private int[][] coordinates;
        private Color c;

        TetrisShape(int[][] coordinates, Color c) {
                this.coordinates = coordinates;
                this.c = c;
        }

        public Color getColor() {
                return c;
        }

        public int[][] getCoordinates() {
                return coordinates;
        }

        public void rotateClockwise() {
                coordinates = rotateClockwise(coordinates);
        }

        // Helper method to rotate the shape clockwise
        private static int[][] rotateClockwise(int[][] matrix) {
                int r = matrix.length;
                int c = matrix[0].length;
                int[][] result = new int[c][r];
                for (int i = 0; i < r; i++) {
                        for (int j = 0; j < c; j++) {
                                result[j][r - 1 - i] = matrix[i][j];
                        }
                }
                return result;
        }

        public void rotateCounterClockwise() {
                coordinates = rotateCounterClockwise(coordinates);
        }

        // Helper method to rotate the shape counter-clockwise
        private static int[][] rotateCounterClockwise(int[][] matrix) {
                int r = matrix.length;
                int c = matrix[0].length;
                int[][] result = new int[c][r];
                for (int i = 0; i < r; i++) {
                        for (int j = 0; j < c; j++) {
                                result[c - 1 - j][i] = matrix[i][j];
                        }
                }
                return result;
        }
}
