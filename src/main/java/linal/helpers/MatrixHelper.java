package linal.helpers;

import com.badlogic.gdx.math.Vector3;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MatrixHelper {

    public static final float ONE_DEGREE_IN_RADIAN = 3.14159f / 180;
    public static final float[][] dickystransform = {
            {0.5f, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
    };

    /**
     * https://www.tutorialandexample.com/3d-shearing
     */
    public static final float[][] shear = {
            {1, 0, 0},
            {0.005f, 1, 0},
            {0.005f, 0, 1},
    };

    public static final float[][] rotateX1Degree = {
            {1, 0, 0},
            {0, (float) cos(ONE_DEGREE_IN_RADIAN), (float) -sin(ONE_DEGREE_IN_RADIAN)},
            {0, (float) sin(ONE_DEGREE_IN_RADIAN), (float) cos(ONE_DEGREE_IN_RADIAN)},
    };

    public static final float[][] rotateY1Degree = {
            {(float) cos(ONE_DEGREE_IN_RADIAN), 0, (float) sin(ONE_DEGREE_IN_RADIAN)},
            {0, 1, 0},
            {(float) -sin(ONE_DEGREE_IN_RADIAN), 0, (float) cos(ONE_DEGREE_IN_RADIAN)},
    };

    public static final float[][] rotateZ1Degree = {
            {(float) cos(ONE_DEGREE_IN_RADIAN), (float) -sin(ONE_DEGREE_IN_RADIAN), 0},
            {(float) sin(ONE_DEGREE_IN_RADIAN), (float) cos(ONE_DEGREE_IN_RADIAN), 0},
            {0, 0, 1}
    };

    public static Vector3 applyV3(float[][] matrix, Vector3 v3) {
        var tmp = v3.cpy();
        v3.x = tmp.x * matrix[0][0] + tmp.y * matrix[0][1] + tmp.z * matrix[0][2];
        v3.y = tmp.x * matrix[1][0] + tmp.y * matrix[1][1] + tmp.z * matrix[1][2];
        v3.z = tmp.x * matrix[2][0] + tmp.y * matrix[2][1] + tmp.z * matrix[2][2];
        return v3;
    }

}