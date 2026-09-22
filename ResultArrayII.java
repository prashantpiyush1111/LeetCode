class Solution{
    class Node{
        int prod;
        int[] cnt;
        Node(int k){
            prod=1;
            cnt=new int[k];
        }
    }
    class SegmentTree{
        int k;
        Node[] tree;
        SegmentTree(int[] nums,int k){
            this.k=k;
            int n=nums.length;
            tree=new Node[4*n];
            build(1,0,n-1,nums);
        }
        Node merge(Node a,Node b){
            Node c=new Node(k);
            c.prod=a.prod*b.prod%k;
            for(int i=0;i<k;i++) c.cnt[i]=a.cnt[i];
            for(int i=0;i<k;i++) c.cnt[a.prod*i%k]+=b.cnt[i];
            return c;
        }
        void build(int u,int l,int r,int[] nums){
            tree[u]=new Node(k);
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
                val%=k;
                tree[u]=new Node(k);
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
            Node left=query(u*2,l,m,ql,qr);
            Node right=query(u*2+1,m+1,r,ql,qr);
            return merge(left,right);
        }
    }
    public int[] resultArray(int[] nums,int k,int[][] queries){
        int n=nums.length;
        SegmentTree tree=new SegmentTree(nums,k);
        int[] ans=new int[queries.length];
        for(int i=0;i<queries.length;i++){
            int idx=queries[i][0];
            int val=queries[i][1];
            int start=queries[i][2];
            int x=queries[i][3];
            tree.update(1,0,n-1,idx,val);
            ans[i]=tree.query(1,0,n-1,start,n-1).cnt[x];
        }
        return ans;
    }
}
