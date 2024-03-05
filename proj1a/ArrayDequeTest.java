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

    @Test
    public void d011Test() {
        ArrayDeque<Integer> exp = new ArrayDeque<Integer>();
        exp.addFirst(0);
        exp.addFirst(1);
        exp.removeFirst();
        exp.addFirst(3);
        exp.removeLast();
        exp.get(0);
        exp.get(0);
        exp.removeFirst();
        exp.addLast(8);
        exp.addFirst(9);
        exp.get(0);
        exp.get(1);
        exp.addLast(12);
        exp.get(1);
        exp.addLast(14);
        exp.addFirst(15);
        exp.get(3);
        exp.get(3);
        exp.addLast(18);
        exp.addFirst(19);
        assertEquals(19, (int) exp.removeFirst());
    }

}
