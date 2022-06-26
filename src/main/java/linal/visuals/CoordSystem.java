package linal.visuals;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import linal.core.Drawable;

import java.util.ArrayList;
import java.util.List;

import static linal.helpers.ShapeHelper.createLine;

public class CoordSystem implements Drawable {

    public List<ModelInstance> lines = new ArrayList<>();

    public CoordSystem() {
        float opacity = 1;
        lines.add(createLine(new Vector3(0, 0, 100), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(0, 0, -100), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(0, 100, 0), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(0, -100, 0), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(100, 0, 0), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(-100, 0, 0), Color.WHITE, opacity));
    }

    @Override
    public void draw(ModelBatch batch, Environment environment) {
        lines.forEach(l -> batch.render(l, environment));
    }
}
