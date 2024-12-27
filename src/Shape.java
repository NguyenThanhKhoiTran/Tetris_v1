/***************************************************************
 * This interface is used for rotating the shape
 * 
 * @author Nguyen Thanh Khoi Tran
 * @date Dec 24th, 2024
 * @version proj_v01
 ***************************************************************/
public interface Shape {
    public int[][] getCoordinates();

    public void rotateClockwise();

    public void rotateCounterClockwise();
}