package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        throw new NumberFormatException("hali");
        final Scanner sc = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        int num = sc.nextInt();
        process(num);
    }

    public static void process(int num) {
        if (num <= 0) {
            System.out.println("This number is not natural!");
            return;
        }

        isEven(num);

        String res = "";
        if (num % 7 != 0 && num % 10 != 7) {
            System.out.printf(
                    "It is not a Buzz number.\n" +
                            "Explanation:\n" +
                            "%d is neither divisible by 7 nor does it end with 7.\n",
                    num
            );
        } else {
            if (num % 7 == 0 && num % 10 == 7) {
                res = "%d is divisible by 7 and ends with 7.\n";
            } else if (num % 10 != 7) {
                res = "%d is divisible by 7.\n";
            } else {
                res = "%d ends with 7.\n";
            }

            System.out.printf(
                    "It is a Buzz number.\n" +
                            "Explanation:\n" +
                            res,
                    num
            );
        }
    }

    public static void isEven(int num) {
        boolean isNumEven = num % 2 == 0;
        System.out.println("This number is " + (isNumEven ? "Even." : "Odd."));
    }

    public static boolean isBuzz(int num) {
        String res = "";
        if (num % 7 != 0 && num % 10 != 7) {
            System.out.printf(
                    "It is not a Buzz number.\n" +
                            "Explanation:\n" +
                            "%d is neither divisible by 7 nor does it end with 7.\n",
                    num
            );
        } else {
            if (num % 7 == 0 && num % 10 == 7) {
                res = "%d is divisible by 7 and ends with 7.\n";
            } else if (num % 10 != 7) {
                res = "%d is divisible by 7.\n";
            } else {
                res = "%d ends with 7.\n";
            }

            System.out.printf(
                    "It is a Buzz number.\n" +
                            "Explanation:\n" +
                            res,
                    num
            );
        }
    }
}
