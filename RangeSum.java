import java.util.Scanner;

class MyNumArray {

    int[] preSum;

    public MyNumArray(int[] nums) {

        preSum = nums;

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] += preSum[i - 1];
        }
    }

    public int sumRange(int left, int right) {

        if (left == 0) {
            return preSum[right];
        }

        return preSum[right] - preSum[left - 1];
    }
}

public class RangeSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        MyNumArray obj = new MyNumArray(nums);

        System.out.print("Enter left index: ");
        int left = sc.nextInt();

        System.out.print("Enter right index: ");
        int right = sc.nextInt();

        int result = obj.sumRange(left, right);

        System.out.println("Sum Range Result = " + result);

        sc.close();
    }
}