package vittahealth.hiring.challenge;


import com.google.gson.*;
import vittahealth.hiring.challenge.domain.Territory;

import java.lang.reflect.Type;

public class TerritorySerializer implements JsonSerializer<Territory> {


    @Override
    public JsonElement serialize(Territory territory, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject tree = null;
        try {
            Gson gson = new Gson();
            tree = new JsonObject();
            tree.addProperty("name",territory.getName());
            tree.addProperty("start",territory.getStartArea().toString());
            tree.addProperty("end",territory.getEndArea().toString());
            tree.addProperty("area",territory.area());
            tree.addProperty("paintedArea",territory.getPaintedArea());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tree;
    }
}
