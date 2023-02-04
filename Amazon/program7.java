class Solution {
    public String predictPartyVictory(String senates) {
        char[] senate = senates.toCharArray();
        int bansForR = 0;
        int bansForD = 0;
        int totalBannedR = 0;
        int totalBannedD = 0;
        int totalR = 0;
        int totalD = 0;
        for (int i=0; i<senate.length; i++) {
            char memberParty = senate[i];
            if (memberParty == 'R') {
                totalR++;
            } else {
                totalD++;
            }
        }
        int totalSenators = senate.length;
        for (int i=0; true; i++) {
            if (i==senate.length) {
                i=0;
            }
            char memberParty = senate[i];
            if (memberParty == 'R') {
                if (bansForR>0) {                
                    bansForR--;
                    senate[i] = 'X';
                } else {
                    bansForD++;
                    totalBannedD++;
                    if (totalBannedD >= totalD) {
                        return "Radiant";
                    }
                }
            } else if (memberParty == 'D') {
                if (bansForD>0) {
                    bansForD--;
                    senate[i] = 'X';
                } else {
                    bansForR++;
                    totalBannedR++;
                    if (totalBannedR >= totalR) {
                        return "Dire";
                    }
                }
            }
        }
    }
}