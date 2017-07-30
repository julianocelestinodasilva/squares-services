package vittahealth.hiring.challenge.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTest {

    @Test
    public void should_return_area_zero() throws Exception {
        final int expectedArea = 0;
        final int x = 0;
        final int  y = 0;
        area(expectedArea, x, y);
    }

    @Test
    public void should_return_area() throws Exception {
        final int expectedArea = 2500;
        final int x = 50;
        final int  y = 50;
        area(expectedArea, x, y);
    }

    private void area(long expectedArea, int x, int y) {
        Node node = new Node(x,y);
        long area = node.area();
        assertEquals(expectedArea,area);
    }
}
