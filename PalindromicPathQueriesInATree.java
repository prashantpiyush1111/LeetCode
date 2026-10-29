import java.util.*;
class Solution{
int LOG;
int[][] up;
int[] depth,tin,tout,prefix;
int timer;
List<Integer>[] adj;
Fenwick bit;
public List<Boolean> palindromePath(int n,int[][] edges,String s,String[] queries){
adj=new ArrayList[n];
for(int i=0;i<n;i++)adj[i]=new ArrayList<>();
for(int[] e:edges){adj[e[0]].add(e[1]);adj[e[1]].add(e[0]);}
LOG=1;while((1<<LOG)<=n)LOG++;
up=new int[n][LOG];depth=new int[n];tin=new int[n];tout=new int[n];prefix=new int[n];
int[] parent=new int[n],iter=new int[n],stack=new int[n];
Arrays.fill(parent,-1);
int top=0;stack[0]=0;parent[0]=0;prefix[0]=1<<(s.charAt(0)-'a');tin[0]=timer++;
while(top>=0){int u=stack[top];if(iter[u]<adj[u].size()){int v=adj[u].get(iter[u]++);if(v==parent[u])continue;parent[v]=u;depth[v]=depth[u]+1;prefix[v]=prefix[u]^(1<<(s.charAt(v)-'a'));tin[v]=timer++;up[v][0]=u;for(int j=1;j<LOG;j++)up[v][j]=up[up[v][j-1]][j-1];stack[++top]=v;}else{tout[u]=timer-1;top--;}}
bit=new Fenwick(n);char[] chars=s.toCharArray();List<Boolean> ans=new ArrayList<>();
for(String q:queries){String[] p=q.split(" ");if(p[0].equals("update")){int u=Integer.parseInt(p[1]);char c=p[2].charAt(0);int diff=(1<<(chars[u]-'a'))^(1<<(c-'a'));bit.update(tin[u],diff);bit.update(tout[u]+1,diff);chars[u]=c;}else{int u=Integer.parseInt(p[1]),v=Integer.parseInt(p[2]);int l=lca(u,v);int mask=(prefix[u]^bit.query(tin[u]))^(prefix[v]^bit.query(tin[v]))^(1<<(chars[l]-'a'));ans.add((mask&(mask-1))==0);}}
return ans;
}
private int lca(int u,int v){if(depth[u]<depth[v]){int t=u;u=v;v=t;}int diff=depth[u]-depth[v];for(int j=0;j<LOG;j++)if((diff&(1<<j))!=0)u=up[u][j];if(u==v)return u;for(int j=LOG-1;j>=0;j--)if(up[u][j]!=up[v][j]){u=up[u][j];v=up[v][j];}return up[u][0];}
class Fenwick{int[] tree;int n;Fenwick(int n){this.n=n;tree=new int[n+1];}void update(int idx,int val){for(int i=idx+1;i<=n;i+=i&-i)tree[i]^=val;}int query(int idx){int res=0;for(int i=idx+1;i>0;i-=i&-i)res^=tree[i];return res;}}
}