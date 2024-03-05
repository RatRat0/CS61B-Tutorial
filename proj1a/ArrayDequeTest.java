import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAddAndGetAndSize() {
        ArrayDeque<Integer> exp = new ArrayDeque<Integer>();
        assertTrue(exp.isEmpty());
        for (int i = 0; i < 8; i++) {
            exp.addLast(i);
        }
        assertEquals(0, (int) exp.get(0));
        assertEquals(8, exp.size());

        for (int i = 0; i < 16; i++) {
            exp.addFirst(i);
        }
        assertEquals(14, (int) exp.get(1));
        assertEquals(24, exp.size());

        for (int i = 0; i < 16; i++) {
            exp.removeFirst();
        }
        assertEquals(8, exp.size());
        for (int i = 0; i < 9; i++) {
            exp.removeLast();
        }
        assertTrue(exp.isEmpty());
    }

}
