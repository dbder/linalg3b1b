package linal; /*******************************************************************************
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

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import linal.core.Drawable;
import linal.helpers.CameraHelper;
import linal.helpers.EnvironmentHelper;
import linal.helpers.MatrixHelper;
import linal.network.CommandHandlerManager;
import linal.network.RestController;
import linal.visuals.CoordSystem;
import linal.visuals.Dots;

import java.util.List;

/**
 * linal.Main class
 */
public class Main extends ApplicationAdapter implements Drawable {
    public PerspectiveCamera cam;
    private ModelBatch modelBatch;
    private Environment environment;
    private Dots dots;
    private List<Drawable> drawables;
    public CommandHandlerManager commandHandlerManager;

    @Override
    public void create() {
        modelBatch = new ModelBatch();
        environment = EnvironmentHelper.create();
        cam = CameraHelper.create();
        new RestController(this);

        // drawables
        dots = new Dots();
        drawables = List.of(new CoordSystem(), dots);
        dots.apply(MatrixHelper.shear);

        // fill the commands
        commandHandlerManager = new CommandHandlerManager();
        commandHandlerManager.put("camera.x", (Integer i) -> cam.position.x = i);
        commandHandlerManager.put("camera.x", (Integer i) -> cam.position.x = i);
        commandHandlerManager.put("camera.y", (Integer i) -> cam.position.y = i);
        commandHandlerManager.put("camera.z", (Integer i) -> cam.position.z = i);

    }

    @Override
    public void render() {
        update();
        draw(modelBatch, environment);
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
    }

    /**
     * draws everything kinda recursive
     */
    @Override
    public void draw(ModelBatch batch, Environment environment) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(cam);
        drawables.forEach(d -> d.draw(batch, environment));
        modelBatch.end();
    }

    public void update() {
        commandHandlerManager.update();
        cam.update();
        dots.update(Gdx.graphics.getDeltaTime());
    }
}

