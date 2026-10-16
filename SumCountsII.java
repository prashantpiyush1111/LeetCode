class Solution{
    static final long MOD=1000000007L;
    public int sumCounts(int[] nums){
        int n=nums.length;
        long[] sum=new long[4*n],sq=new long[4*n],lazy=new long[4*n];
        Map<Integer,Integer> last=new HashMap<>();
        long ans=0,cur=0;
        for(int i=0;i<n;i++){
            int l=last.getOrDefault(nums[i],-1)+1;
            update(1,0,n-1,l,i,1,sum,sq,lazy);
            cur=sq[1];
            ans=(ans+cur)%MOD;
            last.put(nums[i],i);
        }
        return (int)ans;
    }
    void update(int node,int lo,int hi,int l,int r,long v,long[] sum,long[] sq,long[] lazy){
        if(l>hi||r<lo)return;
        if(l<=lo&&hi<=r){
            sq[node]=(sq[node]+2*v*sum[node]+v*v*(hi-lo+1))%MOD;
            sum[node]=(sum[node]+v*(hi-lo+1))%MOD;
            lazy[node]=(lazy[node]+v)%MOD;
            return;
        }
        push(node,lo,hi,sum,sq,lazy);
        int mid=(lo+hi)/2;
        update(node*2,lo,mid,l,r,v,sum,sq,lazy);
        update(node*2+1,mid+1,hi,l,r,v,sum,sq,lazy);
        sum[node]=(sum[node*2]+sum[node*2+1])%MOD;
        sq[node]=(sq[node*2]+sq[node*2+1])%MOD;
    }
    void push(int node,int lo,int hi,long[] sum,long[] sq,long[] lazy){
        if(lazy[node]==0||lo==hi)return;
        int mid=(lo+hi)/2;
        apply(node*2,lo,mid,lazy[node],sum,sq,lazy);
        apply(node*2+1,mid+1,hi,lazy[node],sum,sq,lazy);
        lazy[node]=0;
    }
    void apply(int node,int lo,int hi,long v,long[] sum,long[] sq,long[] lazy){
        sq[node]=(sq[node]+2*v*sum[node]+v*v*(hi-lo+1))%MOD;
        sum[node]=(sum[node]+v*(hi-lo+1))%MOD;
        lazy[node]=(lazy[node]+v)%MOD;
    }
}
