class Solution {
    public int findIntegers(int n) {
        //Let's convert the number to binary string -ex 5=101
        String bin=Integer.toBinaryString(n);
        //find the length k=3 for 101
        int k=bin.length();
        //find the fibo sequence to it
        //if(k==0)-. 1 combination
        //if(k==1)- either 0 or 1, so 2 combination
        //if(k==2)- 00,01,10,11 but 11 will be left so 3 comb
        //if(k==3)-000,001,010,100,101 - 5 comb with no cons ones
        //so fib[k]=fib[k-1]+fib[k-2];
        int fib[]=new int[k+1];
        fib[0]=1;
        fib[1]=2;
        for(int i=2;i<=k;i++){
            fib[i]=fib[i-1]+fib[i-2];
        }
        //now initially,we say the last digit is not one
        boolean isOne=false;
        //we leave the last digit and start checking from k-1 to 0
        int bit=k-1;
        int ct=0;
        while(bit>=0){
            //we check if the digit is 0,so it is the last and hence lastdigit is not one
            if((n&1<<bit) ==0){
                isOne=false;
            }
            //if the digit is 1,we add the fib of bit length till that place to ct
            else{
                ct+=fib[bit];
                //if already a digit was there as 1 we cant do any operations furthur so return it
                if(isOne){
                    return ct;
                }
                //else make last one as true
                isOne=true;
            }
            bit--;
        }
        //if it reaches here,means that the last digit is 0 now so we can append anything to it i.e. the digit that was left out earlier,kth digit,so we add 1 to ct and return
        return ct+1;
    }
}