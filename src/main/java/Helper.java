import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;

public class Helper {

    public static Model sphere(float diameter) {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createSphere(
                diameter,
                diameter,
                diameter,
                30,
                30,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
        );
    }




    static void print(int[][] arr) {
        for (var a : arr ) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
    static void print(float[][] arr) {
        for (var a : arr ) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

}
