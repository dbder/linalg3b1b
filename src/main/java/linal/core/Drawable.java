package linal.core;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public interface Drawable {
    void draw(ModelBatch batch, Environment environment);
}
