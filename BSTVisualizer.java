public class BSTVisualizer {
    public String traversePreOrder(BSTNode root) {
        if (root == null) {
            return "(empty)";
        }

        StringBuilder sb = new StringBuilder();
        traverseNodes(sb, "", "", root, false);
        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer, BSTNode node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.data);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("|  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "\\--";
            String pointerLeft = (node.right != null) ? "|--" : "\\--";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);
        }
    }

    public void printBST(BSTNode root) {
        System.out.println(traversePreOrder(root));
    }
}


