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

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import helpers.CameraHelper;
import helpers.EnvironmentHelper;
import visuals.CoordSystem;
import visuals.Dots;

/**
 * Main class
 */
public class Main extends ApplicationAdapter {
    public PerspectiveCamera cam;
    private ModelBatch modelBatch;
    public RestController restController;
    private Environment environment;
    private CoordSystem coordSystem;
    private Dots dots;


    @Override
    public void create() {
        modelBatch = new ModelBatch();
        environment = EnvironmentHelper.create();
        cam = CameraHelper.create();
        restController = new RestController(this);

        // drawables
        coordSystem = new CoordSystem();
        dots = new Dots();
    }

    @Override
    public void render() {

        //update
        dots.update(Gdx.graphics.getDeltaTime());

        //render
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(cam);
        coordSystem.draw(modelBatch, environment);
        dots.draw(modelBatch, environment);
        modelBatch.end();

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
    }

}

