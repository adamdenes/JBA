package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        printHelper();
        long num;
        do {
            System.out.println("Enter a request:");
            num = sc.nextLong();

            if (num == 0) {
                System.out.println("\nGoodbye!");
                break;
            }

            try {
                final MyNumber n = new MyNumber(num);
                process(n);
            } catch (MyNeturalNumberException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void process(MyNumber num) {
       System.out.println(num);
    }

    public static void printHelper() {
        String help = """
                Welcome to Amazing Numbers!
                                        
                Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                """;
        System.out.println(help);
    }
}

class MyNumber {
    private final long num;

    public MyNumber(long num) throws MyNeturalNumberException {
        if (num < 0) {
            throw new MyNeturalNumberException("The first parameter should be a natural number or zero.\n");
        }
        this.num = num;
    }

    public boolean isEven() {
        return this.num % 2 == 0;
    }

    public boolean isBuzz() {
        return this.num % 7 == 0 || this.num % 10 == 7;
    }

    private boolean isDuck() {
        return String.valueOf(this.num).contains("0") && String.valueOf(this.num).indexOf("0") != 0;
    }

    private boolean isPalindromic() {
        String number = String.valueOf(this.num);
        for (int i = 0; i < number.length(); i++) {
            int last = number.length() - 1;
            if (number.charAt(i) == number.charAt(last - i)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Properties of " + this.num +
                "\n\t\teven: " + this.isEven() +
                "\n\t\todd:  " + !this.isEven() +
                "\n\t\tbuzz: " + this.isBuzz() +
                "\n\t\tduck: " + this.isDuck()) +
                "\n palindromic: " + this.isPalindromic() +
                "\n";
    }
}