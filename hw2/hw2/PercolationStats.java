package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    int[] results;
    int N, T;
    PercolationFactory pf;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        // 初始化
        this.N = N;
        this.T = T;
        this.pf = pf;

        results = new int[T];
        this.clear();

        // 开始实验
        for (int i = 0; i < T; i++) {
            experience(i);
        }
    }

    private void experience(int t) {
        Percolation curExp = pf.make(N);
        while (!curExp.percolates()) {
            int opt = StdRandom.uniform(N * N);
            curExp.open(opt / N, opt % N);
        }
        results[t] = curExp.numberOfOpenSites();
    }

    private void clear() {
        for (int i = 0; i < T; i++) {
            results[i] = 0;
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        if (T == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(results);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
