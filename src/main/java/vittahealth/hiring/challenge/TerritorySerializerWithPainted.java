package vittahealth.hiring.challenge;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import vittahealth.hiring.challenge.domain.Territory;

import java.lang.reflect.Type;

public class TerritorySerializerWithPainted extends TerritorySerializer {

    @Override
    public JsonElement serialize(Territory territory, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject tree = null;
        try {
            Gson gson = new Gson();
            tree = new JsonObject();
            tree.addProperty("id",territory.getId());
            tree.addProperty("name",territory.getName());
            tree.addProperty("start",new Gson().toJson(territory.getStartArea()));
            tree.addProperty("end",new Gson().toJson(territory.getEndArea()));
            tree.addProperty("area",territory.area());
            tree.addProperty("paintedArea",territory.paintedArea());
            tree.addProperty("paintedSquares",new Gson().toJson(territory.paintedSquares()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tree;
    }
}
