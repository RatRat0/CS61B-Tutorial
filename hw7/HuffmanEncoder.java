import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> res = new HashMap<>();
        for (char inputSymbol : inputSymbols) {
            if (!res.containsKey(inputSymbol)) {
                res.put(inputSymbol, 1);
            } else {
                res.put(inputSymbol, res.get(inputSymbol) + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: filename");
            return;
        }
        String inputFileName = args[0];
        char[] inputFileChars = FileUtils.readFile(inputFileName);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputFileChars);
        BinaryTrie huffmanTrie = new BinaryTrie(frequencyTable);

        // Write the binary decoding trie to the .huf file.
        String outputFileName = inputFileName + ".huf";
        ObjectWriter ow = new ObjectWriter(outputFileName);
        ow.writeObject(huffmanTrie);

        // Step5: write the number of symbols to the .huf file
        int numberOfSybmols = inputFileChars.length;
        ow.writeObject(numberOfSybmols);

        //Use binary trie to create lookup table for encoding.
        Map<Character, BitSequence> lookupTable = huffmanTrie.buildLookupTable();
        List<BitSequence> bitSequenceList = new ArrayList<>();

        // for each 8 bit symbol: make that sybol to the list of bitsequences.
        for (char inputfileChar : inputFileChars) {
            BitSequence curBitSeq = lookupTable.get(inputfileChar);
            bitSequenceList.addLast(curBitSeq);
        }

        // Assemble all bit sequences into one huge bit sequence.
        BitSequence compressedBitSeq = BitSequence.assemble(bitSequenceList);
        ow.writeObject(compressedBitSeq);
    }
}
