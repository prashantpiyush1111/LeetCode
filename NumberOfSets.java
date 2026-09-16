class Solution{
    public int numberOfSets(int n,int k){
        long mod=1000000007;
        int N=n+k-1;
        long[] fact=new long[N+1];
        long[] inv=new long[N+1];
        fact[0]=1;
        for(int i=1;i<=N;i++) fact[i]=fact[i-1]*i%mod;
        inv[N]=pow(fact[N],mod-2,mod);
        for(int i=N;i>0;i--) inv[i-1]=inv[i]*i%mod;
        return (int)(fact[N]*inv[2*k]%mod*inv[N-2*k]%mod);
    }
    private long pow(long a,long b,long mod){
        long res=1;
        while(b>0){
            if((b&1)==1) res=res*a%mod;
            a=a*a%mod;
            b>>=1;
        }
        return res;
    }
}
