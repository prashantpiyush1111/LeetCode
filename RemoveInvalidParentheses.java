class Solution{
    public List<String> removeInvalidParentheses(String s){
        List<String> ans=new ArrayList<>();
        remove(s,0,0,'(',')',ans);
        return ans;
    }
    private void remove(String s,int start,int last,char open,char close,List<String> ans){
        int balance=0;
        for(int i=start;i<s.length();i++){
            if(s.charAt(i)==open) balance++;
            else if(s.charAt(i)==close) balance--;
            if(balance<0){
                for(int j=last;j<=i;j++){
                    if(s.charAt(j)==close&&(j==last||s.charAt(j-1)!=close))
                        remove(s.substring(0,j)+s.substring(j+1),i,j,open,close,ans);
                }
                return;
            }
        }
        String rev=new StringBuilder(s).reverse().toString();
        if(open=='(')
            remove(rev,0,0,')','(',ans);
        else
            ans.add(rev);
    }
}
