package vittahealth.hiring.challenge;


import com.google.gson.*;
import vittahealth.hiring.challenge.domain.Node;

import java.lang.reflect.Type;

public class SquareSerializerNoPaintedAttribute implements JsonSerializer<Node> {

    @Override
    public JsonElement serialize(Node node, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject tree = null;
        try {
            Gson gson = new Gson();
            tree = new JsonObject();
            tree.addProperty("x",node.getX());
            tree.addProperty("y",node.getY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tree;
    }
}
