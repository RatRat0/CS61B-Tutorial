public class LinkedListDeque<T> {

    Node<T> sentinel;
    int size;

    private static class Node<T> {
        T val;
        Node<T> next;
        Node<T> prev;

        public Node() {
            val = null;
            next = null;
            prev = null;
        }

        public Node(T val) {
            this.val = val;
            next = null;
            prev = null;
        }

        public Node(T val, Node<T> next, Node<T> prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<T>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node<T> next = sentinel.next;
        Node<T> cur = new Node<T>(item);
        sentinel.next = cur;
        cur.prev = sentinel;
        cur.next = next;
        next.prev = cur;
        size++;
    }

    public void addLast(T item) {
        Node<T> prev = sentinel.prev;
        Node<T> cur = new Node<T>(item);
        sentinel.prev = cur;
        cur.next = sentinel;
        cur.prev = prev;
        prev.next = cur;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> cur = sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Node<T> first = sentinel.next;
        Node<T> next = first.next;
        sentinel.next = next;
        next.prev = sentinel;
        first.next = null;
        first.prev = null;
        size--;
        return first.val;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Node<T> last = sentinel.prev;
        Node<T> prev = last.prev;
        prev.next = sentinel;
        sentinel.prev = prev;
        last.next = null;
        last.prev = null;
        size--;
        return last.val;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        Node<T> cur = sentinel.next;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, Node<T> cur) {
        if (index == 0) {
            return cur.val;
        }
        return getRecursive(index - 1, cur.next);
    }
}
