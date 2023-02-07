package chucknorris;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input encoded string:");
//        System.out.println("Input string:");
        final String line = sc.nextLine();

//        String res = convert(line);
        System.out.println("The result:");
//        System.out.println(encode(res));
        String res2 = convertBack(decode(line));
        System.out.println(res2);
    }

    public static String convert(String line) {
        StringJoiner sj = new StringJoiner("");
        for (int i = 0; i < line.length(); i++) {
            String temp = Integer.toBinaryString(line.charAt(i));
            sj.add(String.format("%s", temp.length() < 7 ? "0" + temp : temp));
        }

        System.out.println();
        return sj.toString();
    }

    public static String convertBack(String binary) {
        int i = 0;
        StringBuilder chs = new StringBuilder();
        StringBuilder res = new StringBuilder();

        for (int j = 0; j < binary.length(); j++) {
            i++;
            res.append(binary.charAt(j));
            if (i % 7 == 0) {
                chs.append((char) Integer.parseInt(res.toString(), 2));
                res = new StringBuilder();
            }
        }
        return chs.toString();
    }

    public static String encode(String binary) {
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

    public static String decode(String encoded) {
        StringJoiner sj = new StringJoiner("");
        String[] arr = encoded.split(" ");

        String[] newArr;
        do {
            newArr = getChunk(arr);
            int zeros = countZeros(newArr[1]);

            switch (newArr[0]) {
                case "0" -> {
                    for (int z = 0; z < zeros; z++) {
                        sj.add("1");
                    }
                }
                case "00" -> {
                    for (int z = 0; z < zeros; z++) {
                        sj.add("0");
                    }
                }
            }
            arr = Arrays.copyOfRange(arr, 2, arr.length);
        } while (arr.length != 0);

        return sj.toString();
    }

    private static int countZeros(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                counter++;
            }
        }
        return counter;
    }

    private static String[] getChunk(String... arr) {
        String[] temp = new String[2];

        System.arraycopy(arr, 0, temp, 0, 2);

        return temp;
    }
}

