package org.example;
import java.util.Scanner;
public class Maskify {

    public static String maskify(String mobileNumber) {
        // Check if the input is null or empty
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            return mobileNumber;
        }

        // Length of the mobile number
        int length = mobileNumber.length();

        // If the length is less than or equal to 3, it will return the original mobile number
        if (length <= 3) {
            return mobileNumber;
        }

        // Mask all characters except the last 3
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < length - 3; i++) {
            maskedNumber.append('#');
        }

        // Append the last 3 digits of the mobile number
        maskedNumber.append(mobileNumber.substring(length - 3));

        return maskedNumber.toString();
    }

    public static void main(String[] args) {
        //Taking Input from User
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your mobile number: ");
        String mobileNumber=sc.nextLine();

        System.out.println("Masked mobile number: " + maskify(mobileNumber));
    }
}
