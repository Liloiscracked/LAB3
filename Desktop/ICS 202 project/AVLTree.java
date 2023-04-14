public class AVLTree<T extends Comparable<T>> extends BST<T> {

    protected int height;

    public AVLTree() {
        super();
        height = -1;
    }

    public AVLTree(BTNode<T> root) {
        super(root);
        height = -1;
    }

    public void purge(){
        super.purge();
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BTNode<T> node) {
        if(node == null)
            return -1;
        else
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private AVLTree<T> getLeftAVL() {
        AVLTree<T> leftsubtree = new AVLTree<T>(root.left);
        return leftsubtree;
    }

    private AVLTree<T> getRightAVL() {
        AVLTree<T> rightsubtree = new AVLTree<T>(root.right);
        return rightsubtree;
    }

    protected int getBalanceFactor() {
        if(isEmpty())
            return 0;
        else
            return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }

    public void insertAVL(T el)  {
        super.insert(el);
        this.balance();
    }
    // Task 1
    public void deleteAVL(T el) {
        // to be completed by students
        super.deleteByCopying(el);
        this.balance();
    }

    protected void balance()
    {
        if(!isEmpty())
        {
            getLeftAVL().balance();
            getRightAVL().balance();

            adjustHeight();

            int balanceFactor = getBalanceFactor();

            if(balanceFactor == -2) {
                System.out.println("Balance factor = " + balanceFactor);
                System.out.println("Balancing node with el: "+root.data);


                if(getRightAVL().getBalanceFactor() == 0 && getLeftAVL().getBalanceFactor() == -1)    /// special case
                    rotateRight();
                else if(getLeftAVL().getBalanceFactor() <= 0)
                    rotateRight();
                else
                    rotateLeftRight();
            }

            else if(balanceFactor == 2) {
                System.out.println("Balance factor = " + balanceFactor);
                System.out.println("Balancing node with el: "+root.data);

                if(getRightAVL().getBalanceFactor() == 0)         /// special case that cannot be done
                    rotateLeft();                                /// by double rotations
                else if(getRightAVL().getBalanceFactor() > 0)
                    rotateLeft();
                else
                    rotateRightLeft();
            }
        }
    }

    protected void adjustHeight()
    {
        if(isEmpty())
            height = -1;
        else
            height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());
    }
    //Task 1
    protected void rotateRight() {
        System.out.println("RIGHT ROTATION");
        // to be completed by students
        BTNode<T> tempNode = root.right;
        root.right = root.left;
        root.left = root.right.left;
        root.right.left = root.right.right;
        root.right.right = tempNode;

        T val = (T) root.data;
        root.data = root.right.data;
        root.right.data = val;

        getRightAVL().adjustHeight();
        adjustHeight();
    }

    protected void rotateLeft() {
        System.out.println("LEFT ROTATION");
        BTNode<T> tempNode = root.left;
        root.left = root.right;
        root.right = root.left.right;
        root.left.right = root.left.left;
        root.left.left = tempNode;

        T val = (T) root.data;
        root.data = root.left.data;
        root.left.data = val;

        getLeftAVL().adjustHeight();
        adjustHeight();
    }

    protected void rotateLeftRight()
    {
        System.out.println("Double Rotation...");
        getLeftAVL().rotateLeft();
        getLeftAVL().adjustHeight();
        this.rotateRight();
        this.adjustHeight();
    }
    protected void rotateRightLeft()
    {
        System.out.println("Double Rotation...");
        // to be completed by students
        getRightAVL().rotateRight();
        getRightAVL().adjustHeight();
        this.rotateLeft();
        this.adjustHeight();

    }

    public void levelOrderTraversal(){
        levelOrderTraversal(root);
    }
    //project methods:
    public boolean find(T key){
        if(this.root == null)
            return false;
        else
            return Find(key,root);
    }
    private boolean Find(T key, BTNode node){
        if (node == null)
            return false;
        if(key.compareTo((T) node.data) == 0)
            return true;
        else if(key.compareTo((T) node.data) > 0)
            return Find(key,node.right);
        else
            return Find(key,node.left);
    }
    public String[] collectSimilar(String s){
        SLL<String> strings = colllectSimilar(new SLL<String>(),s,this.root);
        String[] result = new String[strings.size()];
        int i = 0;
        while (strings.head != null)
            result[i++] = strings.deleteFromTail();
        return result;
    }
    private SLL colllectSimilar(SLL list,String s,BTNode node){
        if(node==null)
            return list;
        if(areSimilar(s,(String) node.data))
            list.addToTail((String)node.data);
        colllectSimilar(list,s,node.left);
        colllectSimilar(list,s,node.right);
        return list;
    }
    public static boolean areSimilar(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (Math.abs(len1 - len2) > 1) {
            return false;
        }

        int i = 0, j = 0, diffCount = 0;
        while (i < len1 && j < len2) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (++diffCount > 1) {
                    return false;
                }
                if (len1 > len2) {
                    i++;
                } else if (len2 > len1) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }
        if (i < len1 || j < len2) {
            diffCount++;
        }
        return diffCount == 1;
    }

}