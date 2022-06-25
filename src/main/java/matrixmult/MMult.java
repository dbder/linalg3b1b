package matrixmult;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MMult {

    static Map<Sphere2D, Vector2> vmap = new HashMap<>();

    public static void main(String[] args) {
        var spheres = Arrays.asList(
                new Sphere2D(1, 0, 0),
                new Sphere2D(1, 2, 2)
        );
        float[][] matrix = {{0, -1}, {1, 0}};
        System.out.println(spheres);
        spheres.forEach(s -> fillTranslation(s, matrix));
        System.out.println(vmap);
        System.out.println("start");
        for (int x = 1; x < 10; x++) {
            spheres.forEach(s -> apply(s, 0.1f));
            System.out.println(spheres);
        }
        System.out.println(spheres);
    }

    public static void apply(Sphere2D sphere2D, float dt) {
        sphere2D.x += vmap.get(sphere2D).x * dt;
        sphere2D.y += vmap.get(sphere2D).y * dt;
    }

    static void fillTranslation(Sphere2D sphere2D, float[][] matrix) {
        float tx = -sphere2D.x + sphere2D.x * (matrix[0][0] + matrix[1][0]);
        float ty = -sphere2D.y + sphere2D.y * (matrix[0][1] + matrix[1][1]);
        vmap.put(sphere2D, new Vector2(tx, ty));
    }
}

class Sphere2D {
    public Sphere2D(int id, float x, float y) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public float x;
    public float y;
    public int id;

    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + ", id=" + id + '}';
    }

}