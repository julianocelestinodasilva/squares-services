package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class SquareEndPoint implements SparkApplication {

    @Override
    public void init() {

        /*post("/authenticate", (request, response) -> {
            Square u = new Gson().fromJson(request.body(), Square.class);
            SquareServiceImpl service = new SquareServiceImpl();
            Square square = service.buscarUsuario(u.getName(), u.getPassword());
            if (square == null) {
                Spark.halt(401, "Square Invalido");
            }
            return new Gson().toJson(square);
        });*/

        get("/teste", (request, response) -> {
            return new Gson().toJson("Funcionou alteracao");
        });
    }
}
