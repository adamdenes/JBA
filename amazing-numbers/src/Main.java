package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        printHelper();
        MyNumber[] numbers;
        MyNumber n;

        String[] input;
        do {
            System.out.print("Enter a request: ");
            input = sc.nextLine().split(" ");

            if ("".equals(input[0])) {
                printHelper();
                continue;
            }

            long num = Long.parseLong(input[0]);

            if (num == 0) {
                System.out.println("\nGoodbye!");
                break;
            }

            try {
                if (input.length < 2) {
                    n = new MyNumber(num);
                    System.out.println(n);
                } else {
                    long numTwo = Long.parseLong(input[1]);
                    numbers = new MyNumber[Math.abs((int) numTwo)];

                    for (int i = 0; i < numbers.length; i++) {
                        numbers[i] = new MyNumber(num + i, numTwo);
                    }
                    process(numbers);
                }
            } catch (MyNeturalNumberException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void process(MyNumber[] numbers) {
        System.out.println();
        for (MyNumber number : numbers) {
            String s = String.join(", ", gatherProperties(number));
            System.out.println(number.getNum() + " is " + s);
        }
        System.out.println();
    }

    public static void printHelper() {
        String help = """                 
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;
        System.out.println(help);
    }

    public static List<String> gatherProperties(MyNumber num) {
        List<String> properties = new ArrayList<>();

        if (num.isBuzz()) {
            properties.add("buzz");
        }
        if (num.isDuck()) {
            properties.add("duck");
        }
        if (num.isPalindromic()) {
            properties.add("palindromic");
        }
        if (num.isGapful()) {
            properties.add("gapful");
        }
        if (num.isEven()) {
            properties.add("even");
        }
        if (num.isOdd()) {
            properties.add("odd");
        }

        return properties;
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

    public MyNumber(long num1, long num2) throws MyNeturalNumberException {
        this(num1);
        if (num2 < 0) {
            throw new MyNeturalNumberException("The second parameter should be a natural number.\n");
        }
    }

    public long getNum() {
        return num;
    }

    protected boolean isEven() {
        return this.num % 2 == 0;
    }

    protected boolean isOdd() {
        return !this.isEven();
    }

    protected boolean isBuzz() {
        return this.num % 7 == 0 || this.num % 10 == 7;
    }

    protected boolean isDuck() {
        return String.valueOf(this.num).contains("0") && String.valueOf(this.num).indexOf("0") != 0;
    }

    protected boolean isGapful() {
        String fakeNum = String.valueOf(this.num);
        if (fakeNum.length() >= 3) {
            String firstDigit = fakeNum.substring(0, 1);
            String lastDigit = fakeNum.substring(fakeNum.length() - 1);
            long divisor = Long.parseLong(firstDigit.concat(lastDigit));

            return this.num % divisor == 0;
        }
        return false;
    }

    protected boolean isPalindromic() {
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
        return String.format("\nProperties of " + this.num +
                "\n\t\tbuzz: " + this.isBuzz() +
                "\n\t\tduck: " + this.isDuck()) +
                "\n palindromic: " + this.isPalindromic() +
                "\n\t  gapful: " + this.isGapful() +
                "\n\t\teven: " + this.isEven() +
                "\n\t\t odd: " + this.isOdd() +
                "\n";
    }
}
