package vittahealth.hiring.challenge.api.endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Spark;
import spark.servlet.SparkApplication;
import vittahealth.hiring.challenge.api.MessageReturn;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;
import vittahealth.hiring.challenge.domain.TerritoryRepository;
import vittahealth.hiring.challenge.api.serializer.SquareSerializer;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.patch;

public class SquareEndPoint implements SparkApplication {


    private TerritoryRepository territoryRepository;

    @Override
    public void init() {

        territoryRepository = new TerritoryRepository();

        patch("/squares/:x/:y/paint", (request, response) -> {
            response.type("application/json");
            Node squareToPaint = getSquare(request);
            Territory territorySquare = getTerritory(squareToPaint);
            territorySquare.paint(squareToPaint);
            territoryRepository.update(territorySquare);
            return json(squareToPaint);
        });

        get("/squares/:x/:y", (request, response) -> {
            response.type("application/json");
            Node square = getSquare(request);
            Territory territorySquare = getTerritory(square);
            square.painted(territorySquare.paintedSquares().contains(square));
            return json(square);
        });
    }

    private Territory getTerritory(Node square) {
        List<Territory> territories = territoryRepository.find();
        Territory territorySquare = getTerritorySquare(square, territories);
        if (territorySquare == null) {
            Spark.halt(HttpStatus.NOT_FOUND_404, new Gson().toJson(new MessageReturn("this square does not belong to any territory")));
        }
        return territorySquare;
    }

    private Territory getTerritorySquare(Node square, List<Territory> territories) {
        return territories.stream()
                        .filter(territory -> territory.belongs(square))
                        .findAny()
                        .orElse(null);
    }

    private Node getSquare(Request request) {
        int x = Integer.valueOf(request.params(":x"));
        int y = Integer.valueOf(request.params(":y"));
        return new Node(x,y);
    }

    private Object json(Object toJson) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        SquareSerializer serializer = new SquareSerializer();
        gsonBuilder.registerTypeAdapter(Node.class, serializer);
        return gsonBuilder.create().toJson(toJson);
    }
}
