package vittahealth.hiring.challenge.acceptancetests;


import io.restassured.response.Response;
import org.junit.*;
import spark.Spark;
import sun.applet.Main;
import vittahealth.hiring.challenge.Territory;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.request;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
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
    public void get_all_territories() throws Exception {
        territories = DataBaseUtils.persistTerritories();
        territory0 = territories.get(0);
        territory1 = territories.get(1);
        logger.log(Level.INFO, url);
        /*final String asString = expect().statusCode(200).when().get(url).asString();
        assertEquals(getExpectedJson(),asString);*/

        expect().statusCode(200).
                body("size()", is(territories.size())).
                body("count", is(territories.size())).
                body("data.get(0).id", notNullValue()).
                /*body("get(0).id", notNullValue()).
                body("get(0).name", equalTo(territory0.getName())).*/
                when().get(url);
    }

    private String getExpectedJson() {
        return  "{\"count\":"+territories.size()+"" +
                ",\"data\":[" +
                "{\"id\":"+territory0.getId()+",\"name\":\""+territory0.getName()+"\",\"startArea\":\""+territory0.getStartArea()+"\",\"endArea\":\""+territory0.getEndArea()+"\",\"area\":"+territory0.getArea()+",\"paintedArea\":"+territory0.getPaintedArea()+"}" +
               ",{\"id\":"+territory1.getId()+",\"name\":\""+territory1.getName()+"\",\"startArea\":\""+territory1.getStartArea()+"\",\"endArea\":\""+territory1.getEndArea()+"\",\"area\":"+territory1.getArea()+",\"paintedArea\":"+territory1.getPaintedArea()+"}]}";
    }
}
