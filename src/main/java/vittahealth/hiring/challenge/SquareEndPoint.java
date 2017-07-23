package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.util.List;

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
            // return new Gson().toJson("Funcionou alteracao");
            SquareRepository squareRepository = new SquareRepository();
            List<Square> squares = squareRepository.find();
            if (squares == null) {
                Spark.halt(401, "Nenhum square encontrado");
            }
            return new Gson().toJson(squares);
        });
    }
}
