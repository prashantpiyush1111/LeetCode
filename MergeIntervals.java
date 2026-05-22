import java.util.*;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<List<Integer>> merged = new ArrayList<>();

        for (int[] interval : intervals) {

            if (merged.isEmpty() ||
                merged.get(merged.size() - 1).get(1) < interval[0]) {

                merged.add(Arrays.asList(interval[0], interval[1]));

            } else {

                int last = merged.size() - 1;

                int maxEnd = Math.max(
                    merged.get(last).get(1),
                    interval[1]
                );

                merged.get(last).set(1, maxEnd);
            }
        }

        int[][] ans = new int[merged.size()][2];

        for (int i = 0; i < merged.size(); i++) {
            ans[i][0] = merged.get(i).get(0);
            ans[i][1] = merged.get(i).get(1);
        }

        return ans;
    }

    public static void main(String[] args) {
        MergeIntervals obj = new MergeIntervals();

        int[][] intervals = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };

        int[][] result = obj.merge(intervals);

        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}