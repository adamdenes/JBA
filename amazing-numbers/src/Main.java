package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
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

            try {
                MyNumber.validate(input);

                long num = Long.parseLong(input[0]);
                long numTwo;

                if (num == 0) {
                    System.out.println("\nGoodbye!");
                    break;
                }

                switch (input.length) {
                    case 1 -> {
                        n = new MyNumber(num);
                        System.out.println(n);
                    }
                    case 2 -> {
                        numTwo = Long.parseLong(input[1]);
                        numbers = new MyNumber[Math.abs((int) numTwo)];

                        for (int i = 0; i < numbers.length; i++) {
                            numbers[i] = new MyNumber(num + i, numTwo);
                        }

                        processProperties(numbers);
                    }
                    default -> {
                        numTwo = Long.parseLong(input[1]);
                        String property = input[2];

                        if (!isProperty(property)) {
                            printHelper(property);
                            continue;
                        }

                        n = new MyNumber(num);
                        int propCount = 0;
                        System.out.println();
                        while (propCount < numTwo) {
                            List<String> stringList = gatherProperties(n);
                            String s = String.join(", ", stringList);

                            for (String sl : stringList) {
                                if (Objects.equals(sl.toUpperCase(), property)) {
                                    System.out.println("\t\t\t   " + n.getNum() + " is " + s);
                                    propCount++;
                                }
                            }
                            n.setNum(n.getNum() + 1);
                        }
                        System.out.println();
                    }
                }
            } catch (MyNeturalNumberException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void processProperties(MyNumber[] numbers) {
        System.out.println();
        for (MyNumber number : numbers) {
            String s = String.join(", ", gatherProperties(number));
            System.out.println("\t\t\t   " + number.getNum() + " is " + s);
        }
        System.out.println();
    }

    public static boolean isProperty(String property) {
        String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY"};
        for (String p : properties) {
            if (Objects.equals(p, property.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static void printHelper(String property) {
        String help = String.format("""
                                
                The property [%s] is wrong.
                Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]
                """, property.toUpperCase());
        System.out.println(help);
    }

    public static void printHelper() {
        String help = """ 
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
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
        if (num.isSpy()) {
            properties.add("spy");
        }
        if (num.isSquare()) {
            properties.add("square");
        }
        if (num.isSunny()) {
            properties.add("sunny");
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
