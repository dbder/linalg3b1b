package linal.visuals;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import linal.core.Drawable;
import linal.helpers.ShapeHelper;
import linal.helpers.MatrixHelper;

import java.util.ArrayList;
import java.util.List;

public class Dots implements Drawable {

    public List<ModelInstance> dots = new ArrayList<>();

    public Dots()  {
        int mul = 10;
        // create a lot of dots !
        for (int x = -mul; x <= mul; x++) {
            for (int y = -mul; y <= mul; y++) {
                Model sphere1 = ShapeHelper.createSphere(2);
                var mi = new ModelInstance(sphere1);
                mi.transform.translate(new Vector3((float) x * 20, (float) y * 20, 1f));
                dots.add(mi);
            }
        }
    }

    @Override
    public void draw(ModelBatch batch, Environment environment) {
        for (ModelInstance dot : dots) {
            batch.render(dot, environment);
        }
    }

    public void update(float dt) {
        apply(MatrixHelper.rotateX1Degree);
        apply(MatrixHelper.rotateY1Degree);
        apply(MatrixHelper.rotateZ1Degree);
        apply(MatrixHelper.rotateZ1Degree);
    }

    private void apply(float[][] matrix) {
        dots.forEach(d -> {
            var tl = d.transform.getTranslation(new Vector3());
            var res = MatrixHelper.applyV3(matrix, tl);
            d.transform.setTranslation(res);
        });
    }
}
