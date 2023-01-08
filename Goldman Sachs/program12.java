class Solution {
        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        inorder(root1, ans);
        inorder(root2, ans);
        Collections.sort(ans); 
        return ans;
    }
    private void inorder(TreeNode n, List<Integer> ans) {
        if (n == null) return;
        inorder(n.left, ans);
        ans.add(n.val);
        inorder(n.right, ans);
    }
}