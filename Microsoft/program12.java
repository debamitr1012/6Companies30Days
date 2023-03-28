class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        char a [] = s.toCharArray();
        int lps [] = new int [n];
        int len = 0, i = 1;
        String rtn ="";
        while(i < n)
        {
            if(a[len] == a[i])
            {
                len++;
                lps[i] = len;
                i++;
            }
            else
            {
                if(len == 0)
                {
                    lps[i] = 0;
                    i++;
                }
                else
                    len = lps[len -1];
            }
        }
        rtn = s.substring(n-lps[n-1],n);
        return rtn;
    }
}