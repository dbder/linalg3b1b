package linal.visuals;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import linal.core.Drawable;
import linal.core.Updatable;
import linal.helpers.ShapeHelper;
import linal.helpers.MatrixHelper;

import java.util.ArrayList;
import java.util.List;

import static linal.helpers.MatrixHelper.*;

public class Dots implements Drawable, Updatable {

    public List<Dot> dots = new ArrayList<>();
    private static final float SPACING = 30f;
    private static final int XMUL = 1;
    private static final int YMUL = 10;
    private static final int ZMUL = 3;


    public Dots() {
        int mul = 3;
        // create a lot of dots !
        for (int x = -XMUL; x <= XMUL; x++) {
            for (int y = -YMUL; y <= YMUL; y++) {
                for (int z = -ZMUL; z <= ZMUL; z++) {
                    Model sphere1 = ShapeHelper.createSphere(2);
                    var mi = new ModelInstance(sphere1);
                    mi.transform.translate(new Vector3(SPACING * x, SPACING * y, SPACING * z));
                    dots.add(new Dot(mi));
                }
            }
        }
    }

    @Override
    public void draw(ModelBatch batch, Environment environment) {
        dots.forEach(d -> d.draw(batch, environment));
    }

    @Override
    public void update(float dt) {
        apply(Matrix.of(rotateX1Degree).apply(Matrix.of(rotateY1Degree)).apply(Matrix.of(rotateZ1Degree)).grid);
//        apply(rotateX1Degree);
//        apply(rotateY1Degree);
//        apply(rotateZ1Degree);
//        apply(rotateZ1Degree);
//        apply(MatrixHelper.shear);
    }

    public void apply(float[][] matrix) {
        dots.forEach(d -> d.apply(matrix));
    }
}
