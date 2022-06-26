package linal.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class ShapeHelper {
    public static final Vector3 ORIGIN = new Vector3(0, 0, 0);
    private static ModelInstance createLine(Vector3 vector3) {
        return createLine(vector3, Color.RED);
    }
    private static ModelInstance createLine(Vector3 vector3, Color color) {
        return createLine(vector3, color, 1);
    }
    public static ModelInstance createLine(Vector3 vector3, Color color, float opacity) {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        MeshPartBuilder builder = modelBuilder.part("line", 1, 3, new Material());
        builder.setColor(color);
        builder.line(ORIGIN, vector3);
        Model end = modelBuilder.end();
        ModelInstance instance1 = new ModelInstance(end);

        BlendingAttribute blendingAttribute = new BlendingAttribute(GL20.GL_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        instance1.materials.get(0).set(blendingAttribute);
        blendingAttribute.opacity = opacity;

        return instance1;
    }

    public static Model createSphere(float diameter) {
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
}
