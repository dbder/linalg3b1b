import com.badlogic.gdx.math.Vector3;
import io.javalin.Javalin;

public class RestController {

    private final Main main;

    public RestController(Main main) {
        this.main = main;
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.json("hello there"));
        app.post("/input", ctx -> {
            main.newline = ctx.bodyAsClass(Vector3.class);
            ctx.status(201);
        });

    }



}




