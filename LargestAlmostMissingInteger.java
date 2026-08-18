import java.util.*;

public class LargestAlmostMissingInteger {
    public static int largestAlmostMissing(int[] nums, int k) {
        int n = nums.length;
        int ans = -1;
        for (int x : nums) {
            int cnt = 0;
            for (int i = 0; i + k <= n; i++) {
                for (int j = i; j < i + k; j++) {
                    if (nums[j] == x) {
                        cnt++;
                        break;
                    }
                }
            }
            if (cnt == 1) ans = Math.max(ans, x);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(largestAlmostMissing(nums, k));
    }
}