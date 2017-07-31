package vittahealth.hiring.challenge.domain;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TerritoryTest {

    @Test
    public void should_return_that_is_same_area() throws Exception {
        final Node start = new Node(0,0);
        final Node end = new Node(50,50);
        Territory territory = new Territory("A",start,end);
        boolean isSameArea = territory.isSameArea(new Territory("B",start,end));
        assertTrue(isSameArea);
    }

    @Test
    public void should_return_proportional_painted_area_total() throws Exception {
        final Node start = new Node(0,0);
        final Node end = new Node(50,50);
        final long paintedArea = 2500L;
        int i = 1;
        List<Node> squaresToPaint = new ArrayList<Node>();
        while (i <= paintedArea) {
            squaresToPaint.add(new Node(i,i+1));
            i++;
        }
        final int expectedProportionalPaintedArea = 100;
        assertProportionalPaintedArea(start, end, expectedProportionalPaintedArea,squaresToPaint);
    }

    @Test
    public void should_return_proportional_painted_area() throws Exception {
        final Node start = new Node(0,0);
        final Node end = new Node(10,10);
        final long paintedArea = 50L;
        int i = 1;
        List<Node> squaresToPaint = new ArrayList<Node>();
        while (i <= paintedArea) {
            squaresToPaint.add(new Node(i,i+1));
            i++;
        }
        final int expectedProportionalPaintedArea = 50;
        assertProportionalPaintedArea(start, end,expectedProportionalPaintedArea,squaresToPaint);
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

    private void assertProportionalPaintedArea(Node start, Node end, int expectedProportionalPaintedArea, List<Node> squaresToPaint) {
        Territory territory = new Territory("TerritoryTest",start,end);
        for (Node suareToPain : squaresToPaint) {
            territory.paint(suareToPain);
        }
        long proportionalPaintedArea = territory.proportionalPaintedArea();
        assertEquals(expectedProportionalPaintedArea,proportionalPaintedArea);
    }

    private void area(long expectedArea, Node start, Node end) {
        Territory territory = new Territory("TerritoryTest",start,end);
        final long area = territory.area();
        assertEquals(expectedArea,area);
    }
}
