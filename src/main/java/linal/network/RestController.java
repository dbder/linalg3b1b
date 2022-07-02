package linal.network;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.http.HttpCode;
import io.javalin.http.staticfiles.Location;
import linal.Main;

public class RestController {

    private final Main main;

    /**
     * MUST RE_IMPLEMENT
     */
    public RestController(Main main) {
        this.main = main;

        JavalinConfig config = new JavalinConfig();

        Javalin app = Javalin
                .create(c -> c.addStaticFiles(
                        "/home/d/repos/gdx/linalg3b1b/src/main/resources",
                        Location.EXTERNAL)
                )
                .start(7000);

        app.get("/", ctx -> ctx.json("hello there"));

        app.post("/input", ctx -> {
            ctx.status(201);
        });

        app.post("/settings", c -> {
            var p = c.bodyAsClass(Pair.class);
            var command = main.commandHandlerManager.consumerMap.get(p.key());
            main.commandHandlerManager.queueCommand(p.value(), command);
            c.status(HttpCode.OK);
        });

        app.post("/inputmatrix", ctx -> {
            float[][] arr = ctx.bodyAsClass(float[][].class);
            ctx.status(201);
        });
    }


}




