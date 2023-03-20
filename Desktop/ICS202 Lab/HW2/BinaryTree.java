import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinaryTree<T extends Comparable<T>> {
    BTNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(BTNode root) {
        root = null;
    }

    public void purge(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(T key){
        if (root == null) {
            root = new BTNode(key);
            return;
        }

        BTNode temp;
        Queue<BTNode> q = new LinkedList<BTNode>();
        q.add(root);

        // Do level order traversal until we find the first empty left or right child.
        while (!q.isEmpty()) {
            temp = q.poll();

            if (temp.left == null) {
                temp.left = new BTNode(key);
                break;
            }
            else
                q.add(temp.left);

            if (temp.right == null) {
                temp.right = new BTNode(key);
                break;
            }
            else
                q.add(temp.right);
        }
    }

    // delete by copying last node
    public void deleteByCopying(T data){
        if(root == null)
            throw new UnsupportedOperationException("Tree is empty!");
        else if(root.left == null && root.right == null){
            if(root.data.equals(data))
                root = null;
            else
                throw new NoSuchElementException(data + " not in the tree.");
            return;
        }

        Queue<BTNode> queue = new LinkedList<BTNode>();
        queue.add(root);
        BTNode keyNode = null;
        BTNode currentNode = null;
        BTNode parentNode = root;
        boolean found = false;
        while(! queue.isEmpty()){
            currentNode = queue.poll();

            if(currentNode.data.equals(data)){
                if(! found){
                    keyNode = currentNode;
                    found = true;
                }
            }

            if(currentNode.left != null){
                queue.add(currentNode.left);
                parentNode = currentNode;
            }
            if(currentNode.right != null){
                queue.add(currentNode.right);
                parentNode = currentNode;
            }
        }

        if(! found)
            throw new NoSuchElementException(data + " not in tree.");

        while(! queue.isEmpty()){
            currentNode = queue.poll();
            System.out.print(currentNode.data + "  ");
            if(currentNode.left != null){
                queue.add(currentNode.left);
                parentNode = currentNode;
            }
            if(currentNode.right != null){
                queue.add(currentNode.right);
                parentNode = currentNode;
            }
        }

        keyNode.data = currentNode.data;
        if(parentNode.left == currentNode)
            parentNode.left = null;
        else if(parentNode.right == currentNode)
            parentNode.right = null;
    }

    public void levelOrderTraversal(){ // BreadthFirstTraversal
        levelOrderTraversal(root);
    }

    // BreadthFirst Traversal
    protected void levelOrderTraversal(BTNode root){
        if(root == null)
            return;
        Queue<BTNode> queue = new LinkedList<BTNode>();
        BTNode node = root;
        queue.add(node);
        while(! queue.isEmpty()){
            node = queue.poll();
            visit(node);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }

    public void levelOrderTraversalByLevels(){
        levelOrderTraversalByLevels(root);
    }

    protected void levelOrderTraversalByLevels(BTNode<T> root){
        Queue q = new LinkedList();
        int levelNodes = 0;
        if(root==null)
            return;

        q.add(root);
        while(!q.isEmpty()){
            levelNodes = q.size();
            while(levelNodes>0){
                BTNode n = (BTNode)q.remove();
                System.out.print(" " + n.data);
                if(n.left!=null)
                    q.add(n.left);
                if(n.right!=null)
                    q.add(n.right);
                levelNodes--;
            }
            System.out.println("");
        }
    }

    protected void visit(BTNode<T> p) {
        System.out.print(p.data + " ");
    }

    public void inorderTraversal(){
        inorderTraversal(root);
    }

    protected void inorderTraversal(BTNode node) {
        if (node == null)
            return;

        inorderTraversal(node.left);
        visit(node);
        inorderTraversal(node.right);
    }

    public void postorderTraversal(){
        postorderTraversal(root);
    }

    protected void postorderTraversal(BTNode node){
        if (node == null)
            return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        visit(node);
    }

    public void preorderTraversal(){
        preorderTraversal(root);
    }

    protected void preorderTraversal(BTNode node){
        if (node == null)
            return;

        visit(node);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public boolean search(T key){
        if(root == null)
            return false;
        Queue<BTNode> queue = new LinkedList<BTNode>();
        BTNode node = root;
        queue.add(node);
        while(! queue.isEmpty()){
            node = queue.poll();
            if(key.equals(node.data))
                return true;
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
        return false;
    }


    public void printTree(){
        printTree(root, "", true);
    }

    // Print the binary tree
    protected void printTree(BTNode currPtr, String indent, boolean last) {
        if(indent.equals(""))
            System.out.print("t");
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.data);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    //******************************************************************************
    // First task
    public boolean subtreesHaveEqualNumberOfNodes(){ // helper method just to check if the tree is empty one time
        if(this.root == null)
            throw new java.lang.UnsupportedOperationException("Empty tree");
        else
            return GetNumberOFChildren(root.right) == GetNumberOFChildren(root.left);

    }
    private int GetNumberOFChildren(BTNode<T> node){ // A method to get the number of a node's children
        if(node == null || (node.left == null && node.right == null))
            return 0;
        else{
            int sumRight = 1+ GetNumberOFChildren(node.right);
            int sumLeft = 1 + GetNumberOFChildren(node.left);
            return sumRight + sumLeft;
        }
    }
    // Task 2
    public int numOneChildNodes(){// helper method
        if(root == null)
            throw new java.lang.UnsupportedOperationException("INVALID!!");
        else
            return NumOnceChildrenNodes(root);
    }
    private int NumOnceChildrenNodes(BTNode<T> node){ // the actual counter method
        if(node == null ) // empty node
            return 0;
        else if((node.right != null && node.left != null) || (node.right == null && node.left == null))//node with two children or node with zero children
            return NumOnceChildrenNodes(node.left) + NumOnceChildrenNodes(node.right);
        else // one child node
            return 1 + NumOnceChildrenNodes(node.left) + NumOnceChildrenNodes(node.right);
    }
}