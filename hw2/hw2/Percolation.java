package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private boolean[][] grid;
    private WeightedQuickUnionUF UF;
    private int top, bottom;
    private int numberOfOpen;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new boolean[N][N];
        numberOfOpen = 0;
        this.clear();
        top = N * N;
        bottom = N * N + 1;
        UF = new WeightedQuickUnionUF(bottom + 1);
    }

    /**
     * 将该对象的所有东西初始化
     */
    private void clear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
        if (!isInOfIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        numberOfOpen++;
        int index = row * N + col;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        if (row == 0) {
            UF.union(index, top);
        }
        if (row == N - 1) {
            UF.union(index, bottom);
        }

        for (int i = 0; i < 4; i++) {
            int newX = row + dx[i];
            int newY = col + dy[i];
            if (isInOfIndex(newX, newY) && grid[newX][newY]) {
                UF.union(index, newX * N + newY);
            }
        }
    }

    private boolean isInOfIndex(int row, int col) {
        return row >= 0 && row < N
                && col >= 0 && col < N;
    }

    public boolean isOpen(int row, int col) {
        if (!isInOfIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (!isInOfIndex(row, col)) {
            throw new IndexOutOfBoundsException("row:" + row + " col:" + col + " N:" + N);
        }
        int index = row * N + col;
        return UF.connected(index, top);
    }

    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    public boolean percolates() {
        return UF.connected(top, bottom);
    }

}
