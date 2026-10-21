class Solution{
    public int minOperations(String s){
        int n=s.length(),min=26,max=-1;
        boolean sorted=true;
        for(int i=0;i<n;i++){
            min=Math.min(min,s.charAt(i)-'a');
            max=Math.max(max,s.charAt(i)-'a');
            if(i>0&&s.charAt(i)<s.charAt(i-1))sorted=false;
        }
        if(sorted)return 0;
        if(n==2)return -1;
        if(s.charAt(0)==min+'a'||s.charAt(n-1)==max+'a')return 1;
        for(int i=1;i<n-1;i++)if(s.charAt(i)==min+'a'||s.charAt(i)==max+'a')return 2;
        return 3;
    }
}