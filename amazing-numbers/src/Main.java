package numbers;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static numbers.MyNumber.gatherProperties;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        printHelper();
        MyNumber[] numbers;
        MyNumber n;

        do {
            System.out.print("Enter a request: ");
            Input input = new Input(sc.nextLine().toLowerCase().split(" "));

            if (input.isEmpty()) {
                printHelper();
                continue;
            }
            if (Objects.equals(input.getNext(), "0")) {
                System.out.println("\nGoodbye!");
                break;
            }

            try {
                Input.validate(input.inputs);
                MyNumber.validate(input.inputs);
                long num, numTwo;

                switch (input.inputs.length) {
                    case 1 -> {
                        num = Long.parseLong(input.getNextInput());
                        n = new MyNumber(num);
                        System.out.println(n);
                    }
                    case 2 -> {
                        num = Long.parseLong(input.getNextInput());
                        numTwo = Long.parseLong(input.getNextInput());
                        numbers = new MyNumber[Math.abs((int) numTwo)];

                        for (int i = 0; i < numbers.length; i++) {
                            numbers[i] = new MyNumber(num + i, numTwo);
                        }

                        processProperties(numbers);
                    }
                    default -> {
                        num = Long.parseLong(input.getNextInput());
                        numTwo = Long.parseLong(input.getNextInput());
                        n = new MyNumber(num);

                        processProperties(n, numTwo, input.getProperties());
                    }
                }
            } catch (MyNeturalNumberException | MyInputPropertyException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void processProperties(MyNumber num, long len, String... properties) {
        int propCount = 0;
        System.out.println();
        StringBuilder sb = new StringBuilder();

        while (propCount < len) {
            String s = gatherProperties(num);
            sb.append(s);
            String[] results = s.split(", ");
            Arrays.sort(results);
            Arrays.sort(properties);

            if (filter(results, properties)) {
                System.out.println("\t\t\t   " + num.getNum() + " is " + sb);
                propCount++;
            }
            sb.delete(0, sb.length());
            num.setNum(num.getNum() + 1);
        }

        System.out.println();
    }

    public static void processProperties(MyNumber[] numbers) {
        System.out.println();
        for (MyNumber number : numbers) {
            String s = String.join(", ", gatherProperties(number));
            System.out.println("\t\t\t   " + number.getNum() + " is " + s);
        }
        System.out.println();
    }

    public static boolean filter(String[] results, String[] properties) {
        int filters = (int) Arrays.stream(properties).filter(s -> s.startsWith("-")).count();
        int trueCount = 0;
        for (String result : results) {
            for (String property : properties) {
                if (Property.isFilter(property) && Objects.equals(result, property.replace("-", ""))) {
                    return false;
                }
                if (!Property.isFilter(property) && Objects.equals(result, property)) {
                    trueCount++;
                }
            }
        }

        return trueCount == properties.length - filters;
    }

    public static void printHelper() {
        String help = """ 
                                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;
        System.out.println(help);
    }
}
