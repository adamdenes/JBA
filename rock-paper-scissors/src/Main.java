package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("rating.txt");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        Player player = new Player(sc.nextLine());
        System.out.printf("Hello, %s\n", player.getName());

        String[] options = sc.nextLine().split(",");
        System.out.println("Okay, let's start");

        do {
            String input = sc.nextLine();
            switch (input) {
                case "!rating" -> {
                    try {
                        int scoreInFile = getRating(player.getName(), file);
                        System.out.printf("Your rating: %d\n", player.getScore() + scoreInFile);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "!exit" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> play(input, options, player);
            }
        } while (true);
    }

    public static int getRating(String name, File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        System.out.println(file.getPath());
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            String player = line[0];
            int score = Integer.parseInt(line[1]);

            if (name.equals(player)) {
                return score;
            }
        }
        return 0;
    }

    public static boolean isValidInput(String input, String[] options) {
        for (var option : options) {
            if (option.equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static void play(String input, String[] options, Player player) {
        String[] defaultOptions;
        Random random = new Random();

        if (options[0].equals("")) {
            defaultOptions = new String[]{"rock", "paper", "scissors"};
            if (isValidInput(input, defaultOptions)) {
                int r = random.nextInt(defaultOptions.length);
                evaluate(input, defaultOptions[r], player);
            } else {
                System.out.println("Invalid input");
            }
        } else {
            if (isValidInput(input, options)) {
                int r = random.nextInt(options.length);
                evaluatePlus(input, options[r], options, player);
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public static void evaluate(String input, String option, Player player) {
        int score = player.getScore();
        if (input.equals(option)) {
            player.setScore(score + 50);
            draw(option);
        } else if ("paper".equals(input)) {
            if ("scissors".equals(option)) {
                loss(option);
            } else {
                player.setScore(score + 100);
                win(option);
            }
        } else if ("scissors".equals(input)) {
            if ("rock".equals(option)) {
                loss(option);
            } else {
                player.setScore(score + 100);
                win(option);
            }
        } else {
            if ("paper".equals(option)) {
                loss(option);
            } else {
                player.setScore(score + 100);
                win(option);
            }
        }
    }

    public static void evaluatePlus(String input, String option, String[] options, Player player) {
        int score = player.getScore();
        String[] stronger = getStronger(input, options);

        // draw
        if (input.equals(option)) {
            player.setScore(score + 50);
            draw(option);
            return;
        }

        // if not stronger
        for (String s : stronger) {
            if (Objects.equals(s, option)) {
                loss(option);
                return;
            }
        }

        // then it is weaker
        player.setScore(score + 100);
        win(option);
    }

    private static String[] getStronger(String option, String[] options) {
        int half = options.length / 2;
        String[] result = new String[half];

        int idx = 0;
        for (int i = 0; i < options.length; i++) {
            if (option.equals(options[i])) {
                idx = i;
            }
        }

        for (int i = 0; i < result.length; i++) {
            int index = (idx + i + 1) % options.length;
            result[i] = options[index];
        }

        return result;
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

