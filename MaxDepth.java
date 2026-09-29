class MaxDepth{
    public int maxDepth(String s){
        int depth=0,answ=0;
        for(char c:s.toCharArray()){
            if(c=='('){
                depth++;
                answ=Math.max(answ,depth);
            }else if(c==')'){
                depth--;
            }
        }
        return answ;
    }
}


