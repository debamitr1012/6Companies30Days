class Solution {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length; 
        int max = 0; 
        for(int i = n/3; i < n-1; i+=2) {
            max += piles[i]; 
        }
        return max; 
    }
}