package linal.visuals;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import linal.core.Drawable;
import linal.helpers.MatrixHelper;

public class Dot implements Drawable {

    private final ModelInstance modelInstance;

    public Dot(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }

    @Override
    public void draw(ModelBatch batch, Environment environment) {
        batch.render(modelInstance, environment);
    }

    public void apply(float[][] matrix) {
        var tl = modelInstance.transform.getTranslation(new Vector3());
        var res = MatrixHelper.applyV3(matrix, tl);
        modelInstance.transform.setTranslation(res);
    }
}
