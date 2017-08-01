package vittahealth.hiring.challenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;
import spark.servlet.SparkApplication;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;
import vittahealth.hiring.challenge.domain.TerritoryRepository;

import java.util.List;

import static spark.Spark.get;

public class SquareEndPoint implements SparkApplication {



    @Override
    public void init() {
        get("/squares/:x/:y", (request, response) -> {
            response.type("application/json");
            List<Territory> territories = new TerritoryRepository().find();
            int x = Integer.valueOf(request.params(":x"));
            int y = Integer.valueOf(request.params(":y"));
            Territory result = territories.stream()
                    .filter(territory -> territory.belongs(new Node(x,y)))
                    .findAny()
                    .orElse(null);
            return returnSquare(result);
        });
    }

    private Object returnSquare(Territory territory) {
        if (territory == null) {
            Spark.halt(HttpStatus.NOT_FOUND_404, new Gson().toJson(new MessageReturn("this square does not belongs to any territory")));
        }
        return json(territory);
    }

    private Object json(Object toJson) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        SquareSerializer serializer = new SquareSerializer();
        gsonBuilder.registerTypeAdapter(Node.class, serializer);
        return gsonBuilder.create().toJson(toJson);
    }
}
