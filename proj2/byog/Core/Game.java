package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.*;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    Random rand;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        char startMethod = input.charAt(0);
        TETile[][] res = new TETile[WIDTH][HEIGHT];

        if (startMethod == 'N' || startMethod == 'n') {
            /*String seedStr = input.substring(1, input.length() - 1);
            if (input.charAt(input.length() - 1) != 's' &&
                    input.charAt(input.length() - 1) != 'S') {
                return null;
            }*/
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (; i < input.length() && input.charAt(i) != 'S'
                    && input.charAt(i) != 's'; i++) {
                sb.append(input.charAt(i));
            }
            String seedStr = sb.toString();
            int seed = Integer.parseInt(seedStr);
            createWorld(res, seed);

            i++;
            if (input.length() >= i + 2 && input.charAt(i) == ':' &&
                    (input.charAt(i + 1) == 'q' || input.charAt(i + 1) == 'Q')) {
                saveWorld(res);
            }
        } else if (startMethod == 'L' || startMethod == 'l') {
            res = loadWorld();
        }

        return res;

    }

    /**
     * 初始化这个世界，以免里面有null之类的难处理的东西
     * @param world: 这个世界的瓷砖分布
     */
    private static void initWorld(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    /**
     * 加载世界，返回一个世界的瓷砖数组
     * @return 返回一个世界的瓷砖数组
     */
    private TETile[][] loadWorld() {
        File f = new File("./myWorld.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                TETile[][] res = (TETile[][]) os.readObject();
                os.close();
                return res;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        System.exit(0);
        return null;
    }

    /**
     * 根据这个world的瓷砖来保存一个世界
     * @param world 这是代表一个世界的瓷砖数组
     */
    private void saveWorld(TETile[][] world) {
        File f = new File("./myWorld.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(world);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * 根据一个种子来生成我想要的世界
     * @param seed 一个代表随机的种子
     * @return 返回一个代表世界的瓷砖数组
     */
    private void createWorld(TETile[][] world, int seed) {
        rand = new Random(seed);
        int totoalNum = WIDTH * HEIGHT;
        int randomX = RandomUtils.uniform(rand, 1, WIDTH - 1);
        int randomY = RandomUtils.uniform(rand, 1, HEIGHT - 1);
        int[] floorNum = new int[]{RandomUtils.uniform(rand, totoalNum / 2, totoalNum * 2 / 3)};

        //首先初始化世界
        initWorld(world);

        //生成floor
        generateFloor(world, randomX, randomY, floorNum);
        generateWall(world);

    }

    /**
     * 随机生成地板
     * @param world 呈现世界的瓷砖
     * @param startX 在该位置添加瓷砖
     * @param startY 在该位置添加瓷砖
     * @param floorNum 剩下添加瓷砖的数量
     */
    private void generateFloor(TETile[][] world, int startX, int startY, int[] floorNum) {
        if (floorNum[0] <= 0 || startX == 0 || startX == WIDTH - 1 ||
        startY == 0 || startY == HEIGHT - 1) {
            return;
        }
        world[startX][startY] = Tileset.FLOOR;
        floorNum[0]--;
        int[] dx = new int[]{1, 1, -1, -1};
        int[] dy = new int[]{1, -1, 1, -1};
        int[][] directions = new int[][] {{0, 1, 2, 3}, {1, 3, 2, 0}, {2 ,1, 3, 0},
                {3, 1, 2, 0}, {0, 3, 2, 1}, {1, 2, 3, 0}, {2, 3, 0, 1}};

        int[] direction = directions[RandomUtils.uniform(rand, 0, 7)];

        for (int i = 0; i < 4; i++) {
            int newX = startX + dx[direction[i]];
            int newY = startY + dy[direction[i]];
            generateFloor(world, newX, newY, floorNum);
        }

    }

    /**
     * 根据floor的分布添加墙壁
     * @param world 这个世界的地形
     */
    private void generateWall(TETile[][] world) {
        int[] dx = {1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1};
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == Tileset.FLOOR || world[i][j] == Tileset.WALL) {
                    continue;
                }
                boolean isNearFloor = false;
                for (int k = 0; k < 4; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    if (newX < 0 || newX >= WIDTH ||
                    newY < 0 || newY >= HEIGHT) {
                        continue;
                    }
                    if (world[newX][newY] == Tileset.FLOOR) {
                        isNearFloor = true;
                    }
                }
                if (isNearFloor) {
                    world[i][j] = Tileset.WALL;
                }
            }
        }
    }

}
