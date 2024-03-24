/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int maxLength = -1;
        String[] res = new String[asciis.length];
        System.arraycopy(asciis, 0, res, 0, asciis.length);

        for (String ascii : asciis) {
            maxLength = ascii.length() > maxLength ? ascii.length() : maxLength;
        }

        for (int i = maxLength - 1; i >= 0; i--) {
            sortHelperLSD(res, i);
        }

        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int max = 255;
        int[] counts = new int[max + 1];

        for (String ascii : asciis) {
            counts[charAt(ascii, index)]++;
        }

        String[] sorted = new String[asciis.length];
        int[] starts = new int[max + 1];

        for (int i = 1; i < starts.length; i++) {
            starts[i] = starts[i - 1] + counts[i - 1];
        }

        for (int i = 0; i < asciis.length; i++) {
            int item = (int) charAt(asciis[i], index);
            int startIndex = starts[item];
            starts[item]++;
            sorted[startIndex] = asciis[i];
        }

        System.arraycopy(sorted, 0, asciis, 0, asciis.length);
    }

    private static char charAt(String s, int index) {
        if (index < 0 || index >= s.length()) {
            return 0;
        }
        return s.charAt(index);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] ss = {"356", "112", "904", "294", "209", "820", "394", "810"};
        String[] sorted = sort(ss);

        // print ss
        for (String s : ss) {
            System.out.print(s + " ");
        }
        System.out.println();

        //print sorted
        for (String s : sorted) {
            System.out.print(s + " ");
        }
    }
}
