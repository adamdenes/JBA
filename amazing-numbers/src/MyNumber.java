package numbers;


class MyNumber {
    private long num;

    public MyNumber(long num) throws MyNeturalNumberException {
        MyNumber.validate(String.valueOf(num));
        this.num = num;
    }

    public MyNumber(long num1, long num2) throws MyNeturalNumberException {
        this(num1);
        MyNumber.validate(String.valueOf(num1), String.valueOf(num2));
    }

    public static void validate(String... input) throws MyNeturalNumberException {
        long num;
        long num2;

        switch (input.length) {
            case 1 -> {
                try {
                    num = Long.parseLong(input[0]);
                    if (num < 0) {
                        throw new MyNeturalNumberException("\nThe first parameter should be a natural number or zero.\n");
                    }
                } catch (NumberFormatException e) {
                    throw new MyNeturalNumberException("\nThe first parameter should be a natural number or zero.\n");
                }
            } case 2 -> {
                try {
                    num2 = Long.parseLong(input[1]);
                    if (num2 < 0) {
                        throw new MyNeturalNumberException("\nThe second parameter should be a natural number.\n");
                    }
                } catch (NumberFormatException e) {
                    throw new MyNeturalNumberException("\nThe second parameter should be a natural number.\n");
                }
            }
        }
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    protected boolean isEven() {
        return this.num % 2 == 0;
    }

    protected boolean isOdd() {
        return !this.isEven();
    }

    protected boolean isBuzz() {
        return this.num % 7 == 0 || this.num % 10 == 7;
    }

    protected boolean isDuck() {
        return String.valueOf(this.num).contains("0") && String.valueOf(this.num).indexOf("0") != 0;
    }

    protected boolean isGapful() {
        String fakeNum = String.valueOf(this.num);
        if (fakeNum.length() >= 3) {
            String firstDigit = fakeNum.substring(0, 1);
            String lastDigit = fakeNum.substring(fakeNum.length() - 1);
            long divisor = Long.parseLong(firstDigit.concat(lastDigit));

            return this.num % divisor == 0;
        }
        return false;
    }

    protected boolean isPalindromic() {
        String number = String.valueOf(this.num);
        for (int i = 0; i < number.length(); i++) {
            int last = number.length() - 1;
            if (number.charAt(i) == number.charAt(last - i)) {
                continue;
            }
            return false;
        }
        return true;
    }

    protected boolean isSquare() {
        return false;
    }

    protected boolean isSunny() {
        return false;
    }

    protected boolean isSpy() {
        return this.sum() == this.product();
    }

    private int product() {
        String num = String.valueOf(this.num);

        if (num.length() < 2) {
            return (int) this.num;
        }

        int prod = 1;
        for (int i = 0; i < num.length(); i++) {
            prod *= Integer.parseInt(String.valueOf(num.charAt(i)));
        }
        return prod;
    }

    private int sum() {
        String num = String.valueOf(this.num);
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            sum += Integer.parseInt(String.valueOf(num.charAt(i)));
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("\nProperties of " + this.num +
                "\n\t\tbuzz: " + this.isBuzz() +
                "\n\t\tduck: " + this.isDuck()) +
                "\n palindromic: " + this.isPalindromic() +
                "\n\t  gapful: " + this.isGapful() +
                "\n\t\t spy: " + this.isSpy() +
                "\n\t  square: " + this.isSquare() +
                "\n\t   sunny: " + this.isSunny() +
                "\n\t\teven: " + this.isEven() +
                "\n\t\t odd: " + this.isOdd() +
                "\n";
    }
}
