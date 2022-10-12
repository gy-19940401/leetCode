package face;

/**
 * @Description: 后继者
 * @author: GanYang
 * @Date: 2022/9/30 17:27
 * <p>
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * <p>
 * 二叉搜索树{
 * 1、左子树的所有节点都比根节点小
 * 2、右子树的所有节点都比根节点大
 * 找前驱：就是在左子树中找最右节点
 * 找后继：就是在右子树中找最左节点
 * 特殊：如果目标节点没得 右子树 且 目标节点位于 根的 左侧  就向上找父亲节点 若无 则 null
 * <p>
 * }
 * <p>
 * https://leetcode.cn/problems/successor-lcci/
 */
public class Face220406SuccessorLcci {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        treeNode1.left = new TreeNode(1);
        treeNode1.right = new TreeNode(3);


        TreeNode result = inorderSuccessor(treeNode1, new TreeNode(1));

        System.out.println(result.val);
    }

    /**
     * 1、如果 查找树的节点 的根节点是 小于 {等于？？？} 目标节点的 就证明 后继节点不在 查找树的 左子树下
     * 就需要在 右子树下找最左的节点
     * 2、 如果 查找树的根节点 大于 目标节点 就只需要在 根节点的左树下查找 如果找不到 就是root
     */
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val <= p.val) {
            //当根前节点小于目标节点，在根节点的 右树 找最左节点
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode treeNode = inorderSuccessor(root.left, p);
            return treeNode == null ? root : treeNode;
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
