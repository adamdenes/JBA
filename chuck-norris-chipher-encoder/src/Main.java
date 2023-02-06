import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        String line = sc.nextLine();

        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < line.length(); i++) {
            sj.add(String.format("%c", line.charAt(i)));
        }

        System.out.println(sj);
    }
}