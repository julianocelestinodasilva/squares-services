package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class SquareEndPoint implements SparkApplication {



    @Override
    public void init() {

        get("/squares/:x/:y", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson("VAIIII");
        });


    }
}
