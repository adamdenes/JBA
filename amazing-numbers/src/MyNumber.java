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
            }
            case 2 -> {
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
        return String.valueOf(this.num).contains("0") && String.valueOf(this.num).charAt(0) != '0';
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
        if (this.num < 0) {
            return false;
        }

        switch ((int) (this.num & 0xF)) {
            case 0, 1, 4, 9 -> {
                long tst = (long) Math.sqrt(this.num);
                return tst * tst == this.num;
            }
            default -> {
                return false;
            }
        }
    }

    protected boolean isSunny() {
        this.num += 1;
        if (this.isSquare()) {
            this.num -= 1;
            return true;
        }
        this.num -= 1;
        return false;
    }

    private boolean isJumping() {
        String num = String.valueOf(this.num);
        if (num.length() == 1) {
            return true;
        }
        for (int i = 0; i < num.length(); i++) {
            if (i + 1 < num.length()) {
                if (Math.abs((long) num.charAt(i) - (long) num.charAt(i + 1)) != 1) {
                    return false;
                }
            }
        }
        return true;
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
        return new StringBuilder("\nProperties of ")
                .append(this.num)
                .append("\n\t\tbuzz: ")
                .append(this.isBuzz())
                .append("\n\t\tduck: ")
                .append(this.isDuck())
                .append("\n palindromic: ")
                .append(this.isPalindromic())
                .append("\n\t  gapful: ")
                .append(this.isGapful())
                .append("\n\t\t spy: ")
                .append(this.isSpy())
                .append("\n\t  square: ")
                .append(this.isSquare())
                .append("\n\t   sunny: ")
                .append(this.isSunny())
                .append("\n\t jumping: ")
                .append(this.isJumping())
                .append("\n\t\teven: ")
                .append(this.isEven())
                .append("\n\t\t odd: ")
                .append(this.isOdd())
                .append("\n").toString();
    }

    public static String gatherProperties(MyNumber num) {
        StringBuilder properties = new StringBuilder();

        if (num.isBuzz()) {
            properties.append("buzz, ");
        }
        if (num.isDuck()) {
            properties.append("duck, ");
        }
        if (num.isPalindromic()) {
            properties.append("palindromic, ");
        }
        if (num.isGapful()) {
            properties.append("gapful, ");
        }
        if (num.isSpy()) {
            properties.append("spy, ");
        }
        if (num.isSquare()) {
            properties.append("square, ");
        }
        if (num.isSunny()) {
            properties.append("sunny, ");
        }
        if (num.isJumping()) {
            properties.append("jumping, ");
        }
        if (num.isEven()) {
            properties.append("even, ");
        }
        if (num.isOdd()) {
            properties.append("odd, ");
        }

        return properties.delete(properties.length() - 2, properties.length()).toString();
    }
}
