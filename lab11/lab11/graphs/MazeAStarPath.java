package lab11.graphs;

import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private class SearchNode implements Comparable<SearchNode> {
        private int value;
        private int dist;
        private int h;

        public SearchNode(int value) {
            this.value = value;
            dist = distTo[value];
            h = h(value);
        }


        @Override
        public int compareTo(SearchNode o) {
            return this.dist + this.h
                    - o.dist - o.h;

        }
    }

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int curX = maze.toX(v);
        int curY = maze.toY(v);

        int targetX = maze.toX(t);
        int targetY = maze.toY(t);

        return Math.abs(curX - targetX) + Math.abs(curY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        PriorityQueue<SearchNode> PQ = new PriorityQueue<>();
        PQ.add(new SearchNode(s));

        while (!targetFound && !PQ.isEmpty()) {
            SearchNode NodeV = PQ.remove();
            int v = NodeV.value;
            announce();

            if (v == t) {
                targetFound = true;
                break;
            }

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    announce();
                    PQ.add(new SearchNode(w));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

