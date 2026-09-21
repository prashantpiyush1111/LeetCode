class Solution{
    public long[] resultArray(int[] nums,int k){
        long[] ans=new long[k];
        long[] prev=new long[k];
        for(int num:nums){
            long[] cur=new long[k];
            cur[num%k]++;
            for(int r=0;r<k;r++){
                cur[(int)((long)r*num%k)]+=prev[r];
            }
            for(int r=0;r<k;r++) ans[r]+=cur[r];
            prev=cur;
        }
        return ans;
    }
}
