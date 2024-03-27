package creatures;

import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

public class TestClorus {


    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());

    }
}
