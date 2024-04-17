package org.example;
import java.util.Scanner;

public class Oops {
    private String name;
    private int age;
    private String mobile;

    // Constructor
    public Oops(String name, int age, String mobile) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // Method to get input person details
    public static Oops inputPersonDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        scanner.nextLine(); // Consume newline character
        System.out.print("Enter mobile number: ");
        String mobile = scanner.nextLine();

        return new Oops(name, age, mobile);
    }

    // Method to print person details
    public void printPersonDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Mobile: " + mobile);
    }

    public static void main(String[] args) {
        // Input person details
        Oops person = Oops.inputPersonDetails();

        // Print person details
        System.out.println("\nPerson details:");
        person.printPersonDetails();
    }
}
