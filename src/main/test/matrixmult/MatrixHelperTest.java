package matrixmult;

import org.junit.jupiter.api.Test;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static matrixmult.MatrixHelper.matrixToV2;
import static matrixmult.MatrixHelper.matrixToV3;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixHelperTest {

    static final double[][] rotateX1Degree = {
            {1, 0, 0},
            {0, cos(1), -sin(1)},
            {},
    };


    @Test
    void test() {
        var matrix = new float[][]{
                {1, 2},
                {5, 7}
        };
        var v2 = matrixToV2(matrix);
        assertEquals(6, v2.x);
        assertEquals(9, v2.y);
    }

    @Test
    void testMatrixToV3() {
        var matrix = new float[][]{
                {1, 2, 3},
                {5, 7, 9},
                {11, 13, 17}
        };
        var v3 = matrixToV3(matrix);
        assertEquals(17, v3.x);
        assertEquals(22, v3.y);
        assertEquals(29, v3.z);
    }

}