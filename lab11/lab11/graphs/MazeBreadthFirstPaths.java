package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(s);
        marked[s] = true;
        announce();

        while (!targetFound && !que.isEmpty()) {
            int curV = que.remove();

            if (curV == t) {
                targetFound = true;
            }

            if (targetFound) {
                break;
            }

            for (int w : maze.adj(curV)) {
                if (!marked[w]) {
                    edgeTo[w] = curV;
                    distTo[w] = distTo[curV] + 1;
                    marked[w] = true;
                    announce();
                    que.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

