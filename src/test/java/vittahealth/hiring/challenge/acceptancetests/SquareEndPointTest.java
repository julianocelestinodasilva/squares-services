package vittahealth.hiring.challenge.acceptancetests;


import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static vittahealth.hiring.challenge.acceptancetests.DataBase.persistTerritory;

public class SquareEndPointTest {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String url;

    @Before
    public void setUp() throws Exception {
        url = URLApi.squares();
    }

    @Test
    public void get_status_of_a_square_no_painted() throws Exception {

        Territory territory = new Territory("A",new Node(0,0),new Node(50,50));
        persistTerritory(territory);

        url +=  "/1/2";
        logger.log(Level.INFO, url);
        expect().statusCode(200).
                body("x", equalTo(1)).
                body("y", equalTo(1)).
                body("painted", equalTo(false)).
                when().get(url);

    }
}