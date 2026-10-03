class Solution{
    public int longestValidParentheses(String s){
        int n=s.length(),ans=0;
        int[] stack=new int[n+1];
        int top=0;
        stack[0]=-1;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stack[++top]=i;
            }else{
                top--;
                if(top<0){
                    stack[++top]=i;
                }else{
                    ans=Math.max(ans,i-stack[top]);
                }
            }
        }
        return ans;
    }
}
