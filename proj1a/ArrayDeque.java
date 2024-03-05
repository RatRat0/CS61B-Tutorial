public class ArrayDeque<T> {
    private int size;
    private int totalSize;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        totalSize = 8;
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[totalSize];
    }

    public void addFirst(T item) {
        size++;
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + totalSize) % totalSize;
        if (size == totalSize - 1) {
            this.extendDouble();
        }
    }

    public void addLast(T item) {
        size++;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % totalSize;
        if (size == totalSize - 1) {
            this.extendDouble();
        }
    }

    /**
     * 将数组的总大小扩大一倍
     */
    private void extendDouble() {
        T[] newItems = (T[]) new Object[2 * totalSize];

        int j = 0;

        for (int i = (nextFirst + 1) % totalSize; i != nextLast; i = (i + 1) % totalSize) {
            newItems[++j] = items[i];
        }

        nextFirst = 0;
        nextLast = j;

        totalSize = 2 * totalSize;
        items = newItems;
    }

    /**
     * 将数组的总大小减半
     */
    private void extendHalf() {
        if (totalSize <= 8) {
            return;
        }

        T[] newItems = (T[]) new Object[totalSize / 2];

        int j = 0;

        for (int i = (nextFirst + 1) % totalSize; i != nextLast; i = (i + 1) % totalSize) {
            newItems[j++] = items[i];
        }

        nextFirst = 0;
        nextLast = j;

        totalSize = totalSize / 2;
        items = newItems;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = (nextFirst + 1) % totalSize; i != nextLast; i = (i + 1) % totalSize) {
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        if (2 * size < totalSize) {
            this.extendHalf();
        }
        nextFirst = (nextFirst + 1) % totalSize;
        T res = items[nextFirst];
        size--;
        return res;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        if (2 * size < totalSize) {
            this.extendHalf();
        }
        nextLast = (nextLast - 1 + totalSize) % totalSize;
        T res = items[nextLast];
        size--;
        return res;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + index + 1) % totalSize];
    }

}
