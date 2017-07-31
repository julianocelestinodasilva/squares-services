package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;
import spark.servlet.SparkApplication;
import vittahealth.hiring.challenge.domain.IncompleteDataException;
import vittahealth.hiring.challenge.domain.Territory;
import vittahealth.hiring.challenge.domain.TerritoryOverlaysException;
import vittahealth.hiring.challenge.domain.TerritoryRepository;

import java.util.Comparator;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class TerritoryEndPoint implements SparkApplication {

    private static final String ORDER_BY_MOST_PAINTED_AREA = "mostPaintedArea";
    private static final String ORDER_BY_MOST_PROPORTIONAL_PAINTED_AREA = "mostProportionalPaintedArea";

    private static final String TERRITORIES_NOT_FOUND = "territories not found";

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
        post("/territories", (request, response) -> {
            response.type("application/json");
            final String body = request.body();
            Gson gson = new Gson();
            final Territory territory = gson.fromJson(body, Territory.class);
            try {
                new TerritoryRepository().create(territory);
                response.status(HttpStatus.CREATED_201);
            } catch (IncompleteDataException | TerritoryOverlaysException e) {
                Spark.halt(HttpStatus.BAD_REQUEST_400, new Gson().toJson(new MessageReturn(e.getMessage())));
            }
            return json(territory);
        });
    }

    private Object returnTerritories(List<Territory> territories) {
        if (territories == null || territories.isEmpty()) {
            Spark.halt(HttpStatus.NOT_FOUND_404, new Gson().toJson(new MessageReturn(TERRITORIES_NOT_FOUND)));
        }
        return json(territories);
    }

    private Object json(Object toJson) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Territory.class, new TerritorySerializer());
        return gsonBuilder.create().toJson(toJson);
    }
}
