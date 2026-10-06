package Day1;

import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter the operator (+,-,*, /): ");
        char operator = scanner.next().charAt(0);
        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();
        System.out.print("The result is: ");
        if (operator == '+'){
            System.out.print(num1 + num2);
        }
        else if (operator == '-'){
            System.out.print(num1 - num2);
        }
        else if (operator == '*'){
            System.out.print(num1 * num2);
        }
        else{
            if (num2 == 0){
                System.out.print("Error cannot divide by 0");
            }
            else{
                System.out.print(num1/num2);
            }
        }

    }
}