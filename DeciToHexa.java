package org.example;
import java.util.Scanner;
class DeciToHexa {
    public static void DecimalToHexa() {
        Scanner user_input = new Scanner(System.in);
        int deci, remainder;

        // Creating character array to store the hexadecimal digits
        char[] hexa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        // String variable to display the hexadecimal value
        String hexa_value = ""; // Initially it would be null
        System.out.println("Enter Your number: ");
        deci = user_input.nextInt();

        while (deci > 0) {
            remainder = deci % 16;
            hexa_value = hexa[remainder] + hexa_value;
            deci = deci / 16;
        }
        System.out.println("Hexa value is " + hexa_value);
    }

    public static void main(String[] args) {
        DecimalToHexa();
    }
}
