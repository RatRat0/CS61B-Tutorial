import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testTaskI() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> exp = new ArrayDequeSolution<>();

        String message = "";

        for (int i = 0; i < 20; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.7) {
                test.addLast(i);
                exp.addLast(i);
                message = message + "addLast(" + i + ")\n";
            } else {
                test.addFirst(i);
                exp.addFirst(i);
                message = message + "addFirst(" + i + ")\n";
            }
        }

        for (int i = 0; i < 20; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                message = message + "removeLast()\n";
                assertEquals(message, exp.removeLast(), test.removeLast());
            } else {
                message = message + "removeFirst()\n";
                assertEquals(message, exp.removeFirst(), test.removeFirst());
            }
        }
    }
}
