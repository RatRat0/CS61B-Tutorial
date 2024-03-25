import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {

    private Picture picture;
    private int width;
    private int height;
    private double[][] energy;

    public SeamCarver(Picture picture) {
        this.picture = picture;
        width = picture.width();
        height = picture.height();
        energy = new double[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color leftColor = new Color(picture.getRGB(Math.floorMod(i - 1, width), j));
                Color rightColor = new Color(picture.getRGB(Math.floorMod(i + 1, width), j));
                Color upColor = new Color(picture.getRGB(i, Math.floorMod(j - 1, height)));
                Color downColor = new Color(picture.getRGB(i, Math.floorMod(j + 1, height)));

                int deltaX = calcSqureSum(leftColor, rightColor);
                int deltaY = calcSqureSum(upColor, downColor);
                energy[i][j] = deltaX + deltaY;
            }
        }

    }

    private int calcSqureSum(Color color1, Color color2) {
        int R = (color1.getRed() - color2.getRed()) * (color1.getRed() - color2.getRed());
        int G = (color1.getGreen() - color2.getGreen()) * (color1.getGreen() - color2.getGreen());
        int B = (color1.getBlue() - color2.getBlue()) * (color1.getBlue() - color2.getBlue());
        return R + G + B;
    }

    private boolean isInBound(int x, int y) {
        return x >= 0 && x < width
                && y >= 0 && y < height;
    }

    public Picture picture() {
        return picture;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public double energy(int x, int y) {
        if (!isInBound(x, y)) {
            throw new IndexOutOfBoundsException();
        }
        return energy[x][y];
    }

    public int[] findHorizontalSeam() {
        int width = this.width;
        int height = this.height;
        energy = transpose(energy);
        this.width = height;
        this.height = width;

        int[] res = findVerticalSeam();

        energy = transpose(energy);
        this.width = width;
        this.height = height;

        return res;
    }

    private double[][] transpose(double[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int width = matrix.length;
        int height = matrix[0].length;
        double[][] res = new double[height][width];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[j][i] = matrix[i][j];
            }
        }

        return res;
    }

    public int[] findVerticalSeam() {
        double[][] M = new double[width][height];
        int[][] path = new int[width][height];

        for (int j = 0; j < width; j++) {
            M[j][0] = energy[j][0];
            path[j][0] = -1;
        }

        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double left = getM(M, j - 1, i - 1);
                double mid = getM(M, j, i - 1);
                double right = getM(M, j + 1, i - 1);

                if (left < mid && left < right) {
                    M[j][i] = left + energy[j][i];
                    path[j][i] = j - 1;
                } else if (right < left && right < mid) {
                    M[j][i] = right + energy[j][i];
                    path[j][i] = j + 1;
                } else {
                    M[j][i] = mid + energy[j][i];
                    path[j][i] = j;
                }
            }
        }

        int optJ = -1;
        double min = Double.MAX_VALUE;

        for (int j = 0; j < width; j++) {
            if (min > M[j][height - 1]) {
                min = M[j][height - 1];
                optJ = j;
            }
        }

        int[] res = new int[height];
        for (int i = height - 1; i >= 0; i--) {
            res[i] = optJ;
            optJ = path[optJ][i];
        }

        return res;

    }

    private double getM(double[][]M, int x, int y) {
        if (!isInBound(x, y)) {
            return Double.MAX_VALUE;
        }
        return M[x][y];
    }

    public void removeHorizontalSeam(int[] seam) {
        SeamRemover.removeHorizontalSeam(picture, findHorizontalSeam());
    }

    public void removeVerticalSeam(int[] seam) {
        SeamRemover.removeVerticalSeam(picture, findVerticalSeam());
    }

}
