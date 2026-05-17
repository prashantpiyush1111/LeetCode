import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 8, 12, 15};
        int target = 10;
        TwoSum obj = new TwoSum();
        int[] result = obj.twoSum(nums, target);
        System.out.println(result[0] + ", " + result[1]);
    }

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{ map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No Match Found");
    }
}
