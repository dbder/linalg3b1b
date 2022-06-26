import com.badlogic.gdx.math.Vector3;
import io.javalin.Javalin;

public class RestController {

    private final Main main;

    /**
     * MUST RE_IMPLEMENT
     */
    public RestController(Main main) {
        this.main = main;
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.json("hello there"));

        app.post("/input", ctx -> {
            ctx.status(201);
        });

        app.post("/inputmatrix", ctx -> {
            float[][] arr = ctx.bodyAsClass(float[][].class);
            ctx.status(201);
        });
    }



}




