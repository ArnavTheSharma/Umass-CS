import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MaxFinderText {
    @Test
    public void testFindMax() {
        double[] A = {100, 0, 9, 22, -4, 6, 999};
        assertEquals(MaxFinder.findMaximum(A), 999, 0.01);
    }

    public void testMaxIsFirst() {
        double[] B = {1000, 0, 9, 22, -4, 6, 999};
        assertEquals(MaxFinder.findMaximum(B), 1000, 0.01);
    }

    public void testOnlyOneElement() {
        double[] C = {999};
        assertEquals(MaxFinder.findMaximum(C), 999, 0.01);
    }
}
