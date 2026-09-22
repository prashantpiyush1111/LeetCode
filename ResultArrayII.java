class Solution{
    int k;
    Node[] tree;
    class Node{
        int prod;
        int[] cnt;
        Node(){
            prod=1%k;
            cnt=new int[k];
        }
    }
    Node merge(Node a,Node b){
        Node c=new Node();
        c.prod=(int)((long)a.prod*b.prod%k);
        for(int i=0;i<k;i++){
            c.cnt[i]=a.cnt[i];
            c.cnt[(int)((long)i*a.prod%k)]+=b.cnt[i];
        }
        return c;
    }
    void build(int u,int l,int r,int[] nums){
        if(l==r){
            int v=nums[l]%k;
            tree[u].prod=v;
            tree[u].cnt[v]=1;
            return;
        }
        int m=(l+r)/2;
        build(u*2,l,m,nums);
        build(u*2+1,m+1,r,nums);
        tree[u]=merge(tree[u*2],tree[u*2+1]);
    }
    void update(int u,int l,int r,int idx,int val){
        if(l==r){
            tree[u]=new Node();
            val%=k;
            tree[u].prod=val;
            tree[u].cnt[val]=1;
            return;
        }
        int m=(l+r)/2;
        if(idx<=m) update(u*2,l,m,idx,val);
        else update(u*2+1,m+1,r,idx,val);
        tree[u]=merge(tree[u*2],tree[u*2+1]);
    }
    Node query(int u,int l,int r,int ql,int qr){
        if(ql<=l&&r<=qr) return tree[u];
        int m=(l+r)/2;
        if(qr<=m) return query(u*2,l,m,ql,qr);
        if(ql>m) return query(u*2+1,m+1,r,ql,qr);
        return merge(query(u*2,l,m,ql,qr),query(u*2+1,m+1,r,ql,qr));
    }
    public int[] resultArray(int[] nums,int k,int[][] queries){
        this.k=k;
        int n=nums.length;
        tree=new Node[4*n];
        for(int i=0;i<4*n;i++) tree[i]=new Node();
        build(1,0,n-1,nums);
        int[] ans=new int[queries.length];
        for(int i=0;i<queries.length;i++){
            int idx=queries[i][0];
            int val=queries[i][1];
            int start=queries[i][2];
            int x=queries[i][3];
            update(1,0,n-1,idx,val);
            ans[i]=query(1,0,n-1,start,n-1).cnt[x];
        }
        return ans;
    }
}
