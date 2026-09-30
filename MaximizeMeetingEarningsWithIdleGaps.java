import java.util.Arrays;

class Solution {
 public long maxEarnings(int[][] m) {
  Arrays.sort(m,(a,b)->a[1]-b[1]);
  int n=m.length;
  long[] dp=new long[n];

  for(int i=0;i<n;i++){
   dp[i]=m[i][2];
   int l=0,r=i-1;
   while(l<=r){
    int x=(l+r)/2;
    if(m[x][1]<=m[i][0]) l=x+1;
    else r=x-1;
   }
   if(r>=0) dp[i]=Math.max(dp[i],dp[r]+m[i][2]+m[i][0]-m[r][1]);
  }

  long ans=0;
  for(long x:dp) ans=Math.max(ans,x);
  return ans;
 }
}
