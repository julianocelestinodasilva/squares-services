package vittahealth.hiring.challenge.api.serializer;


import com.google.gson.*;
import vittahealth.hiring.challenge.domain.Node;

import java.lang.reflect.Type;

public class SquareSerializer implements JsonSerializer<Node> {

    @Override
    public JsonElement serialize(Node node, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject tree = null;
        try {
            Gson gson = new Gson();
            tree = new JsonObject();
            tree.addProperty("x",node.getX());
            tree.addProperty("y",node.getY());
            tree.addProperty("painted",node.isPainted());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tree;
    }
}
