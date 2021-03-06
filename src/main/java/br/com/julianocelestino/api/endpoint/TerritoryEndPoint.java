package br.com.julianocelestino.api.endpoint;

import br.com.julianocelestino.api.MessageReturn;
import br.com.julianocelestino.api.serializer.TerritorySerializer;
import br.com.julianocelestino.api.serializer.TerritorySerializerWithPainted;
import br.com.julianocelestino.domain.IncompleteDataException;
import br.com.julianocelestino.domain.Territory;
import br.com.julianocelestino.domain.TerritoryOverlaysException;
import br.com.julianocelestino.domain.TerritoryRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.util.Comparator;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class TerritoryEndPoint implements SparkApplication {

    private static final String ORDER_BY_MOST_PAINTED_AREA = "mostPaintedArea";
    private static final String ORDER_BY_MOST_PROPORTIONAL_PAINTED_AREA = "mostProportionalPaintedArea";

    private static final String TERRITORIES_NOT_FOUND = "territories not found";
    private static final String TERRITORY_NOT_FOUND = "territory not found";

    private Boolean withpainted = false;

    @Override
    public void init() {

        get("/territories/:id", (request, response) -> {
            response.type("application/json");
            withpainted = Boolean.valueOf(request.queryParams("withpainted"));
            Long id = Long.valueOf(request.params(":id"));
            Territory territory = new TerritoryRepository().find(id);
            return returnTerritory(territory);
        });

        get("/territories", (request, response) -> {
            response.type("application/json");
            final String order = request.queryParams("order");
            List<Territory> territories = new TerritoryRepository().find();
            if (ORDER_BY_MOST_PROPORTIONAL_PAINTED_AREA.equals(order)) {
                territories.sort(Comparator.comparing(Territory::proportionalPaintedArea).reversed());
            } else if (ORDER_BY_MOST_PAINTED_AREA.equals(order)) {
                territories.sort(Comparator.comparing(Territory::paintedArea).reversed());
            }
            return returnTerritories(territories);
        });

        post("/territories", (request, response) -> {
            response.type("application/json");
            final Territory territory = new Gson().fromJson(request.body(), Territory.class);
            try {
                new TerritoryRepository().create(territory);
                response.status(HttpStatus.CREATED_201);
            } catch (IncompleteDataException | TerritoryOverlaysException e) {
                Spark.halt(HttpStatus.BAD_REQUEST_400, new Gson().toJson(new MessageReturn(e.getMessage())));
            }
            return json(territory);
        });
    }

    private Object returnTerritory(Territory territory) {
        if (territory == null) {
            Spark.halt(HttpStatus.NOT_FOUND_404, new Gson().toJson(new MessageReturn(TERRITORY_NOT_FOUND)));
        }
        return json(territory);
    }

    private Object returnTerritories(List<Territory> territories) {
        if (territories == null || territories.isEmpty()) {
            Spark.halt(HttpStatus.NOT_FOUND_404, new Gson().toJson(new MessageReturn(TERRITORIES_NOT_FOUND)));
        }
        return json(territories);
    }

    private Object json(Object toJson) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (withpainted) {
            gsonBuilder.registerTypeAdapter(Territory.class, new TerritorySerializerWithPainted());
        } else {
            gsonBuilder.registerTypeAdapter(Territory.class, new TerritorySerializer());
        }
        return gsonBuilder.create().toJson(toJson);
    }
}
