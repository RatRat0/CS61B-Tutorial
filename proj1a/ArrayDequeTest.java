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

    @Test
    public void d01123Test() {
        ArrayDeque<Integer> exp = new ArrayDeque<Integer>();
        exp.addLast(0);
        exp.removeFirst();
        exp.addFirst(2);
        exp.addFirst(3);
        exp.removeLast();
        exp.addFirst(5);
        exp.addFirst(6);
        exp.addFirst(7);
        exp.get(3);
        exp.addFirst(9);
        exp.removeFirst();
        exp.removeFirst();
        exp.get(1);
        exp.removeLast();
        exp.get(0);
        exp.removeLast();
        exp.get(0);
        exp.addLast(17);
        exp.addLast(18);
        exp.addLast(19);
        exp.addLast(20);
        exp.addLast(21);
        exp.addLast(22);
        assertEquals(22, (int) exp.removeLast());
    }

}
