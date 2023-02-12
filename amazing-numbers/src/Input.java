package numbers;

import java.util.Arrays;
import java.util.Objects;

public class Input {
    String next;
    String[] inputs;

    public Input(String... inputs) {
        this.inputs = inputs;
        this.next = this.inputs[0];
    }

    public static void validate(String... in) throws MyInputPropertyException {
        String help;

        if (in.length == 3) {
            if (!isProperty(in[2])) {
                help = String.format("""
                                        
                        The property [%s] is wrong.
                        Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]
                        """, in[2].toUpperCase());
                throw new MyInputPropertyException(help);
            }
        } else if (in.length == 4) {
            if (!isProperty(in[2]) && !isProperty(in[1])) {
                help = String.format("""
                                        
                        The properties [%s, %s] are wrong.
                        Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]
                        """, in[2].toUpperCase(), in[3].toUpperCase());
                throw new MyInputPropertyException(help);
            }

            if (!isProperty(in[2])) {
                help = String.format("""
                                        
                        The property [%s] is wrong.
                        Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]
                        """, in[2].toUpperCase());
                throw new MyInputPropertyException(help);
            } else if (!isProperty(in[3])) {
                help = String.format("""
                                        
                        The property [%s] is wrong.
                        Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]
                        """, in[3].toUpperCase());
                throw new MyInputPropertyException(help);
            }

            if (
                    (in[2].equals("sunny") || in[2].equals("square") && in[3].equals("sunny") || in[3].equals("square")) ||
                            (in[2].equals("odd") || in[2].equals("even") && in[3].equals("odd") || in[3].equals("even")) ||
                            (in[2].equals("spy") || in[2].equals("duck") && in[3].equals("spy") || in[3].equals("duck"))
            ) {
                help = String.format("""

                        The request contains mutually exclusive properties: [%s, %s]
                        There are no numbers with these properties.
                        """, in[2].toUpperCase(), in[3].toUpperCase());
                throw new MyInputPropertyException(help);
            }
        }
    }

    public static boolean isProperty(String property) {
        String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY"};
        for (String p : properties) {
            if (property == null) {
                continue;
            }
            if (Objects.equals(p, property.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.inputs == null || "".equals(this.inputs[0]);
    }

    public String getNext() {
        return next;
    }

    public String getNextInput() {
        if (this.isEmpty()) {
            return "";
        }
        this.next = this.inputs[0];
        this.inputs = Arrays.copyOfRange(
                this.inputs, 1, this.inputs.length
        );
        return this.next;
    }
}
