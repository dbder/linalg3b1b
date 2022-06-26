package linal.visuals;

import com.badlogic.gdx.math.Vector3;
import linal.helpers.MatrixHelper;

public class Matrix {

    public float[][] grid;

    public Matrix() {
        grid = new float[3][3];
    }

    public Matrix(float[][] grid) {
        this.grid = grid;
    }


    public Matrix apply(Matrix matrix) {
        var tmpg = new float[3][3];
        var tmpv = getNthColumn(0);
        MatrixHelper.applyV3(matrix.grid, tmpv);
        writeNthColumn(0, tmpv, tmpg);

        tmpv = getNthColumn(1);
        MatrixHelper.applyV3(matrix.grid, tmpv);
        writeNthColumn(1, tmpv, tmpg);

        tmpv = getNthColumn(2);
        MatrixHelper.applyV3(matrix.grid, tmpv);
        writeNthColumn(2, tmpv, tmpg);
        this.grid = tmpg;
        return this;
    }

    private Vector3 getNthColumn(int n) {
        return new Vector3(grid[0][n], grid[1][n], grid[2][n]);
    }
    private void writeNthColumn(int n, Vector3 vector3, float[][] grid) {
        grid[0][n] = vector3.x;
        grid[1][n] = vector3.y;
        grid[2][n] = vector3.z;
    }

    static Matrix of(float[][] grid) {
        return new Matrix(grid);
    }
}
