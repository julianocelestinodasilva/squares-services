package vittahealth.hiring.challenge.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerritoryTest {

    @Test
    public void should_return_proportional_painted_area_total() throws Exception {
        final Node start = new Node(0,0);
        final Node end = new Node(50,50);
        final long paintedArea = 2500L;
        final int expectedProportionalPaintedArea = 100;
        assertProportionalPaintedArea(start, end, paintedArea, expectedProportionalPaintedArea);
    }

    @Test
    public void should_return_proportional_painted_area() throws Exception {
        final Node start = new Node(0,0);
        final Node end = new Node(10,10);
        final long paintedArea = 50L;
        final int expectedProportionalPaintedArea = 50;
        assertProportionalPaintedArea(start, end, paintedArea, expectedProportionalPaintedArea);
    }

    private void assertProportionalPaintedArea(Node start, Node end, long paintedArea, int expectedProportionalPaintedArea) {
        Territory territory = new Territory("TerritoryTest",start,end);
        territory.setPaintedArea(paintedArea);
        long proportionalPaintedArea = territory.proportionalPaintedArea();
        assertEquals(expectedProportionalPaintedArea,proportionalPaintedArea);
    }

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
        Territory territory = new Territory("TerritoryTest",start,end);
        final long area = territory.area();
        assertEquals(expectedArea,area);
    }
}
