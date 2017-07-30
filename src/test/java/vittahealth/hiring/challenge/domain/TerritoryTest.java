package vittahealth.hiring.challenge.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerritoryTest {

    @Test
    public void should_return_area() throws Exception {
        long expectedArea = 2900;
        final Node start = new Node(20,20);
        final Node end = new Node(50,50);
        area(expectedArea, start, end);

    }

    @Test
    public void should_return_area_start_zero() throws Exception {
        long expectedArea = 2500;
        final Node start = new Node(0,0);
        final Node end = new Node(50,50);
        area(expectedArea, start, end);

    }

    private void area(long expectedArea, Node start, Node end) {
        Territory territory = new Territory("",start,end);
        final long area = territory.getArea();
        assertEquals(expectedArea,area);
    }
}
