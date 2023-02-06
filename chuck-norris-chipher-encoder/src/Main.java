package chucknorris;

import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        final String line = sc.nextLine();

        StringJoiner sj = new StringJoiner("");
        for (int i = 0; i < line.length(); i++) {
            String temp = Integer.toBinaryString(line.charAt(i));
            sj.add(String.format("%c = %7s\n",
                            line.charAt(i),
                            temp.length() < 7 ? "0" + temp : temp
                    )
            );
        }

        System.out.println("\nThe result:");
        System.out.println(sj);
    }
}

