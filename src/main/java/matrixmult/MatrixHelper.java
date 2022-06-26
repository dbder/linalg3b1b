package matrixmult;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MatrixHelper {

    public static final float oneDegreeInRadian = 3.14159f / 180;

    public static final float[][] rotateX1Degree = {
            {1, 0, 0},
            {0, (float) cos(oneDegreeInRadian), (float) -sin(oneDegreeInRadian)},
            {0, (float) sin(oneDegreeInRadian), (float) cos(oneDegreeInRadian)},
    };

    public static final float[][] rotateY1Degree = {
            {(float) cos(oneDegreeInRadian), 0, (float) sin(oneDegreeInRadian)},
            {0, 1, 0},
            {(float) -sin(oneDegreeInRadian), 0, (float) cos(oneDegreeInRadian)},
    };

    public static final float[][] rotateZ1Degree = {
            {(float) cos(oneDegreeInRadian), (float) -sin(oneDegreeInRadian), 0},
            {(float) sin(oneDegreeInRadian), (float) cos(oneDegreeInRadian), 0},
            {0, 0, 1}
    };


    public static Vector2 matrixToV2(float[][] matrix) {
        return new Vector2(
                matrix[0][0] + matrix[1][0],
                matrix[0][1] + matrix[1][1]
        );
    }

    public static Vector3 applyV3(float[][] matrix, Vector3 v3) {
        var res = v3.cpy();
        res.x = v3.x * matrix[0][0] + v3.y * matrix[0][1] + v3.z * matrix[0][2];
        res.y = v3.x * matrix[1][0] + v3.y * matrix[1][1] + v3.z * matrix[1][2];
        res.z = v3.x * matrix[2][0] + v3.y * matrix[2][1] + v3.z * matrix[2][2];
        return res;
    }
}
