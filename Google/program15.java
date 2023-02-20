class Solution {
    Integer[] dp;
    int ALL;
    int[][] students;
    int[][] mentors;
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        final int nStudents = students.length;
        final int nAnswers = students[0].length;
        this.students = students;
        this.mentors = mentors;

        ALL = (1<<nStudents)-1;
        dp = new Integer[ALL + 1];
        return dfs(0,0, nAnswers);
    }
    int dfs(int iStudent, int assignedMentors, int nAnswers){
        if (assignedMentors == ALL) return 0;
        if(iStudent >= students.length) return 0;
        if(dp[assignedMentors] != null) return dp[assignedMentors];
        int max = 0;
        for (int iMentor = 0; iMentor < mentors.length; iMentor++) {
            if ((assignedMentors & (1 << iMentor)) == 0) {
                final int curr = getScore(students[iStudent], mentors[iMentor], nAnswers);
                final int right = dfs(iStudent + 1, assignedMentors | (1 << iMentor), nAnswers);
                max = Math.max(max, curr + right);
            }
        }
        return dp[assignedMentors] = max;
    }
    int getBits(int[] A){
        int res = 0;
        for (int a : A) {
            res |= a;
            res <<= 1;
        }
        res = res >> 1;
        return res;
    }
    int getScore(int[] a, int[] b, int size){
        return Integer.bitCount(getBits(a) ^ ~getBits(b)) -32 + size;
    }
}