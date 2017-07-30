package vittahealth.hiring.challenge.acceptancetests;


import org.junit.Before;
import org.junit.Test;
import vittahealth.hiring.challenge.domain.Territory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

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
    public void list_territories_ordered_by_most_proportional_painted_area() throws Exception {
        throw new RuntimeException("Implementar");
    }

    @Test
    public void list_territories_ordered_by_most_painted_area() throws Exception {
        territories = DataBaseUtils.persistTerritories(500L);
        territory0 = territories.get(0);
        territory1 = territories.get(1);
        url +=  "?order=mostPaintedArea";
        logger.log(Level.INFO, url);
        expect().statusCode(200).
                body("size()", is(territories.size())).
                body("get(0).id", equalTo(2)).
                body("get(0).name", equalTo(territory1.getName())).
                body("get(0).startArea.x", equalTo(territory1.getStartArea().getX())).
                body("get(0).startArea.y", equalTo(territory1.getStartArea().getY())).
                body("get(0).endArea.x", equalTo(territory1.getEndArea().getX())).
                body("get(0).endArea.y", equalTo(territory1.getEndArea().getY())).
                body("get(0).area", equalTo(territory1.getArea().intValue())).
                body("get(0).paintedArea", equalTo(territory1.getPaintedArea().intValue())).
                body("get(1).id", equalTo(1)).
                body("get(1).name", equalTo(territory0.getName())).
                body("get(1).startArea.x", equalTo(territory0.getStartArea().getX())).
                body("get(1).startArea.y", equalTo(territory0.getStartArea().getY())).
                body("get(1).endArea.x", equalTo(territory0.getEndArea().getX())).
                body("get(1).endArea.y", equalTo(territory0.getEndArea().getY())).
                body("get(1).area", equalTo(territory0.getArea().intValue())).
                body("get(1).paintedArea", equalTo(territory0.getPaintedArea().intValue())).
                when().get(url);
    }

    @Test
    public void get_all_territories() throws Exception {
        territories = DataBaseUtils.persistTerritories();
        territory0 = territories.get(0);
        territory1 = territories.get(1);
        logger.log(Level.INFO, url);
        expect().statusCode(200).
                body("size()", is(territories.size())).
                body("get(0).id", equalTo(1)).
                body("get(0).name", equalTo(territory0.getName())).
                body("get(0).startArea.x", equalTo(territory0.getStartArea().getX())).
                body("get(0).startArea.y", equalTo(territory0.getStartArea().getY())).
                body("get(0).endArea.x", equalTo(territory0.getEndArea().getX())).
                body("get(0).endArea.y", equalTo(territory0.getEndArea().getY())).
                body("get(0).area", equalTo(territory0.getArea().intValue())).
                body("get(0).paintedArea", equalTo(territory0.getPaintedArea().intValue())).
                body("get(1).id", equalTo(2)).
                body("get(1).name", equalTo(territory1.getName())).
                body("get(1).startArea.x", equalTo(territory1.getStartArea().getX())).
                body("get(1).startArea.y", equalTo(territory1.getStartArea().getY())).
                body("get(1).endArea.x", equalTo(territory1.getEndArea().getX())).
                body("get(1).endArea.y", equalTo(territory1.getEndArea().getY())).
                body("get(1).area", equalTo(territory1.getArea().intValue())).
                body("get(1).paintedArea", equalTo(territory1.getPaintedArea().intValue())).
                when().get(url);
    }
}