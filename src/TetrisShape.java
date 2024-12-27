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
        }),

        Z1(new int[][] {
                        { 0, 1, 1, 1 },
                        { 1, 1, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 }
        }),

        Z2(new int[][] {
                        { 1, 1, 0 },
                        { 0, 1, 1 },
                        { 0, 0, 0 }
        }),

        Z3(new int[][] {
                        { 1, 1, 1, 0 },
                        { 0, 0, 1, 1 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 }
        }),

        Z4(new int[][] {
                        { 0, 1, 1 },
                        { 1, 1, 0 },
                        { 0, 0, 0 }
        }),

        L1(new int[][] {
                        { 1, 0, 0 },
                        { 1, 1, 1 },
                        { 0, 0, 0 }
        }),

        L2(new int[][] {
                        { 0, 0, 1 },
                        { 1, 1, 1 },
                        { 0, 0, 0 }
        }),

        I1(new int[][] {
                        { 1, 1, 1, 1 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 }
        }),

        I2(new int[][] {
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0 },
        }),

        O(new int[][] {
                        { 1, 1 },
                        { 1, 1 }
        }),

        U(new int[][] {
                        { 1, 0, 1 },
                        { 1, 1, 1 },
                        { 0, 0, 0 }
        }),

        PLUS(new int[][] {
                        { 0, 1, 0 },
                        { 1, 1, 1 },
                        { 0, 1, 0 }
        }),

        /*******
         * #..\*
         * ##.\ *
         * ##.\*
         *******/
        BONUS1(new int[][] {
                        { 1, 0, 0 },
                        { 1, 1, 0 },
                        { 1, 1, 0 }
        }),

        /*******
         * ..#\*
         * ###\*
         * .#.\*
         *******/
        BONUS2(new int[][] {
                        { 0, 0, 1 },
                        { 1, 1, 1 },
                        { 0, 1, 0 }
        }),

        /*******
         * ## \*
         * .# \*
         * .##\*
         *******/
        BONUS3(new int[][] {
                        { 1, 1, 0 },
                        { 0, 1, 0 },
                        { 0, 1, 1 }
        }),

        SINGLE(new int[][] {
                        { 1 }
        });

        private int[][] coordinates;

        TetrisShape(int[][] coordinates) {
                this.coordinates = coordinates;
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
