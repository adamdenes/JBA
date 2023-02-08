package chucknorris.input;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Decryption extends Input {
    public Decryption() {
    }

    @Override
    public String getMessage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input encoded string:");
        return sc.nextLine();
    }

    private static String convertBack(String binary) {
        StringBuilder chs = new StringBuilder();
        StringBuilder res = new StringBuilder();

        int i = 0;
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

    public static String decode(String encoded) throws InvalidEncodedMessage {
        StringJoiner sj = new StringJoiner("");
        String[] arr = encoded.split(" ");

        String[] newArr;
        do {
            newArr = getChunk(arr);
            int zeros = countZeros(newArr[1]);

            if (zeros == -1) {
                throw new InvalidEncodedMessage("Encoded string is not valid.");
            }

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
                default -> throw new InvalidEncodedMessage("Encoded string is not valid.");
            }
            arr = Arrays.copyOfRange(arr, 2, arr.length);
        } while (arr.length != 0);

        if (sj.length() % 7 != 0) {
            throw new InvalidEncodedMessage("Encoded string is not valid.");
        }

        return convertBack(sj.toString());
    }

    private static int countZeros(String str) {
        int counter = 0;

        if (str == null) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                counter++;
            }
        }

        return counter;
    }

    private static String[] getChunk(String... arr) {
        String[] temp = new String[2];
        try {
            System.arraycopy(arr, 0, temp, 0, 2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.arraycopy(arr, 0, temp, 0, 1);
        }

        return temp;
    }

    public static class InvalidEncodedMessage extends Exception {
        InvalidEncodedMessage(String message) {
            super(message);
        }
    }
}
