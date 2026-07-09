import java.util.*;

public class ShortestWordDistanceII244 {

    static class WordDistance {
        private Map<String, List<Integer>> map;

        public WordDistance(String[] wordsDict) {
            map = new HashMap<>();

            for (int i = 0; i < wordsDict.length; i++) {
                map.putIfAbsent(wordsDict[i], new ArrayList<>());
                map.get(wordsDict[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);

            int i = 0, j = 0;
            int minDistance = Integer.MAX_VALUE;

            while (i < list1.size() && j < list2.size()) {
                minDistance = Math.min(minDistance,
                        Math.abs(list1.get(i) - list2.get(j)));

                if (list1.get(i) < list2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }

            return minDistance;
        }
    }

    public static void main(String[] args) {
        String[] words = {
                "practice", "makes", "perfect",
                "coding", "makes"
        };

        WordDistance wd = new WordDistance(words);

        System.out.println(wd.shortest("coding", "practice")); // 3
        System.out.println(wd.shortest("makes", "coding"));    // 1
    }
}