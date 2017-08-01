package vittahealth.hiring.challenge.api.endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;
import spark.servlet.SparkApplication;
import vittahealth.hiring.challenge.api.MessageReturn;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;
import vittahealth.hiring.challenge.domain.TerritoryRepository;
import vittahealth.hiring.challenge.api.serializer.SquareSerializer;

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
            Node square = new Node(x,y);
            Territory result = territories.stream()
                    .filter(territory -> territory.belongs(square))
                    .findAny()
                    .orElse(null);
            if (result == null) {
                Spark.halt(HttpStatus.NOT_FOUND_404, new Gson().toJson(new MessageReturn("this square does not belong to any territory")));
            }
            square.painted(result.paintedSquares().contains(square));
            return json(square);
        });
    }

    private Object json(Object toJson) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        SquareSerializer serializer = new SquareSerializer();
        gsonBuilder.registerTypeAdapter(Node.class, serializer);
        return gsonBuilder.create().toJson(toJson);
    }
}
