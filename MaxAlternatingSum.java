import java.util.Arrays;

class Solution{
    public long maxAlternatingSum(int[] nums){
        long[] a=new long[nums.length];
        for(int i=0;i<nums.length;i++) a[i]=(long)nums[i]*nums[i];
        Arrays.sort(a);
        long ans=0;
        for(int i=0;i<a.length;i++){
            if(i<a.length/2) ans-=a[i];
            else ans+=a[i];
        }
        return ans;
    }
}
