public class traprainwater {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] height = {4, 2, 0, 3, 2, 5};

        int result = obj.trap(height);
        System.out.println("Trapped Water: " + result);
    }
}

class Solution {

    public int trap(int[] height) {
        int n = height.length;
        int totalWater = 0;
        int leftMax = 0;
        int rightMax = 0;
        int start = 0;
        int end = n - 1;

        while (start < end) {

            leftMax = Math.max(leftMax, height[start]);
            rightMax = Math.max(rightMax, height[end]);

            if (leftMax < rightMax) {
                totalWater += leftMax - height[start];
                start++;
            } else {
                totalWater += rightMax - height[end];
                end--;
            }
        }
        return totalWater;
    }
}