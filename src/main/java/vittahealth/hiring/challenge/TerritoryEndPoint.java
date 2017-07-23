package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.util.List;

import static spark.Spark.get;

public class TerritoryEndPoint implements SparkApplication {

    @Override
    public void init() {
        get("/territories", (request, response) -> {
            TerritoryRepository repository = new TerritoryRepository();
            List<Territory> territories = repository.find();
            if (territories == null) {
                Spark.halt(404, "territories not found");
            }
            return new Gson().toJson(territories);
        });
    }
}
