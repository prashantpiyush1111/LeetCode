public class LexPalindromicPermutation3734 {
    /**
     * LeetCode 3734: Lexicographically Smallest Palindromic Permutation Greater Than Target.
     *
     * Returns the lexicographically smallest palindromic permutation of s
     * that is strictly greater than target, or "" if none exists.
     */
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if ((count[i] & 1) != 0) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        // A palindrome can have at most one character with an odd frequency.
        if (oddCount > 1) {
            return "";
        }

        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // First, check whether target's first half can be used unchanged.
        int[] remaining = halfCount.clone();
        StringBuilder prefix = new StringBuilder(half);
        boolean matchesTargetPrefix = true;

        for (int i = 0; i < half; i++) {
            int index = target.charAt(i) - 'a';
            if (remaining[index] == 0) {
                matchesTargetPrefix = false;
                break;
            }
            remaining[index]--;
            prefix.append(target.charAt(i));
        }

        if (matchesTargetPrefix) {
            String candidate = buildPalindrome(prefix, middle, n);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Otherwise, keep the longest possible prefix equal to target and
        // increase the rightmost position that can be made larger.
        for (int i = half - 1; i >= 0; i--) {
            remaining = halfCount.clone();
            boolean validPrefix = true;

            for (int j = 0; j < i; j++) {
                int index = target.charAt(j) - 'a';
                if (remaining[index] == 0) {
                    validPrefix = false;
                    break;
                }
                remaining[index]--;
            }

            if (!validPrefix) {
                continue;
            }

            int current = target.charAt(i) - 'a';
            int next = -1;
            for (int c = current + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    next = c;
                    break;
                }
            }

            if (next == -1) {
                continue;
            }

            remaining[next]--;

            StringBuilder left = new StringBuilder(half);
            left.append(target, 0, i);
            left.append((char) ('a' + next));

            // Once the first greater character is fixed, the smallest
            // completion is obtained by placing the remaining characters
            // in ascending order.
            for (int c = 0; c < 26; c++) {
                while (remaining[c] > 0) {
                    left.append((char) ('a' + c));
                    remaining[c]--;
                }
            }

            return buildPalindrome(left, middle, n);
        }

        return "";
    }

    private String buildPalindrome(CharSequence left, char middle, int n) {
        StringBuilder result = new StringBuilder(n);
        result.append(left);

        if ((n & 1) != 0) {
            result.append(middle);
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            result.append(left.charAt(i));
        }

        return result.toString();
    }
}
