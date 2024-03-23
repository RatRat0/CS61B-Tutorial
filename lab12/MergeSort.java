import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> res = new Queue<>();

        for (Item item : items) {
            Queue<Item> tmp = new Queue<>();
            tmp.enqueue(item);
            res.enqueue(tmp);
        }

        return res;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> res = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            Item minItem = getMin(q1, q2);
            res.enqueue(minItem);
        }

        return res;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items == null) {
            return null;
        }
        if (items.size() <= 1) {
            return items;
        }

        Queue<Queue<Item>> tmp = makeSingleItemQueues(items);

        while (tmp.size() > 1) {
            Queue<Item> q1 = tmp.dequeue();
            Queue<Item> q2 = tmp.dequeue();

            tmp.enqueue(mergeSortedQueues(q1, q2));
        }

        return tmp.dequeue();
    }

    public static void main(String[] args) {
        Queue<Double> que1 = new Queue<>();
        Queue<Double> que2 = new Queue<>();

        que1.enqueue(2.2);
        que1.enqueue(4.5);
        que1.enqueue(9.9);

        que2.enqueue(1.5);
        que2.enqueue(3.3);
        que2.enqueue(4.4);

        //test mergeSortedQueues
        Queue<Double> que3 = mergeSortedQueues(que1, que2);
        System.out.println(que3);
        System.out.println(que2);
        System.out.println(que1);

        //test makeSingleItemQueues
        Queue<Queue<Double>> que4 = makeSingleItemQueues(que3);
        for (Queue<Double> que : que4) {
            System.out.println(que);
        }

        Queue<Double> que5 = new Queue<>();
        que5.enqueue(2.8);
        que5.enqueue(7.3);
        que5.enqueue(2.2);
        que5.enqueue(4.6);
        que5.enqueue(6.6);
        que5.enqueue(9.9);
        Queue<Double> que6 = mergeSort(que5);

        System.out.println(que5);
        System.out.println(que6);

    }
}
