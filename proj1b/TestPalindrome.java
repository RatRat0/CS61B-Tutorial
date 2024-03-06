import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator getOffBy5 = new OffByN(5);

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
        assertFalse(palindrome.isPalindrome("horse", offByOne));
        assertFalse(palindrome.isPalindrome("cat", offByOne));

        assertTrue(palindrome.isPalindrome("abbdccab", offByOne));
        assertTrue(palindrome.isPalindrome("rbbecas", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
    }

    @Test
    public void testIsPalindrome5() {
        assertFalse(palindrome.isPalindrome("horse", getOffBy5));
        assertFalse(palindrome.isPalindrome("cat", getOffBy5));

        assertTrue(palindrome.isPalindrome("", getOffBy5));
        assertTrue(palindrome.isPalindrome("afaf", getOffBy5));
    }
}
