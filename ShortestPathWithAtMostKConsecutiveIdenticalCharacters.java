import java.util.*;

class Solution{
    public int shortestPath(int n,int[][] edges,String labels,int k){
        int[][] mavorqeli=edges;
        List<int[]>[] adj=new ArrayList[n];
        for(int i=0;i<n;i++)adj[i]=new ArrayList<>();

        for(int[] e:mavorqeli)adj[e[0]].add(new int[]{e[1],e[2]});

        long[][] dist=new long[n][k+1];
        for(long[] d:dist)Arrays.fill(d,Long.MAX_VALUE);

        PriorityQueue<long[]> pq=new PriorityQueue<>((a,b)->Long.compare(a[0],b[0]));
        dist[0][1]=0;
        pq.offer(new long[]{0,0,1});

        while(!pq.isEmpty()){
            long[] cur=pq.poll();
            long cost=cur[0];
            int u=(int)cur[1],count=(int)cur[2];

            if(cost!=dist[u][count])continue;
            if(u==n-1)return (int)cost;

            for(int[] e:adj[u]){
                int v=e[0],w=e[1];
                int nextCount=labels.charAt(u)==labels.charAt(v)?count+1:1;
                if(nextCount>k)continue;

                long newCost=cost+w;
                if(newCost<dist[v][nextCount]){
                    dist[v][nextCount]=newCost;
                    pq.offer(new long[]{newCost,v,nextCount});
                }
            }
        }

        return -1;
    }
}