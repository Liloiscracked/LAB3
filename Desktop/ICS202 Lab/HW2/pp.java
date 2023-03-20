import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class pp {// just for testing Question 1
    public static void main(String[] args) {
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
    }
}
