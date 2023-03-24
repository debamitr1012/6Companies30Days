class Solution {
    public int findKthNumber(int n, int k) {
        int prefix=1;
        for(int count=1;count<k;){
            int currCount=getCountWithPrefix(prefix,prefix+1,n);
            //if total count of elements < k, our answer doesn't lies with that prefix 
            if(currCount+count<=k){
                count+=currCount;
                prefix++;
            }else{
                prefix*=10; //if count of elements > k our answer lies with that prefix
                count++;    //skip to next element
            }
        }
        return prefix;
    }
    private int getCountWithPrefix(long startPrefix,long endPrefix,int max){
        int count=0;
        while(startPrefix<=max){
            //elements between startPrefix and endPrefix
            count+=Math.min(max+1,endPrefix)-startPrefix; //max+1 since max is included
            //increase the digits
            startPrefix*=10;
            endPrefix*=10;
        }
        return count;
    }
}