/************************ BST.java ***************************
 * generic binary search tree*/
public class BST<T extends Comparable<? super T>> {
    protected BSTNode<T> root = null;
    public BST() {}
//Lab Quiz 02 methods
    public T shallowestLeaf() {
        return shallowestLeafRecursive(root);
    }
    public T deepestLeaf() {
        return deepestLeafRecursive(root);
    }
    public T shallowestLeafRecursive(BSTNode<T> node) {
        System.out.println("Please feel free to put your own parameters");
        System.out.println("And add more methods if you consider these necessary");
        return null;
    }
    public T deepestLeafRecursive(BSTNode<T> node) {
        System.out.println("Please feel free to put your own parameters");
        System.out.println("And add more methods if you consider these necessary");
        return null;
    }
    public void clear() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public void insert(T el) {
        BSTNode<T> p = root, prev = null;
        while (p != null) {
            prev = p;
            if (el.compareTo(p.el) < 0)
                p = p.left;
            else p = p.right;
        }
        if (root == null)
            root = new BSTNode<T>(el);
        else if (el.compareTo(prev.el) < 0)
            prev.left = new BSTNode<T>(el);
        else prev.right = new BSTNode<T>(el);
    }
    public void recInsert(T el) {
        root = recInsert(root, el);
    }
    protected BSTNode<T> recInsert(BSTNode<T> p, T el) {
        if (p == null)
            p = new BSTNode<T>(el);
        else if (el.compareTo(p.el) < 0)
            p.left = recInsert(p.left, el);
        else p.right = recInsert(p.right, el);
        return p;
    }
    public boolean isInTree(T el) {
        return search(el) != null;
    }
    protected T search(T el) {
        BSTNode<T> p = root;
        while (p != null)
            if (el.equals(p.el))
                return p.el;
            else if (el.compareTo(p.el) < 0)
                p = p.left;
            else p = p.right;
        return null;
    }
    public void preorder() {
        preorder(root);
    }
    public void inorder() {
        inorder(root);
    }
    public void postorder() {
        postorder(root);
    }
    protected void visit(BSTNode<T> p) {
        System.out.print(p.el + " ");
    }
    protected void inorder(BSTNode<T> p) {
        if (p != null) {
            inorder(p.left);
            visit(p);
            inorder(p.right);
        }
    }
    protected void preorder(BSTNode<T> p) {
        if (p != null) {
            visit(p);
            preorder(p.left);
            preorder(p.right);
        }
    }
    protected void postorder(BSTNode<T> p) {
        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            visit(p);
        }
    }
    public void deleteByCopying(T el) {
        BSTNode<T> node, p = root, prev = null;
        while (p != null && !p.el.equals(el)) {
            prev = p;
            if (el.compareTo(p.el) < 0)
                p = p.left;
            else p = p.right;
        }
        node = p;
        if (p != null && p.el.equals(el)){
            if (node.right == null)
                node = node.left;
            else if (node.left == null)
                node = node.right;
            else {
                BSTNode<T> tmp = node.left;
                BSTNode<T> previous = node;
                while (tmp.right != null) {
                    previous = tmp;
                    tmp = tmp.right;
                }
                node.el = tmp.el;
                if (previous == node)
                    previous.left = tmp.left;
                else previous.right = tmp.left;
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else
                prev.right = node;
        }
        else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }
    public void deleteByMerging(T el) {
        BSTNode<T> tmp, node, p = root, prev = null;
        while (p != null && !p.el.equals(el)) {
            prev = p;
            if (el.compareTo(p.el) < 0)
                p = p.right;
            else p = p.left;
        }
        node = p;
        if (p != null && p.el.equals(el)) {
            if (node.right == null)
                node = node.left;
            else if (node.left == null)
                node = node.right;
            else {
                tmp = node.left;
                while (tmp.right != null)
                    tmp = tmp.right;
                tmp.right = node.right;
                node = node.left;
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
        } else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }
    public void iterativePreorder() {
        BSTNode<T> p = root;
        Stack<BSTNode<T>> travStack = new Stack<BSTNode<T>>();
        if (p != null) {
            travStack.push(p);
            while (!travStack.isEmpty()) {
                p = travStack.pop();
                visit(p);
                if (p.right != null)
                    travStack.push(p.right);
                if (p.left != null)
                    travStack.push(p.left);
            }
        }
    }
    public void iterativeInorder() {
        BSTNode<T> p = root;
        Stack<BSTNode<T>> travStack = new Stack<BSTNode<T>>();
        while (p != null) {
            while (p != null) {
                if (p.right != null)
                    travStack.push(p.right);
                travStack.push(p);
                p = p.left;
            }
            p = travStack.pop();
            while (!travStack.isEmpty() && p.right == null){
                visit(p);
                p = travStack.pop();
            }
            visit(p);
            if (!travStack.isEmpty())
                p = travStack.pop();
            else p = null;
        }
    }
    public void iterativePostorder2() {
        BSTNode<T> p = root;
        Stack<BSTNode<T>> travStack = new Stack<BSTNode<T>>(),
                output = new Stack<BSTNode<T>>();
        if (p != null) {
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
        BSTNode<T> p = root, q = root;
        Stack<BSTNode<T>> travStack = new Stack<BSTNode<T>>();
        while (p != null) {
            for (; p.left != null; p = p.left)
                travStack.push(p);
            while (p != null && (p.right == null || p.right == q)){
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
    public void breadthFirst() {
        BSTNode<T> p = root;
        Queue<BSTNode<T>> queue = new Queue<BSTNode<T>>();
        if (p != null) {
            queue.enqueue(p);
            while (!queue.isEmpty()){
                p = queue.dequeue();
                visit(p);
                if (p.left != null)
                    queue.enqueue(p.left);
                if (p.right != null)
                    queue.enqueue(p.right);
            }
        }
    }
    public void MorrisInorder() {
        BSTNode<T> p = root, tmp;
        while (p != null)
            if (p.left == null) {
                visit(p);
                p = p.right;
            }
        else {
                tmp = p.left;
                while (tmp.right != null && tmp.right != p)
                    tmp = tmp.right;
                if (tmp.right == null) {
                    tmp.right = p;
                    p = p.left;
                }
                else {
                    visit(p);
                    tmp.right = null;
                    p = p.right;
                }
            }
    }
    public void MorrisPreorder() {
        BSTNode<T> p = root, tmp;
        while (p != null) {
            if (p.left == null) {
                visit(p);
                p = p.right;
            }
            else {
                tmp = p.left;
                while (tmp.right != null && tmp.right != p)
                    tmp = tmp.right;
                if (tmp.right == null) {
                    visit(p);
                    tmp.right = p;
                    p = p.left;
                } else {
                    tmp.right = null;
                    p = p.right;
                }
            }
        }
    }
    public void MorrisPostorder() {
        BSTNode<T> p = new BSTNode<T>(), tmp, q, r, s;
        p.left = root;
        while (p != null)
            if (p.left == null)
                p = p.right;
            else {
                tmp = p.left;
                while (tmp.right != null && tmp.right != p)
                    tmp = tmp.right;
                if (tmp.right == null) {
                    tmp.right = p;
                    p = p.left;
                }
                else {
                    for (q = p.left, r = q.right, s = r.right; r != p; q = r, r = s, s = s.right);
                        r.right = q;
                    for (s = q.right; q != p.left;q.right = r, r = q, q = s, s = s.right)
                        visit(q);
                    visit(p.left);
                    tmp.right = null;
                    p = p.right;}
            }
    }
    public void balance(T data[], int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            insert(data[middle]
            );
            balance(data, first, middle - 1);
            balance(data, middle + 1, last);
        }
    }
    public void balance(T data[]){
        balance(data, 0, data.length - 1);
    }


//a
public void printTree(){
    printTree(root, "", true);
}

    // Print the binary tree
    protected void printTree(BSTNode currPtr, String indent, boolean last) {
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
            System.out.println(currPtr.el);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }
    public T shallowestLeaff(){
        return (T) Findshortestpath(this.root, 0).el;
}
    public static int getLevel(BSTNode current, BSTNode node, int level){
        if (node.el.compareTo(current.el) == 0)
            return level;
        if (node.el.compareTo(current.el) > 0)
            return getLevel(current.right, node, level + 1);
        else
            return getLevel(current.left, node, level + 1);
    }
    public  BSTNode Findshortestpath(BSTNode node, int level) {
        if (node.left == null && node.right == null) {
            return node;
        }
        if (node.right != null && node.left != null){
            BSTNode right = Findshortestpath(node.right, level + 1);
            BSTNode left = Findshortestpath(node.left, level + 1);
            if (getLevel(this.root, right, 0) > getLevel(this.root, left, 0))
                return left;
            else if (getLevel(this.root, right, 0) < getLevel(this.root, left, 0))
                return right;
            else if (right.el.compareTo(left.el) > 0)
                return left;
            else
                return right;
        }
        else if (node.left == null) {
            return Findshortestpath(node.right, level + 1);
        }
        else {
            return Findshortestpath(node.left, level + 1);
        }
    }
    //b
    public T deepestLeaff() {
        return (T) Findlongestpath(this.root, 0).el;
    }
    public BSTNode Findlongestpath(BSTNode node, int level) {
        if (node.left == null && node.right == null) {
            return node;
        }
        if (node.right != null && node.left != null){
            BSTNode right = Findlongestpath(node.right, level + 1);
            BSTNode left = Findlongestpath(node.left, level + 1);
            if (getLevel(this.root, right, 0) < getLevel(this.root, left, 0))
                return left;
            else if (getLevel(this.root, right, 0) > getLevel(this.root, left, 0))
                return right;
            else if (right.el.compareTo(left.el) < 0)
                return left;
            else
                return right;
        }
        else if (node.left == null) {
            return Findlongestpath(node.right, level + 1);
        }
        else {
            return Findlongestpath(node.left, level + 1);
        }
    }
}
