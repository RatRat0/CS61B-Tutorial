import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("aaaaab"));

        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeOne() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("horse", cc));
        assertFalse(palindrome.isPalindrome("cat", cc));

        assertTrue(palindrome.isPalindrome("abbdccab", cc));
        assertTrue(palindrome.isPalindrome("rbbecas", cc));
        assertTrue(palindrome.isPalindrome("", cc));
    }

    @Test
    public void testIsPalindrome5() {
        CharacterComparator cc = new OffByN(5);
        assertFalse(palindrome.isPalindrome("horse", cc));
        assertFalse(palindrome.isPalindrome("cat", cc));

        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("afaf", cc));
    }
}
