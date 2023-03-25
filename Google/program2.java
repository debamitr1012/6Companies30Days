class Solution {
    public int maximumGood(int[][] statements) {  
        // Declare variable to store the max number of good people
        int[] maxGoodPeople = { 0 };      
        // Recursive function to find the maximum number of good people
        maximumGood(statements, new boolean[statements.length], 0, 0, maxGoodPeople);
        return maxGoodPeople[0];
    }
    private void maximumGood(int[][] statements, boolean[] goodPeople, int currentPerson, int currentGoodPeople, int[] maxGoodPeople) {
        // Base case: if all people have been checked
        if (currentPerson == goodPeople.length) {
            // Update maxGoodPeople if necessary
            maxGoodPeople[0] = Math.max(maxGoodPeople[0], currentGoodPeople);
            return;
        }
        // Set current person as good
        goodPeople[currentPerson] = true;
        // If remaining people cannot beat the current max, return
        if (goodPeople.length - currentPerson + currentGoodPeople < maxGoodPeople[0]) {
            return;
        }
        // If current configuration is valid, move forward
        if (isValid(statements, goodPeople, currentPerson)) {
            maximumGood(statements, goodPeople, currentPerson + 1, currentGoodPeople + 1, maxGoodPeople);
        }
        // Set current person as bad
        goodPeople[currentPerson] = false; 
        // If remaining people cannot beat the current max, return
        if (goodPeople.length - currentPerson - 1 + currentGoodPeople < maxGoodPeople[0]) {
            return;
        }
        // If current configuration is valid, move forward
        if (isValid(statements, goodPeople, currentPerson)) {
            maximumGood(statements, goodPeople, currentPerson + 1, currentGoodPeople, maxGoodPeople);
        } 
        return;
    }
    // Function to check if the current configuration is valid
    private boolean isValid(int[][] statements, boolean[] goodPeople, int currentPerson) {
        // Check if all previous people's statements are consistent with current configuration
        for (int i = 0; i < currentPerson; ++i) {
            if (goodPeople[i]) {
                if (goodPeople[currentPerson] && statements[i][currentPerson] == 0) {
                    return false;
                }
                if (!goodPeople[currentPerson] && statements[i][currentPerson] == 1) {
                    return false;
                }
            }
        }
        // Check if current person's statements are consistent with current configuration
        if (goodPeople[currentPerson]) {
            for (int i = 0; i < currentPerson; ++i) {
                if (goodPeople[i] && statements[currentPerson][i] == 0) return false;
                if (!goodPeople[i] && statements[currentPerson][i] == 1) return false;
            }
        }
        return true;
    }
}