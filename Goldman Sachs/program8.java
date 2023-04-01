class Solution {
    int[] optimalStrategy; // optimal strategy for Bob, in terms of number of arrows used for each round
    int maxPoints = 0; // maximum number of points Bob can earn
    public int[] maximumBobPoints(int bobArrows, int[] aliceArrows) {
        // initialize optimalStrategy array and set all elements to 0
        optimalStrategy = new int[aliceArrows.length];
        compute(bobArrows, aliceArrows, new int[aliceArrows.length], aliceArrows.length - 1, 0);
        return optimalStrategy;
    }
    private void compute(int bobArrows, int[] aliceArrows, int[] currentStrategy, int currentRound, int currentPoints) {
        // base case: if we've reached the last round, check if this is the best strategy so far
        if (currentRound == 0) {
            if (currentPoints > maxPoints) {
                maxPoints = currentPoints;
                currentStrategy[currentRound] = bobArrows;
                optimalStrategy = currentStrategy.clone();
            }
            return;
        }
        // try not using any arrows in this round
        compute(bobArrows, aliceArrows, currentStrategy, currentRound - 1, currentPoints);
        // if Bob has enough arrows to beat Alice's score in this round, try using some arrows
        if (bobArrows > aliceArrows[currentRound]) {
            currentStrategy[currentRound] = aliceArrows[currentRound] + 1;
            compute(bobArrows - currentStrategy[currentRound], aliceArrows, currentStrategy, currentRound - 1, currentPoints + currentRound);
            currentStrategy[currentRound] = 0;
        }
    }
}