package vittahealth.hiring.challenge.acceptancetests;


import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import vittahealth.hiring.challenge.Territory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
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
        final String asString = expect().statusCode(200).when().get(url).asString();
        assertEquals(getExpectedJson(),asString);
    }

    private String getExpectedJson() {
        return  "{\"count\":"+territories.size()+"" +
                ",\"data\":[" +
                "{\"id\":"+territory0.getId()+",\"name\":\""+territory0.getName()+"\",\"startArea\":\""+territory0.getStartArea()+"\",\"endArea\":\""+territory0.getEndArea()+"\",\"area\":"+territory0.getArea()+",\"paintedArea\":"+territory0.getPaintedArea()+"}" +
               ",{\"id\":"+territory1.getId()+",\"name\":\""+territory1.getName()+"\",\"startArea\":\""+territory1.getStartArea()+"\",\"endArea\":\""+territory1.getEndArea()+"\",\"area\":"+territory1.getArea()+",\"paintedArea\":"+territory1.getPaintedArea()+"}]}";
    }
}
