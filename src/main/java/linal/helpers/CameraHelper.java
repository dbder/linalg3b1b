package linal.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class CameraHelper {

    public static PerspectiveCamera create() {
        PerspectiveCamera cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(300f, 300f, -200f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 1600f;
        cam.update();
        return cam;
    }
}
