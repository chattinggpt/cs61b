public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque q = new ArrayDeque<Character>();
        for (int i=0; i<word.length(); i++) {
            q.addLast(word.charAt(i));
        }
        return q;
    }
    public boolean isPalindrome(String word) {
        for (int i=0; i<word.length(); i++) {
            if (i >= word.length()-1-i) break;
            if (word.charAt(i) != word.charAt(word.length()-1-i)) return false;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        for (int i=0; i<word.length(); i++) {
            if (i >= word.length()-1-i) break;
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length()-1-i))) return false;
        }
        return true;
    }
}
