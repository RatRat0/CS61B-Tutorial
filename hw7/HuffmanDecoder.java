public class HuffmanDecoder {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: [compressed file] [origin file]");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        // Step1 2 3
        ObjectReader or = new ObjectReader(inputFileName);
        // Step1: Read the Huffman coding trie.
        BinaryTrie huffmanTrie = (BinaryTrie) or.readObject();
        // Step2: Read the number of symbols.
        int numberOfSymbols = (int) or.readObject();
        // Step3: read the massive bit sequence corresponding to the original txt.
        BitSequence compressBitSeq = (BitSequence) or.readObject();
        char[] outputChars = new char[numberOfSymbols];
        int charIndex = 0;

        // Step4: Repeat until compressBitSeq is nothing
        // Step4a: Perform a longest prefix match
        while (compressBitSeq != null && compressBitSeq.length() != 0 && charIndex < numberOfSymbols) {
            Match curMatch = huffmanTrie.longestPrefixMatch(compressBitSeq);
            compressBitSeq = compressBitSeq.allButFirstNBits(curMatch.getSequence().length());
            // Step4b: Record the symbol in some date structure.
            outputChars[charIndex++] = curMatch.getSymbol();
        }

        FileUtils.writeCharArray(outputFileName, outputChars);
    }
}
