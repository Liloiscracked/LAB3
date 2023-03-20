import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //Question 1
        Scanner input = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;
        String operation = "";
        try {
            while (!operation.equals("#")){
                System.out.println("Enter the first number : ");
                num1 = input.nextInt();
                System.out.println("Enter the second number : ");
                num2 = input.nextInt();
                System.out.println("Enter the wanted operation : ");
                operation = input.next();
                String result = SLL.Operation(num1,num2,operation);
                System.out.println("The result is : " + result);
                operation = "";
            }
        }
        catch (InputMismatchException ex){
            System.out.println("Invalid number!!!");
        }
        //Question 1 part 2
        System.out.println("The time complexity for (+) is O(n)");
        System.out.println("The time complexity for (-) is O(Max(m,n))");
        System.out.println("The time complexity for (*) is O(m*n)");
        // Question 2
        System.out.println("Breadth first is : 15 30 40 5 37 25 28 20 35 42 10");
        System.out.println("preorder traversal is : 15 30 37 28 20 25 35 40 42 5 10");
        System.out.println("Post - order traversal is : 28 20 37 35 25 30 42 40 10 5 15");
        System.out.println("In order traversal of the subtree with root 30 is :  28 37 20 30 35 25");

        //Question 3
        BST<Integer> Q2tree = new BST<>();
        Q2tree.insert(15);
        Q2tree.insert(17);
        Q2tree.insert(10);
        Q2tree.insert(2);
        Q2tree.insert(19);
        Q2tree.insert(18);
        Q2tree.insert(14);
        Q2tree.insert(8);
        Q2tree.insert(7);
        Q2tree.insert(16);
        Q2tree.insert(3);
        Q2tree.insert(4);
        Q2tree.insert(11);
        System.out.println("The original tree");
        Q2tree.printTree();
        Q2tree.deleteByCopying(15);
        System.out.println("after deleting by copy: ");
        Q2tree.printTree();
        BST<Integer> Q2tree2 = new BST<>();
        Q2tree2.insert(15);
        Q2tree2.insert(17);
        Q2tree2.insert(10);
        Q2tree2.insert(2);
        Q2tree2.insert(19);
        Q2tree2.insert(18);
        Q2tree2.insert(14);
        Q2tree2.insert(8);
        Q2tree2.insert(7);
        Q2tree2.insert(16);
        Q2tree2.insert(3);
        Q2tree2.insert(4);
        Q2tree2.insert(11);
        Q2tree2.deleteByMerging(15);
        System.out.println("after deleting by Merge: ");
        Q2tree2.printTree();
    }
    // Question 1 method
}
