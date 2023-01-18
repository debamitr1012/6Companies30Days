class Node {
    String word;
    int index;
    Node(String word, int index) {
        this.word = word;
        this.index = index;
    }
}
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        ArrayList<Node>[] buckets = new ArrayList[26];
        for (int i = 0; i < 26; ++i) buckets[i] = new ArrayList<>();
        for (String word : words) {
            char startingChar = word.charAt(0);
            buckets[startingChar-'a'].add(new Node(word, 0));
        }
        int ans = 0;
        for (char c : s.toCharArray()) {
            ArrayList<Node> currBucket = buckets[c-'a'];
            buckets[c-'a'] = new ArrayList<>();
            for (Node node : currBucket) {
                node.index += 1; 
                if (node.index == node.word.length()) {
                    ans += 1;
                } else {
                    char startingChar = node.word.charAt(node.index);
                    buckets[startingChar - 'a'].add(node);
                }
            }
        }
        return ans;
    }
}