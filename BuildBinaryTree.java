import java.util.HashMap;

class BuildBinaryTree {
    static int index = 0;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(preorder, 0, inorder.length - 1, map);
    }

    private static TreeNode helper(int[] preorder, int start, int end, HashMap<Integer, Integer> map) {
        if (start > end) return null;

        int rootVal = preorder[index++];
        TreeNode node = new TreeNode(rootVal);

        int inorderIndex = map.get(rootVal);

        node.left = helper(preorder, start, inorderIndex - 1, map);
        node.right = helper(preorder, inorderIndex + 1, end, map);

        return node;
    }

    public static void printInorder(TreeNode root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = buildTree(preorder, inorder);

        System.out.println("Constructed Tree Inorder Traversal:");
        printInorder(root);
    }
}