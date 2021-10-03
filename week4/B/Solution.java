//program to find greatest restaurant value
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
class Solution {
    public static int largestRestaurantValue(String restaurant, int[][] edges) {
       ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
        int n=restaurant.length();
        char[] color=restaurant.toCharArray();
        
        for(int i=0;i<n;i++) graph.add(i,new ArrayList<>());
        int[] indegree=new int[n];
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            indegree[v]++;
            graph.get(u).add(v);
        }
        
	
        int[][] map=new int[n][26]; 
        
	
        Queue<Integer> que=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                que.add(i);
                map[i][color[i]-'a']=1;
            }
        }
        
        int res=0;
        int seen=0;
        
        while(que.size()>0){
            int node=que.remove();
            seen++;
            
            int max=getMax(map[node]);
            res=Math.max(res,max);
            
            for(int nbr:graph.get(node)){
                
                for(int i=0;i<26;i++){
                    map[nbr][i]=Math.max(map[nbr][i],map[node][i] + (color[nbr]-'a'==i?1:0));
                }
                indegree[nbr]--;
                
                if(indegree[nbr]==0){
                    que.add(nbr);
                }
            }
        }
        
        return seen==n?res:-1;
    }
    
    private static int getMax(int[] num){
        int max=num[0];
        for(int n:num){
            max=Math.max(n,max);
        }
        return max;
    }
    public static void main (String[] args) {
        String restaurant ="abaca";
        int[][] edges = {{0,1},{0,2},{2,3},{3,4}};
        int ans = largestRestaurantValue(restaurant,edges);
        System.out.println(ans);
    }
   
}
