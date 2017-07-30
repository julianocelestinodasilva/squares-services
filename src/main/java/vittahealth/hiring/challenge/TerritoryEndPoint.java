package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Spark;
import spark.servlet.SparkApplication;
import vittahealth.hiring.challenge.domain.Territory;
import vittahealth.hiring.challenge.domain.TerritoryRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static spark.Spark.get;

public class TerritoryEndPoint implements SparkApplication {

    private static final String ORDER_BY_MOST_PAINTED_AREA = "mostPaintedArea";
    private static final String ORDER_BY_MOST_PROPORTIONAL_PAINTED_AREA = "mostProportionalPaintedArea";


    @Override
    public void init() {
        get("/territories", (request, response) -> {
            response.type("application/json");
            final String order = request.queryParams("order");
            if (ORDER_BY_MOST_PAINTED_AREA.equals(order)) {
                return returnTerritories(new TerritoryRepository().findOrderedByMostPaintedArea());
            }
            List<Territory> territories = new TerritoryRepository().find();
            if (ORDER_BY_MOST_PROPORTIONAL_PAINTED_AREA.equals(order)) {
                territories.sort(Comparator.comparing(Territory::proportionalPaintedArea).reversed());
            }
            return returnTerritories(territories);
        });
    }

    private Object returnTerritories(List<Territory> territories) {
        if (territories == null || territories.size() < 1) {
            Spark.halt(404, new Gson().toJson(new MessageReturn("territories not found")));
        }
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Territory.class, new TerritorySerializer());
        final Gson parser = gson.create();
        return parser.toJson(territories);
    }
}
