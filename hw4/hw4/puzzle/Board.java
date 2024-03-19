package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private final int N;
    private final int[][] tiles;
    private final int BLANK = 0;

    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, N);
        }
    }

    public int tileAt(int i, int j) {
        if (!isInBound(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    private boolean isInBound(int i, int j) {
        return i >= 0 && i < N
                && j >= 0 && j < N;
    }

    public int size() {
        return N;
    }

    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int answer = 1;
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    continue;
                }
                if (tiles[i][j] != answer) {
                    res++;
                }
                answer++;
            }
        }

        return res;
    }

    public int manhattan() {
        int answer = 1;
        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    continue;
                }
                int answerI = (answer - 1) / N;
                int answerJ = Math.floorMod(answer - 1, N);
                res += Math.abs(answerI - i) + Math.abs(answerJ - j);
                answer++;
            }
        }

        return res;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }

        Board that = (Board) y;

        if (that.N != this.N) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != that.tileAt(i, j)) {
                    return false;
                }
            }
        }

        return true;

    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
