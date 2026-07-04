import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalInserter {
    
    // Method to insert and merge intervals
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int n = intervals.length;
        int i = 0;
        List<int[]> list = new ArrayList<>();

        // -----------------------------------
        // PHASE 1:
        // Add intervals before newInterval
        // -----------------------------------
        while (i < n && newStart > intervals[i][1]) {
            list.add(intervals[i]);
            i++;
        }

        // -----------------------------------
        // PHASE 2:
        // Merge overlapping intervals
        // -----------------------------------
        while (i < n && newEnd >= intervals[i][0]) {
            // Expand newInterval boundaries
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add merged interval
        list.add(newInterval);

        // -----------------------------------
        // PHASE 3:
        // Add remaining intervals
        // -----------------------------------
        while (i < n) {
            list.add(intervals[i]);
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }

    // Main function to run and test the code
    public static void main(String[] args) {
        IntervalInserter inserter = new IntervalInserter();

        // Test Case 1
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        int[][] result1 = inserter.insert(intervals1, newInterval1);
        System.out.println("Test Case 1 Output: " + Arrays.deepToString(result1));
        // Expected Output: [[1, 5], [6, 9]]

        // Test Case 2
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        int[][] result2 = inserter.insert(intervals2, newInterval2);
        System.out.println("Test Case 2 Output: " + Arrays.deepToString(result2));
        // Expected Output: [[1, 2], [3, 10], [12, 16]]
    }
}
