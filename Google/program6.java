class Solution {
    long hash[];
    long pow[];
    long r=256;
    long mod=(long)Math.pow(10,9)+7;
    public void process(String s,int n)
    {
        hash=new long[n];
        pow=new long[n];
        pow[0]=1;
        for(int i=1;i<n;i++)
        {
            hash[i]=(hash[i-1]*r+s.charAt(i))%mod;
            pow[i]=(pow[i-1]*r)%mod;
        }
    }
    public long calc(int l,int r)
    {
        long hashValue=(hash[r]-hash[l]*pow[r-l]%mod+mod)%mod;
        return hashValue;
    }
    public int distinctEchoSubstrings(String text) {
        int n=text.length();
        process(text,n);
        HashSet<Long> hset=new HashSet<>();
        for(int len=1;len<=n/2;len++)
        {
            int c=0;
            for(int l=0,r=len;r<n;l++,r++)
            {
                if(text.charAt(l)==text.charAt(r))
                {
                   c++; 
                }
                else
                {
                    c=0;
                }
                if(c==len)
                {
                    long hv=calc(l,r);
                    hset.add(hv);
                    c--;
                }
            }
        }
        return hset.size();
    }
}