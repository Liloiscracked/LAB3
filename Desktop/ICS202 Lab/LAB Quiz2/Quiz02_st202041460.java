public class Quiz02_st202041460 {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.root = new BSTNode<>(8);
        tree.insert(9);
        tree.insert(4);
        tree.insert(2);
        tree.insert(7);
        tree.insert(20);
        tree.insert(-1);
        tree.insert(-2);
        tree.insert(-10);
        tree.insert(-30);
        System.out.println("The original tree : \n");
        tree.printTree();
        System.out.println("The furthest leaf is : \n ");
        System.out.println(tree.deepestLeaff());
        System.out.println("The closest leaf is : \n ");
        System.out.println(tree.shallowestLeaff());
    }
}
