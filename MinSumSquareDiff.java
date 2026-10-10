class Solution{
    public long minSumSquareDiff(int[] nums1,int[] nums2,int k1,int k2){
        int n=nums1.length;
        int[] diff=new int[n];
        int max=0;
        long sum=0;
        for(int i=0;i<n;i++){
            diff[i]=Math.abs(nums1[i]-nums2[i]);
            max=Math.max(max,diff[i]);
            sum+=diff[i];
        }
        long k=(long)k1+k2;
        if(k>=sum) return 0;
        int lo=0,hi=max;
        while(lo<hi){
            int mid=(lo+hi)/2;
            long need=0;
            for(int d:diff) if(d>mid) need+=d-mid;
            if(need<=k) hi=mid;
            else lo=mid+1;
        }
        int level=lo;
        long need=0,ans=0;
        for(int d:diff){
            if(d>level) need+=d-level;
            int x=Math.min(d,level);
            ans+=(long)x*x;
        }
        long rem=k-need;
        for(int i=0;i<n&&rem>0;i++){
            if(diff[i]>=level&&diff[i]>0){
                ans-=2L*level-1;
                rem--;
            }
        }
        return ans;
    }
}
