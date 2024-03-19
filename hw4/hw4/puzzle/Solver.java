package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private int moveTimes;
    private List<WorldState> bestPath;
    private MinPQ<SearchNode> PQ;

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private SearchNode pre;
        private int moves;

        public SearchNode(WorldState state, SearchNode pre, int moves) {
            this.state = state;
            this.pre = pre;
            this.moves = moves;
        }

        @Override
        public int compareTo(SearchNode that) {
            return moves + state.estimatedDistanceToGoal() - that.moves - that.state.estimatedDistanceToGoal();
        }

    }

    public Solver(WorldState initial) {
        moveTimes = 0;
        PQ = new MinPQ<>();

        SearchNode startNode = new SearchNode(initial, null, 0);
        PQ.insert(startNode);
        while (!PQ.isEmpty()) {
            SearchNode cur = PQ.delMin();
            WorldState curState = cur.state;

            if (curState.isGoal()) {
                getAnswer(cur);
                break;
            }

            for (WorldState neighbor : cur.state.neighbors()) {
                if (cur.pre == null || !neighbor.equals(cur.pre.state)) {
                    PQ.insert(new SearchNode(neighbor, cur, cur.moves + 1));
                }
            }

        }

    }

    private void getAnswer(SearchNode goal) {
        moveTimes = goal.moves;

        bestPath = new ArrayList<>();
        while (goal != null) {
            bestPath.addFirst(goal.state);
            goal = goal.pre;
        }
    }

    public int moves() {
        return moveTimes;
    }

    public Iterable<WorldState> solution() {
        return bestPath;
    }
}
