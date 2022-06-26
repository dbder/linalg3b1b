package matrixmult;

import com.badlogic.gdx.math.Vector3;
import helpers.MatrixHelper;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static helpers.MatrixHelper.*;

class MatrixHelperTest {

    @Test
    void rotatea360X() {
        BufferedImage image = new BufferedImage(150, 150, Image.SCALE_DEFAULT);
        image.setRGB(10, 10, Color.WHITE.getRGB());
        Vector3 v3 = new Vector3(50, 50, 50);

        for (int x = 0; x < 270; x++) {
            v3 = MatrixHelper.applyV3(rotateX1Degree, v3);

            System.out.println(v3);
            image.setRGB(75 + (int) v3.y, 75 + (int) v3.z , Color.WHITE.getRGB());
        }
        try {
            File outputfile = new File("x.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
        }
    }


    @Test
    void rotate360Y() {
        BufferedImage image = new BufferedImage(150, 150, Image.SCALE_DEFAULT);
        image.setRGB(10, 10, Color.WHITE.getRGB());
        Vector3 v3 = new Vector3(50, 50, 50);

        for (int x = 0; x < 270; x++) {
            v3 = MatrixHelper.applyV3(rotateY1Degree, v3);

            System.out.println(v3);
            image.setRGB(75 + (int) v3.x, 75 + (int) v3.z , Color.WHITE.getRGB());
        }
        try {
            File outputfile = new File("y.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
        }
    }


    @Test
    void rotate360Z() {
        BufferedImage image = new BufferedImage(150, 150, Image.SCALE_DEFAULT);
        image.setRGB(10, 10, Color.WHITE.getRGB());
        Vector3 v3 = new Vector3(50, 50, 50);

        for (int x = 0; x < 270; x++) {
            v3 = MatrixHelper.applyV3(rotateZ1Degree, v3);

            System.out.println(v3);
            image.setRGB(75 + (int) v3.x, 75 + (int) v3.y , Color.WHITE.getRGB());
        }
        try {
            File outputfile = new File("z.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
        }
    }


}