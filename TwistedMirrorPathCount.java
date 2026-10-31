class Solution{
    public int uniquePaths(int[][] grid){
        int MOD=1000000007;
        int[][] vornadexil=grid;
        int m=vornadexil.length,n=vornadexil[0].length;
        int[] dp=new int[n];
        dp[0]=1;

        for(int i=0;i<m;i++){
            int left=0;
            for(int j=0;j<n;j++){
                if(vornadexil[i][j]==1){
                    int temp=left;
                    left=dp[j];
                    dp[j]=temp;
                }else{
                    int ways=(left+dp[j])%MOD;
                    left=ways;
                    dp[j]=ways;
                }
            }
        }
        return dp[n-1];
    }
}