package vittahealth.hiring.challenge.acceptancetests;


import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class TerritoryEndPointTest {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String url;

    private Territory territory0;
    private Territory territory1;
    private List<Territory> territories;

    @Before
    public void setUp() throws Exception {
        url = URLApi.territories();
    }

    @Test
    public void get_territory_by_id_withpainted() throws Exception {

        Territory territory = new Territory("A",new Node(0,0),new Node(50,50));
        DataBase.persistTerritory(territory);

        List<Node> expectedPaintedSquaresList = new ArrayList<Node>();
        expectedPaintedSquaresList.add(new Node(1,2));
        expectedPaintedSquaresList.add(new Node(2,3));

        url +=  "/1?withPainted=true";

        logger.log(Level.INFO, url);
        expect().statusCode(200).
                body("id", equalTo(Long.valueOf(territory.getId()).intValue())).
                body("name", equalTo(territory.getName())).
                body("start", equalTo(new Gson().toJson(territory.getStartArea()))).
                body("end", equalTo(new Gson().toJson(territory.getEndArea()))).
                body("area", equalTo(Long.valueOf(territory.area()).intValue())).
                body("paintedArea", equalTo(expectedPaintedSquaresList.size())).
                /*body("paintedSquares.get(0)", equalTo(expectedPaintedSquaresList.get(0))).
                body("paintedSquares.get(1)", equalTo(expectedPaintedSquaresList.get(1))).*/
                when().get(url);
    }

    @Test
    public void should_return_error_incomplete_data() throws Exception {
        Territory territoryToCreate = new Territory();
        Response response = given().contentType("application/json").and().body(new Gson().toJson(territoryToCreate)).post(url);
        assertError(response, 400, "misses the start, end or name fields!");
        territoryToCreate = new Territory("",null,null);
        response = given().contentType("application/json").and().body(new Gson().toJson(territoryToCreate)).post(url);
        assertError(response, 400, "misses the start, end or name fields!");
        territoryToCreate = new Territory(null,new Node(),null);
        response = given().contentType("application/json").and().body(new Gson().toJson(territoryToCreate)).post(url);
        assertError(response, 400, "misses the start, end or name fields!");
        territoryToCreate = new Territory(null,null,new Node());
        response = given().contentType("application/json").and().body(new Gson().toJson(territoryToCreate)).post(url);
        assertError(response, 400, "misses the start, end or name fields!");
    }

    @Test
    public void should_return_error_territory_overlay() throws Exception {
        DataBase.persistTerritory(new Territory("A",new Node(10,10),new Node(50,50)));
        Territory territoryToCreate = new Territory("B",new Node(10,10),new Node(50,50));
        Response response = given().contentType("application/json").and().body(new Gson().toJson(territoryToCreate)).post(url);
        assertError(response, 400, "this new territory overlays another territory");
    }

    @Test
    public void should_add_a_territory() throws Exception {
        DataBase.deleteTerritories();
        logger.log(Level.INFO, url);
        Territory territoryToCreate = new Territory("A",new Node(0,0),new Node(50,50));
        Response response = given().contentType("application/json").and().body(new Gson().toJson(territoryToCreate)).post(url);
        assertEquals(201,response.getStatusCode());
        final JsonPath jsonResponse = response.jsonPath();
        assertEquals(1,jsonResponse.getLong("id"));
        assertEquals(territoryToCreate.getName(),jsonResponse.getString("name"));
        assertEquals(new Gson().toJson(territoryToCreate.getStartArea()),jsonResponse.get("start"));
        assertEquals(new Gson().toJson(territoryToCreate.getEndArea()),jsonResponse.get("end"));
        assertEquals(territoryToCreate.area(),jsonResponse.getLong("area"));
    }

    @Test
    public void list_territories_ordered_by_most_proportional_painted_area() throws Exception {
        territory0 = new Territory("A",new Node(0,0),new Node(10,10));
        territory0.setPaintedArea(95L);
        territory1 = new Territory("B",new Node(0,0),new Node(50,50));
        territory1.setPaintedArea(100L);
        territories = DataBase.persistTerritories(territory1,territory0);
        url +=  "?order=mostProportionalPaintedArea";
        expectTerritoryZeroFirst();
    }

    @Test
    public void list_territories_ordered_by_most_painted_area() throws Exception {
        territories = DataBase.persistTerritories(500L);
        territory0 = territories.get(0);
        territory1 = territories.get(1);
        url +=  "?order=mostPaintedArea";
        logger.log(Level.INFO, url);
        expect().statusCode(200).
                body("size()", is(territories.size())).
                body("get(0).id", equalTo(Long.valueOf(territory1.getId()).intValue())).
                body("get(0).name", equalTo(territory1.getName())).
                body("get(0).start", equalTo(new Gson().toJson(territory1.getStartArea()))).
                body("get(0).end", equalTo(new Gson().toJson(territory1.getEndArea()))).
                body("get(0).area", equalTo(Long.valueOf(territory1.area()).intValue())).
                body("get(0).paintedArea", equalTo(Long.valueOf(territory1.getPaintedArea()).intValue())).
                body("get(1).id", equalTo(Long.valueOf(territory0.getId()).intValue())).
                body("get(1).name", equalTo(territory0.getName())).
                body("get(1).start", equalTo(new Gson().toJson(territory0.getStartArea()))).
                body("get(1).end", equalTo(new Gson().toJson(territory0.getEndArea()))).
                body("get(1).area", equalTo(Long.valueOf(territory0.area()).intValue())).
                body("get(1).paintedArea", equalTo(Long.valueOf(territory0.getPaintedArea()).intValue())).
                when().get(url);
    }

    @Test
    public void get_all_territories() throws Exception {
        territories = DataBase.persistTerritories();
        territory0 = territories.get(0);
        territory1 = territories.get(1);
        expectTerritoryZeroFirst();
    }

    @Test
    public void should_return_territories_not_found() throws Exception {
        DataBase.deleteTerritories();
        logger.log(Level.INFO, url);
        expect().statusCode(404).
                body("messageReturn", equalTo("territories not found")).
                when().get(url);
    }

    private void assertError(Response response, int statusCode, String errorMessage) {
        assertEquals(statusCode,response.getStatusCode());
        final JsonPath jsonResponse = response.jsonPath();
        assertEquals(errorMessage,jsonResponse.getString("messageReturn"));
    }

    private void expectTerritoryZeroFirst() {
        logger.log(Level.INFO, url);
        expect().statusCode(200).
                body("size()", is(territories.size())).
                body("get(0).id", equalTo(Long.valueOf(territory0.getId()).intValue())).
                body("get(0).name", equalTo(territory0.getName())).
                body("get(0).start", equalTo(new Gson().toJson(territory0.getStartArea()))).
                body("get(0).end", equalTo(new Gson().toJson(territory0.getEndArea()))).
                body("get(0).area", equalTo(Long.valueOf(territory0.area()).intValue())).
                body("get(0).paintedArea", equalTo(Long.valueOf(territory0.getPaintedArea()).intValue())).
                body("get(1).id", equalTo(Long.valueOf(territory1.getId()).intValue())).
                body("get(1).name", equalTo(territory1.getName())).
                body("get(1).start", equalTo(new Gson().toJson(territory1.getStartArea()))).
                body("get(1).end", equalTo(new Gson().toJson(territory1.getEndArea()))).
                body("get(1).area", equalTo(Long.valueOf(territory1.area()).intValue())).
                body("get(1).paintedArea", equalTo(Long.valueOf(territory1.getPaintedArea()).intValue())).
                when().get(url);
    }
}