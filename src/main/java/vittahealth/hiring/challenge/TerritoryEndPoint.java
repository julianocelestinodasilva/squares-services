package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import spark.Spark;
import spark.servlet.SparkApplication;
import vittahealth.hiring.challenge.domain.Territory;
import vittahealth.hiring.challenge.domain.TerritoryRepository;

import java.util.List;

import static spark.Spark.get;

public class TerritoryEndPoint implements SparkApplication {

    private static final String ORDER_BY_MOST_PAINTED_AREA = "mostPaintedArea";

    private Data data;

    @Override
    public void init() {
        get("/territories", (request, response) -> {
            response.type("application/json");
            final String order = request.queryParams("order");
            if (ORDER_BY_MOST_PAINTED_AREA.equals(order)) {
                List<Territory> territories = new TerritoryRepository().findOrderedByMostPaintedArea();
                return returnTerritories(territories);
            }
            List<Territory> territories = new TerritoryRepository().find();
            return returnTerritories(territories);
        });
    }

    private Object returnTerritories(List<Territory> territories) {
        if (territories == null || territories.size() < 1) {
            data = new Data("territories not found");
            Spark.halt(404, new Gson().toJson(data));
        }
        data = new Data(territories.size(),territories);
        return new Gson().toJson(data);
    }
}
