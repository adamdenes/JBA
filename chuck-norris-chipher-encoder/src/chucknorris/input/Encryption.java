package chucknorris.input;

import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;

public class Encryption extends Input {
    public Encryption() {
    }

    @Override
    public String getMessage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        return sc.nextLine();
    }

    private static String convert(String line) {
        StringJoiner sj = new StringJoiner("");

        for (int i = 0; i < line.length(); i++) {
            String temp = Integer.toBinaryString(line.charAt(i));
            sj.add(String.format("%s", temp.length() < 7 ? "0" + temp : temp));
        }

        return sj.toString();
    }

    public static String encode(String line) {
        String binary = convert(line);
        StringJoiner sj = new StringJoiner("");


        int counterZeros = 0;
        int countOnes = 0;
        for (int i = 0; i < binary.length(); i++) {
            String ch = String.valueOf(binary.charAt(i));
            String nextCh;

            if (i + 1 > binary.length() - 1) {
                nextCh = null;
            } else {
                nextCh = String.valueOf(binary.charAt(i + 1));
            }

            switch (ch) {
                case "1" -> {
                    if (!Objects.equals(nextCh, "1")) {
                        sj.add("0 ");
                        for (int j = 0; j <= countOnes; j++) {
                            sj.add("0");
                        }
                        countOnes = 0;
                        sj.add(" ");
                    } else {
                        countOnes++;
                    }
                }
                case "0" -> {
                    if (!Objects.equals(nextCh, "0")) {
                        sj.add("00 ");
                        for (int j = 0; j <= counterZeros; j++) {
                            sj.add("0");
                        }
                        counterZeros = 0;
                        sj.add(" ");
                    } else {
                        counterZeros++;
                    }
                }
            }
        }

        return sj.toString();
    }
}
