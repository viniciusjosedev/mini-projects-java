package app;

import java.util.Scanner;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true) {
            try {
                System.out.print("Type a first number: ");
                String numberOneText = scan.nextLine();
                Integer numberOne = Integer.parseInt(numberOneText);


                System.out.print("Type a second number: ");
                String numberSecondText = scan.nextLine();
                Integer numberSecond = Integer.parseInt(numberSecondText);

                System.out.printf("This sum is: %d\n", numberOne + numberSecond);
            } catch (Exception err) {
                System.out.println("Type only numbers!");
            } finally {
                continue;
            }
        }

    }
}
