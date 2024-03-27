import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {

    private static class Node implements Comparable<Node>, Serializable {
        private final char ch;
        private final int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        //is the node a leaf node?
        private boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    private Node trie;
    boolean islookup = false;
    private Map<Character, BitSequence> lookupTable = new HashMap<>();

    /**
     * build the Huffman trie given frequencies
     * @param frequencyTable
     * @return the Huffman trie
     */
    private static Node buildTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();

        // create the MinPQ
        frequencyTable.forEach((key, value) -> {
            if (value > 0) {
                pq.insert(new Node(key, value, null, null));
            }
        });

        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }

        return pq.delMin();
    }


    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        trie = buildTrie(frequencyTable);
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        if (querySequence == null || querySequence.length() == 1) {
            return new Match(null, trie.ch);
        }

        StringBuilder sb = new StringBuilder();
        Node cur = trie;

        for (int i = 0; i < querySequence.length() && !cur.isLeaf(); i++) {
            int check = querySequence.bitAt(i);
            if (check == 0) {
                sb.append(0);
                cur = cur.left;
            } else if (check == 1) {
                sb.append(1);
                cur = cur.right;
            } else {
                throw new IllegalArgumentException();
            }
        }

        if (!cur.isLeaf()) {
            throw new IllegalArgumentException();
        }

        return new Match(new BitSequence(sb.toString()), cur.ch);
    }

    public Map<Character, BitSequence> buildLookupTable() {
        if (islookup) {
            return lookupTable;
        }
        islookup = true;
        dfsHelper(trie, "");
        return lookupTable;
    }

    private void dfsHelper(Node cur, String prefix) {
        if (cur.isLeaf()) {
            lookupTable.put(cur.ch, new BitSequence(prefix));
            return;
        }

        if (cur.left != null) {
            dfsHelper(cur.left, prefix + "0");
        }
        if (cur.right != null) {
            dfsHelper(cur.right, prefix + "1");
        }
    }
}
