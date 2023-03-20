/************************  BST.java  **************************
 *                 generic binary search tree
 */
import java.util.*;
public class BST<T extends Comparable<T>> extends BinaryTree<T> {
    protected BTNode<T> root = null;
    public BST() {
    }

    public BST(BTNode root) {
        root = null;
    }

    public void clear() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public void insert(T el) {
        BTNode<T> p = root, prev = null;
        while (p != null) {  // find a place for inserting new node;
            prev = p;
            if (el.compareTo(p.data) < 0)
                p = p.left;
            else
                p = p.right;
        }
        if (root == null)    // tree is empty;
            root = new BTNode<T>(el);
        else if (el.compareTo(prev.data) < 0)
            prev.left  = new BTNode<T>(el);
        else
            prev.right = new BTNode<T>(el);
    }
    public void recInsert(T el) {
        root = recInsert(root,el);
    }
    protected BTNode<T> recInsert(BTNode<T> p, T el) {
        if (p == null)
            p = new BTNode<T>(el);
        else if (el.compareTo(p.data) < 0)
            p.left  = recInsert(p.left,el);
        else
            p.right = recInsert(p.right,el);
        return p;
    }

    public boolean search(T el) {
        BTNode<T> p = root;
        while (p != null)
            if (el.equals(p.data))
                return  true;
            else if (el.compareTo(p.data) < 0)
                p = p.left;
            else
                p = p.right;
        return false;
    }

    public void deleteByCopying(T el) {
        BTNode<T> node, p = root, prev = null;
        while (p != null && !p.data.equals(el)) {  // find the node p
            prev = p;                           // with element el;
            if (el.compareTo(p.data) < 0)
                p = p.left;
            else
                p = p.right;
        }
        node = p;
        if (p != null && p.data.equals(el)) {
            if (node.right == null)             // node has no right child;
                node = node.left;
            else if (node.left == null)         // no left child for node;
                node = node.right;
            else {
                BTNode<T> tmp = node.left;    // node has both children;
                BTNode<T> previous = node;    // 1.
                while (tmp.right != null) {    // 2. find the rightmost
                    previous = tmp;            //    position in the
                    tmp = tmp.right;           //    left subtree of node;
                }
                node.data = tmp.data;              // 3. overwrite the reference
                //    to the element being deleted;
                if (previous == node)          // if node's left child's
                    previous.left  = tmp.left; // right subtree is null;
                else
                    previous.right = tmp.left; // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
        }
        else if (root != null)
            throw new java.util.NoSuchElementException("el " + el + " is not in the tree");
        else
            throw new UnsupportedOperationException("the tree is empty");
    }
    public void deleteByMerging(T el) {
        BTNode<T> tmp, node, p = root, prev = null;
        while (p != null && !p.data.equals(el)) {  // find the node p
            prev = p;                           // with element el;
            if (el.compareTo(p.data) < 0)
                p = p.right;
            else
                p = p.left;
        }
        node = p;
        if (p != null && p.data.equals(el)) {
            if (node.right == null) // node has no right child: its left
                node = node.left;  // child (if any) is attached to its parent;
            else if (node.left == null) // node has no left child: its right
                node = node.right; // child is attached to its parent;
            else {                  // be ready for merging subtrees;
                tmp = node.left;   // 1. move left
                while (tmp.right != null) // 2. and then right as far as
                    tmp = tmp.right;      //    possible;
                tmp.right =        // 3. establish the link between
                        node.right;    //    the rightmost node of the left
                //    subtree and the right subtree;
                node = node.left;  // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else
                prev.right = node; // 5.
        }
        else if (root != null)
            throw new java.util.NoSuchElementException("el " + el + " is not in the tree");
        else
            throw new UnsupportedOperationException("the tree is empty");
    }


    public void inorderTraversal(){
        inorderTraversal(root);
    }

    public void preorderTraversal(){
        preorderTraversal(root);
    }

    public void postorderTraversal(){
        postorderTraversal(root);
    }

    public void levelOrderTraversal(){
        levelOrderTraversal(root);
    }

    public void levelOrderTraversalByLevels(){
        levelOrderTraversalByLevels(root);
    }

    public void iterativePreorder() {
        BTNode<T> p = root;
        Stack<BTNode<T>> travStack = new Stack<BTNode<T>>();
        if (p != null) {
            travStack.push(p);
            while (!travStack.isEmpty()) {
                p = travStack.pop();
                visit(p);
                if (p.right != null)
                    travStack.push(p.right);
                if (p.left  != null)        // left child pushed after right
                    travStack.push(p.left);// to be on the top of the stack;
            }
        }
    }
    public void iterativeInorder() {
        BTNode<T> p = root;
        Stack<BTNode<T>> travStack = new Stack<BTNode<T>>();
        while (p != null) {
            while(p != null) {               // stack the right child (if any)
                if (p.right != null)        // and the node itself when going
                    travStack.push(p.right); // to the left;
                travStack.push(p);
                p = p.left;
            }
            p = travStack.pop();             // pop a node with no left child
            while (!travStack.isEmpty() && p.right == null) { // visit it and all
                visit(p);                   // nodes with no right child;
                p =  travStack.pop();
            }
            visit(p);                        // visit also the first node with
            if (!travStack.isEmpty())        // a right child (if any);
                p = travStack.pop();
            else
                p = null;
        }
    }
    public void iterativePostorder2() {
        BTNode<T> p = root;
        Stack<BTNode<T>> travStack = new Stack<BTNode<T>>(),
                output = new Stack<BTNode<T>>();
        if (p != null) {        // left-to-right postorder = right-to-left preorder;
            travStack.push(p);
            while (!travStack.isEmpty()) {
                p = travStack.pop();
                output.push(p);
                if (p.left != null)
                    travStack.push(p.left);
                if (p.right != null)
                    travStack.push(p.right);
            }
            while (!output.isEmpty()) {
                p = output.pop();
                visit(p);
            }
        }
    }
    public void iterativePostorder() {
        BTNode<T> p = root, q = root;
        Stack<BTNode<T>> travStack = new Stack<BTNode<T>>();
        while (p != null) {
            for ( ; p.left != null; p = p.left)
                travStack.push(p);
            while (p != null && (p.right == null || p.right == q)) {
                visit(p);
                q = p;
                if (travStack.isEmpty())
                    return;
                p = travStack.pop();
            }
            travStack.push(p);
            p = p.right;
        }
    }

    public void printTree(){
        printTree(root, "", true);
    }

    //==============================================================

    // Task 3
    public String getPathToLeafNode(T e) throws Exception { // helper method
        if (!search(e)) // if the element is not in the tree
            throw new Exception("The element does not exist");
        else
            return getPathToLeafNode(e, root);

    }
    private String getPathToLeafNode(T e, BTNode<T> node){
        if(node == null)
            return "";
        if(node.equals(e))
            return  String.valueOf(node.data);
        else if(e.compareTo(node.data) > 0){
            return String.valueOf(node.data) + " " + getPathToLeafNode(e,node.right);
        }
        else
            return String.valueOf(node.data) + " " + getPathToLeafNode(e,node.left);
    }

    // Task 4

    public int getNodeLevel(T  e) throws Exception {
        if(!search(e))
            throw new Exception("NOT FOUND!!");
        else
            return getNodeLevel(e,root) ;
    }
    private int getNodeLevel(T  e, BTNode node){
        if(e.compareTo((T) node.data)==0)
            return 0;
        else if (e.compareTo((T) node.data)>0) {
            return 1 + getNodeLevel(e, node.right);
        }
        else
            return 1 + getNodeLevel(e,node.left);
    }
}