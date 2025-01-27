import java.util.*;

public class Factorial {
    public static int Rec(int n) {
        if (n <= 0) { // Base case
            return 1;
        } else { // Recursive case
            return n * Rec(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number you want the factorial of:");

        // Validate input
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n < 0) {
                System.out.println("Factorial is not defined for negative numbers.");
            } else {
                System.out.println("The factorial of " + n + " is " + Rec(n));
            }
        } else {
            System.out.println("Invalid input! Please enter a valid integer.");
        }

        sc.close(); // Close Scanner to avoid resource leaks
    }
}
