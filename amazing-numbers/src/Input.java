package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static numbers.Property.isProperty;

public class Input {
    String next;
    String[] inputs;
    String[] properties;

    public Input(String... inputs) {
        this.inputs = inputs;
        if (inputs.length > 2) {
            this.properties = new String[inputs.length - 2];
            System.arraycopy(inputs, 2, this.properties, 0, inputs.length - 2);
        }
        this.next = this.inputs[0];
    }

    public static void validate(String... in) throws MyInputPropertyException {
        if (in.length < 2) {
            return;
        }
        String help;
        List<String> invalidList = new ArrayList<>();

        String[] afterNumbers = new String[in.length - 2];
        System.arraycopy(in, 2, afterNumbers, 0, in.length - 2);

        for (String property : afterNumbers) {
            if (!isProperty(property)) {
                invalidList.add(property);
            }
        }

        switch (invalidList.size()) {
            case 1 -> {
                help = String.format("""

                        The property %s is wrong.
                        Available properties: %s
                        """, Arrays.toString(invalidList.toArray()).toUpperCase(), Arrays.toString(Property.values()));
                throw new MyInputPropertyException(help);
            }
            case 2 -> {
                help = String.format("""

                        The properties %s are wrong.
                        Available properties: %s
                        """, Arrays.toString(invalidList.toArray()).toUpperCase(), Arrays.toString(Property.values()));
                throw new MyInputPropertyException(help);
            }
            default -> {
                String[] exclusive = Property.getMutuallyExclusive(afterNumbers);
                if (exclusive[0] != null) {
                    help = String.format("""
                                
                            The request contains mutually exclusive properties: %s
                            There are no numbers with these properties.
                            """, Arrays.toString(exclusive));
                    throw new MyInputPropertyException(help);
                }
            }
        }
    }

    public boolean isEmpty() {
        return this.inputs == null || "".equals(this.inputs[0]);
    }

    public String[] getProperties() {
        return properties;
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
