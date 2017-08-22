package br.com.julianocelestino.api.serializer;


import br.com.julianocelestino.domain.Territory;
import com.google.gson.*;
import br.com.julianocelestino.domain.Node;

import java.lang.reflect.Type;

public class TerritorySerializerWithPainted implements JsonSerializer<Territory> {

    @Override
    public JsonElement serialize(Territory territory, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject tree = null;
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            SquareSerializerNoPaintedAttribute serializer = new SquareSerializerNoPaintedAttribute();
            gsonBuilder.registerTypeAdapter(Node.class, serializer);
            tree = new JsonObject();
            tree.addProperty("id",territory.getId());
            tree.addProperty("name",territory.getName());
            tree.addProperty("start",gsonBuilder.create().toJson((territory.getStartArea())));
            tree.addProperty("end",gsonBuilder.create().toJson((territory.getEndArea())));
            tree.addProperty("area",territory.area());
            tree.addProperty("paintedArea",territory.paintedArea());
            tree.addProperty("paintedSquares",gsonBuilder.create().toJson(territory.paintedSquares()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tree;
    }
}
