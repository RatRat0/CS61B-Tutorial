package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] nodeTo;
    private Maze maze;
    private boolean isFound = false;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.N() * maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        DFS(-1, 0);
    }

    // Helper methods go here
    private void DFS(int parent, int cur) {
        if (isFound) {
            return;
        }
        marked[cur] = true;
        announce();

        for (int child : maze.adj(cur)) {
            if (!marked[child]) {
                nodeTo[child] = cur;
                DFS(cur, child);
            } else if (child != parent) {
                edgeTo[child] = cur;
                announce();
                for (int v = cur; v != child; v = nodeTo[v]) {
                    edgeTo[v] = nodeTo[v];
                    announce();
                }
                isFound = true;
            }
            if (isFound) {
                return;
            }
        }
    }

}

