public class Main {
    public static void main(String[] args) {
        TetrisShape shape = TetrisShape.I2;

        System.out.println("Original Shape:");
        printShape(shape.getCoordinates());

        shape.rotateCounterClockwise();
        printShape(shape.getCoordinates());

        shape.rotateCounterClockwise();
        printShape(shape.getCoordinates());

        shape.rotateCounterClockwise();
        printShape(shape.getCoordinates());

        shape.rotateCounterClockwise();
        printShape(shape.getCoordinates());

        shape.rotateCounterClockwise();
        printShape(shape.getCoordinates());
    }

    public static void printShape(int[][] shape) {
        for (int[] row : shape) {
            for (int cell : row) {
                System.out.print((cell == 0 ? "." : "#") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
