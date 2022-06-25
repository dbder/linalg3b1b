/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import matrixmult.MatrixHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See: http://blog.xoppa.com/basic-3d-using-libgdx-2/
 *
 * @author Xoppa
 */
public class Main implements ApplicationListener {
    public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public Model model;
    public ModelInstance instance;
    public ModelInstance lineInstance;

    public RestController restController;

    public List<ModelInstance> lines = new ArrayList<>();
    public Vector3 newline = null;
    public float[][] transform;
    public float[][] currentTransform;
    public float currentTransformCount;

    private Environment environment;

    private ModelInstance sphere;

    public List<ModelInstance> dots = new ArrayList<>();


    static Map<ModelInstance, Vector2> vmap = new HashMap<>();


    @Override
    public void create() {
        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(300f, 300f, -200f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 1600f;
        cam.update();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));


        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                Usage.Position | Usage.Normal);
        instance = new ModelInstance(model);

        float opacity = 0.1f;
        lines.add(createLine(new Vector3(0, 0, 100), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(0, 0, -100), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(0, 100, 0), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(0, -100, 0), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(100, 0, 0), Color.WHITE, opacity));
        lines.add(createLine(new Vector3(-100, 0, 0), Color.WHITE, opacity));

        restController = new RestController(this);


        int mul = 20;

        for (int x = -mul; x <= mul; x++) {
            for (int y = -mul; y <= mul; y++) {
                Model sphere1 = Helper.sphere(2);
                var mi = new ModelInstance(sphere1);
                mi.transform.translate(new Vector3((float) x * 20, (float) y * 20, 1f));
                dots.add(mi);
            }
        }
    }

    @Override
    public void render() {
        if (newline != null) {
            lines.add(createLine(newline));
            newline = null;
            System.out.println("lines size: " + lines.size());
        }

        if (transform != null) {
            currentTransform = transform;
            vmap = new HashMap<>();
            dots.forEach(d -> fillTranslation(d, currentTransform));

            currentTransformCount = 0;
            transform = null;
        }

        if (currentTransform != null) {
            float dt = Gdx.graphics.getDeltaTime();
            currentTransformCount += (dt);
            if (currentTransformCount < 1) {
                for (var d : dots) {
                    var tl = d.transform.getTranslation(new Vector3());
                    tl.x += vmap.get(d).x * dt;
                    tl.y += vmap.get(d).y * dt;
                    d.transform.setTranslation(tl);
                }
            }
        }


        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        modelBatch.begin(cam);
        lines.forEach(l -> modelBatch.render(l, environment));
        for (ModelInstance dot : dots) {
            modelBatch.render(dot, environment);
        }
//        modelBatch.render(sphere, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    static Vector3 origin = new Vector3(0, 0, 0);


    private static ModelInstance createLine(Vector3 vector3) {
        return createLine(vector3, Color.RED);
    }

    private static ModelInstance createLine(Vector3 vector3, Color color) {
        return createLine(vector3, color, 1);
    }

    private static ModelInstance createLine(Vector3 vector3, Color color, float opacity) {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        MeshPartBuilder builder = modelBuilder.part("line", 1, 3, new Material());
        builder.setColor(color);
        builder.line(origin, vector3);
        Model end = modelBuilder.end();
        ModelInstance instance1 = new ModelInstance(end);

        BlendingAttribute blendingAttribute = new BlendingAttribute(GL20.GL_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        instance1.materials.get(0).set(blendingAttribute);
        blendingAttribute.opacity = opacity;

        return instance1;
    }


    static void fillTranslation(ModelInstance model, float[][] matrix) {
        Vector3 translation = model.transform.getTranslation(new Vector3());
        var v2 = MatrixHelper.matrixToV2(matrix);
        float tx = -translation.x + translation.x * v2.x;
        float ty = -translation.y + translation.y * v2.y;
        vmap.put(model, new Vector2(tx, ty));
    }
}

