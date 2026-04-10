import java.util.*;

// Tree Node class
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class binaryTreePaths {

    // Function to get all root-to-leaf paths
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        helper(root, "", result);
        return result;
    }

    // Helper function (Recursion)
    private static void helper(TreeNode node, String path, List<String> result) {
        if (node == null) return;

        // Build path
        if (path.length() == 0) {
            path = "" + node.val;
        } else {
            path = path + "->" + node.val;
        }

        // Leaf node
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        // Recursive calls
        helper(node.left, path, result);
        helper(node.right, path, result);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Safe input
        System.out.print("Enter root value: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input! Integer required.");
            return;
        }
        int rootVal = sc.nextInt();

        // Create tree manually (simple)
        TreeNode root = new TreeNode(rootVal);

        System.out.print("Enter left child of root: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            return;
        }
        root.left = new TreeNode(sc.nextInt());

        System.out.print("Enter right child of root: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            return;
        }
        root.right = new TreeNode(sc.nextInt());

        System.out.print("Enter right child of left node: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            return;
        }
        root.left.right = new TreeNode(sc.nextInt());

        // Get paths
        List<String> paths = binaryTreePaths(root);

        System.out.println("Paths: " + paths);
    }
}