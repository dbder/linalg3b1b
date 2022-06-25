package matrixmult;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MatrixHelper {


    public static Vector2 matrixToV2(float[][] matrix) {
        return new Vector2(
                matrix[0][0] + matrix[1][0],
                matrix[0][1] + matrix[1][1]
        );
    }

    public static Vector3 matrixToV3(float[][] matrix) {
        return new Vector3(
                matrix[0][0] + matrix[1][0] + matrix[2][0],
                matrix[0][1] + matrix[1][1] + matrix[2][1],
                matrix[0][2] + matrix[1][2] + matrix[2][2]
        );
    }
}
