package cinema;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Cinema {
    int rows;
    int seats;
    private int income;
    private int soldTickets;
    final int ticketPrice = 10;
    String[][] matrix;

    public Cinema(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        this.setMatrix();
    }

    public void setMatrix() {
        this.matrix = new String[rows][seats];
        for (String[] strings : this.matrix) {
            Arrays.fill(strings, "S");
        }
    }

    private boolean isReserved(int rowNum, int seatNum) {
        return Objects.equals(this.matrix[rowNum - 1][seatNum - 1], "B");
    }

    public void reserveSeat() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter a row number:");
        int rowNum = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = sc.nextInt();

        if ((rowNum - 1 < 0 || rowNum > this.rows) || (seatNum - 1 < 0 || seatNum > this.seats)) {
            System.out.println("\nWrong input!");
            this.reserveSeat();
        } else if (this.isReserved(rowNum, seatNum)) {
            System.out.println("\nThat ticket has already been purchased!");
            this.reserveSeat();
        } else {
            this.matrix[rowNum - 1][seatNum - 1] = "B";
            this.soldTickets++;
            System.out.println("\nTicket price: $" + this.getTicketPrice(rowNum));
        }
    }

    public int getTicketPrice(int rowNum) {
        final var half = this.rows / 2;

        if (this.rows * this.seats <= 60) {
            this.income += this.ticketPrice;
            return this.ticketPrice;
        } else if (this.rows % 2 == 0) {
            if (rowNum <= half) {
                this.income += this.ticketPrice;
                return this.ticketPrice;
            } else {
                this.income += this.ticketPrice - 2;
                return this.ticketPrice - 2;
            }
        } else {
            if (rowNum <= half) {
                this.income += this.ticketPrice;
                return this.ticketPrice;
            } else {
                this.income += this.ticketPrice - 2;
                return this.ticketPrice - 2;
            }
        }
    }

    public static cinema.Cinema initTheatre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        final int numOfRows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int numOfSeatsInRows = sc.nextInt();

        return new cinema.Cinema(numOfRows, numOfSeatsInRows);
    }

    public void showStatistics() {
        System.out.printf("""
                        Number of purchased tickets: %d
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d
                        """,
                this.soldTickets, this.soldPercentage(), this.getIncome(), this.total());
    }

    private int total() {
        final var priceFront = 10;
        final var priceBack = 8;
        final var half = this.rows / 2;

        var result = 0;

        if (this.rows * this.seats <= 60) {
            result = priceFront * seats * rows;
        } else if (this.rows % 2 == 0) {
            result = (half * this.seats * priceFront) + (half * this.seats * priceBack);
        } else {
            result = (half * this.seats * priceFront) + ((this.rows - half) * this.seats * priceBack);
        }

        return result;
    }

    public int getIncome() {
        return income;
    }

    private double soldPercentage() {
        return (this.soldTickets / (double) (this.rows * this.seats)) * 100;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nCinema:\n");
        for (int i = 0; i <= this.rows; i++) {
            if (i > 0) {
                sb.append(i).append(" ");
            } else {
                sb.append("  ");
            }
            for (int j = 1; j <= this.seats; j++) {
                if (i == 0) {
                    sb.append(j).append(" ");
                } else {
                    sb.append(this.matrix[i - 1][j - 1]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
