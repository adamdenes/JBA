import cinema.Cinema;

import java.util.Scanner;

import static cinema.Cinema.initTheatre;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cinema c = initTheatre();

        while (true) {
            showMenu();
            switch (sc.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> System.out.print(c);
                case 2 -> c.reserveSeat();
                case 3 -> c.showStatistics();
            }
        }
    }

    private static void showMenu() {
        String[] arr = {"1. Show the seats", "2. Buy a ticket", "3. Statistics", "0. Exit"};

        System.out.println();
        for (String option : arr) {
            System.out.println(option);
        }
    }
}
