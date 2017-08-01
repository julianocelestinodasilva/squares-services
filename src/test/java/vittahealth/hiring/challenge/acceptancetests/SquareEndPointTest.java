package vittahealth.hiring.challenge.acceptancetests;


import org.junit.Before;
import org.junit.Test;
import vittahealth.hiring.challenge.domain.Node;
import vittahealth.hiring.challenge.domain.Territory;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static vittahealth.hiring.challenge.acceptancetests.DataBase.deleteTerritories;
import static vittahealth.hiring.challenge.acceptancetests.DataBase.persistTerritoryToTest;

public class SquareEndPointTest {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String url;

    @Before
    public void setUp() throws Exception {
        url = URLApi.squares();
    }

    @Test
    public void should_paint_square() throws Exception {
        Territory territory = new Territory("A",new Node(0,0),new Node(50,50));
        final Node square = new Node(1, 2);
        persistTerritoryToTest(territory);
        territory.paint(square);
        url +=  "/"+square.getX()+"/"+square.getY()+"/paint";
        logger.log(Level.INFO, url);
        expect().statusCode(200).
                body("x", equalTo(square.getX())).
                body("y", equalTo(square.getY())).
                body("painted", equalTo(square.isPainted())).
                when().patch(url);
    }

    @Test
    public void get_status_of_a_square_painted() throws Exception {
        Territory territory = new Territory("A",new Node(0,0),new Node(50,50));
        final Node square = new Node(1, 2);
        territory.paint(square);
        persistTerritoryToTest(territory);
        url +=  "/"+square.getX()+"/"+square.getY();
        logger.log(Level.INFO, url);
        getStatus(200,square);
    }

    @Test
    public void get_status_of_a_square_no_painted() throws Exception {
        Territory territory = new Territory("A",new Node(0,0),new Node(50,50));
        persistTerritoryToTest(territory);
        url +=  "/1/2";
        logger.log(Level.INFO, url);
        getStatus(200,new Node(1, 2));
    }

    @Test
    public void should_return_this_square_does_not_belong_to_any_territory() throws Exception {
        deleteTerritories();
        url +=  "/1/2";
        logger.log(Level.INFO, url);
        expect().statusCode(404).
                body("messageReturn", equalTo("this square does not belong to any territory")).
                when().get(url);
    }

    private void getStatus(int statusCode, Node square) {
        expect().statusCode(statusCode).
                body("x", equalTo(square.getX())).
                body("y", equalTo(square.getY())).
                body("painted", equalTo(square.isPainted())).
                when().get(url);
    }
}
