package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            String input = sc.nextLine();
            switch (input) {
                case "rock", "paper", "scissors" -> play(input);
                case "!exit" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        } while (true);
    }

    public static void play(String input) {
        String[] options = {"rock", "paper", "scissors"};
        Random random = new Random();
        int r = random.nextInt(3);
        evaluate(input, options[r]);
    }

    public static void evaluate(String input, String option) {
        if (input.equals(option)) {
            draw(option);
        } else if ("paper".equals(input)) {
            if ("scissors".equals(option)) {
                loss(option);
            } else {
                win(option);
            }
        } else if ("scissors".equals(input)) {
            if ("rock".equals(option)) {
                loss(option);
            } else {
                win(option);
            }
        } else {
            if ("paper".equals(option)) {
                loss(option);
            } else {
                win(option);
            }
        }
    }

    private static void loss(String option) {
        System.out.printf("Sorry, but the computer chose %s\n", option);
    }

    private static void draw(String option) {
        System.out.printf("There is a draw (%s)\n", option);
    }

    private static void win(String option) {
        System.out.printf("Well done. The computer chose %s and failed\n", option);
    }

    public static void godMode(String input) {
        String owned = "Sorry, but the computer chose";
        if ("paper".equals(input)) {
            System.out.println(owned + " scissors");
        } else if ("scissors".equals(input)) {
            System.out.println(owned + " rock");
        } else {
            System.out.println(owned + " paper");
        }
    }
}

