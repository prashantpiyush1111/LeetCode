import java.util.*;

class SubstringConcatenationFinder {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int n = words.length, k = words[0].length(), len = s.length();
        if (len < n * k) return res;
        Map<String, Integer> wMap = new HashMap<>();
        for (String w : words) wMap.merge(w, 1, Integer::sum);
        for (int off = 0; off < k; off++) {
            Map<String, Integer> cur = new HashMap<>();
            int left = off, count = 0;
            for (int right = off; right + k <= len; right += k) {
                String w = s.substring(right, right + k);
                if (!wMap.containsKey(w)) {
                    cur.clear(); count = 0; left = right + k; continue;
                }
                cur.merge(w, 1, Integer::sum);
                count++;
                while (cur.get(w) > wMap.get(w)) {
                    cur.merge(s.substring(left, left + k), -1, Integer::sum);
                    count--; left += k;
                }
                if (count == n) {
                    res.add(left);
                    cur.merge(s.substring(left, left + k), -1, Integer::sum);
                    count--; left += k;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubstringConcatenationFinder finder = new SubstringConcatenationFinder();

        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        System.out.println("Test 1: " + finder.findSubstring(s1, words1)); // [0, 9]

        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "word"};
        System.out.println("Test 2: " + finder.findSubstring(s2, words2)); // []

        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar", "foo", "the"};
        System.out.println("Test 3: " + finder.findSubstring(s3, words3)); // [6, 9, 12]
    }
}