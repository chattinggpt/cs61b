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
        String s1 = "abcba";
        assertTrue(palindrome.isPalindrome(s1));
        String s2 = "abba";
        assertTrue(palindrome.isPalindrome(s2));
        String s3 = "abca";
        assertFalse(palindrome.isPalindrome(s3));
        String s4 = "abcddcb";
        assertFalse(palindrome.isPalindrome(s4));
    }

    @Test
    public void testOffByOneIsPalindrome() {
        CharacterComparator offByOne = new OffByOne();
        String testString;
        testString = "abcab";
        assertTrue(palindrome.isPalindrome(testString, offByOne));
        testString = "abcdab";
        assertTrue(palindrome.isPalindrome(testString, offByOne));
        testString = "abccab";
        assertFalse(palindrome.isPalindrome(testString, offByOne));
    }

    @Test
    public void testOffByNIsPalindrome() {
        CharacterComparator offByN = new OffByN(5);
        String testString;
        testString = "fa";
        assertTrue(palindrome.isPalindrome(testString, offByN));
        testString = "fd";
        assertFalse(palindrome.isPalindrome(testString, offByN));
    }
}
