package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public static class Position {
        public int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void init(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int size, TETile t) {
        int startX = p.x;
        int startY = p.y;

        //画上半部分的金字塔，其中i表示所在行的增量，j表示所在列的增量
        for (int i = 0; i < size; i++) {
            for (int j = -i; j < size + i; j++) {
                world[startY + j][startX + i] = t;
            }
        }

        //画下半部分的金字塔
        for (int i = 0; i < size; i++) {
            for (int j = -size + i + 1; j < size + size - i - 1; j++) {
                world[startY + j][startX + size + i] = t;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        Position p = new Position(5, 5);

        init(world);
        addHexagon(world, p, 3, Tileset.TREE);

        ter.renderFrame(world);

    }
}
