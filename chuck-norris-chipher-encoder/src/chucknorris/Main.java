package chucknorris;

import chucknorris.input.Decryption;
import chucknorris.input.Encryption;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Decryption.InvalidEncodedMessage {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Please input operation (encode/decode/exit):");
            String line = sc.nextLine();

            switch (line) {
                case "encode" -> {
                    Encryption enc = new Encryption();
                    System.out.printf("Encoded string:\n%s\n\n", Encryption.encode(enc.getMessage()));
                }
                case "decode" -> {
                    Decryption dec = new Decryption();
                    try {
                        System.out.printf("Decoded string:\n%s\n\n", Decryption.decode(dec.getMessage()));
                    } catch (Decryption.InvalidEncodedMessage e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("There is no '" + line + "' operation\n");
            }
        } while (true);
    }
}

