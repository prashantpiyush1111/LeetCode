/**
 * LeetCode 1880: Check if Word Equals Summation of Two Words
 *
 * The value of each character is its zero-based position in the alphabet.
 * Build each word's numeric value by appending those values as decimal digits.
 *
 * Time Complexity: O(n + m + k), where n, m, and k are the word lengths.
 * Space Complexity: O(1).
 */
class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return wordValue(firstWord) + wordValue(secondWord) == wordValue(targetWord);
    }

    private int wordValue(String word) {
        int value = 0;

        for (int i = 0; i < word.length(); i++) {
            int digit = word.charAt(i) - 'a';
            value = value * 10 + digit;
        }

        return value;
    }
}
