public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> deque) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        char first = deque.removeFirst();
        char last = deque.removeLast();
        if (first != last) {
            return false;
        }
        return isPalindrome(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        char first = deque.removeFirst();
        char last = deque.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return isPalindrome(deque, cc);
    }
}
