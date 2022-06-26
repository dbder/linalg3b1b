package matrixmult;

import com.badlogic.gdx.math.Vector3;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static matrixmult.MatrixHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixHelperTest {

    @Test
    void testMAtrixToV2() {
        var matrix = new float[][]{
                {1, 2},
                {5, 7}
        };
        var v2 = matrixToV2(matrix);
        assertEquals(6, v2.x);
        assertEquals(9, v2.y);
    }

//    @Test
//    void testMatrixToV3() {
//        var matrix = new float[][]{
//                {1, 2, 3},
//                {5, 7, 9},
//                {11, 13, 17}
//        };
//        var v3 = applyV3(matrix);
//        assertEquals(17, v3.x);
//        assertEquals(22, v3.y);
//        assertEquals(29, v3.z);
//    }

    @Test
    void rotate360X() {
        BufferedImage image = new BufferedImage(300, 300, Image.SCALE_DEFAULT);
        image.setRGB(10, 10, Color.WHITE.getRGB());
        Vector3 v3 = new Vector3(50, 50, 0);

        for (int x = 0; x < 270; x++) {
            MatrixHelper.applyV3(rotateX1Degree, v3);

            System.out.println(v3);
            image.setRGB(150 + (int) v3.y, 150 + (int) v3.z , Color.WHITE.getRGB());
        }
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
        }
    }
    @Test
    void rotate360Y() {
        BufferedImage image = new BufferedImage(300, 300, Image.SCALE_DEFAULT);
        image.setRGB(10, 10, Color.WHITE.getRGB());
        Vector3 v3 = new Vector3(50, 50, 50);

        for (int x = 0; x < 270; x++) {
            MatrixHelper.applyV3(rotateY1Degree, v3);

            System.out.println(v3);
            image.setRGB(150 + (int) v3.x, 150 + (int) v3.y , Color.WHITE.getRGB());
        }
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
        }
    }

    @Test
    void rotate360Z() {
        BufferedImage image = new BufferedImage(110, 110, Image.SCALE_DEFAULT);
        Vector3 v3 = new Vector3(50, 50, 50);

        for (int x = 0; x < 270; x++) {
            MatrixHelper.applyV3(rotateZ1Degree, v3);

            System.out.println(v3);
            image.setRGB(51 + (int) v3.x, 51 + (int) v3.y , Color.WHITE.getRGB());
            image.setRGB(50 + (int) v3.x, 50 + (int) v3.y , Color.WHITE.getRGB());
            image.setRGB(49 + (int) v3.x, 49 + (int) v3.y , Color.WHITE.getRGB());
        }
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
        }
    }

}