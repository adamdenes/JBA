package numbers;

import java.util.Objects;

public enum Property {
    EVEN("even"),
    ODD("odd"),
    BUZZ("buzz"),
    DUCK("duck"),
    PALINDROMIC("palindromic"),
    GAPFUL("gapful"),
    SPY("spy"),
    SQUARE("square"),
    SUNNY("sunny"),
    JUMPING("jumping");

    final String type;

    Property(String type) {
        this.type = type;
    }

    public static boolean isProperty(String property) {
        for (Property p : values()) {
            if (property == null) {
                continue;
            }
            if (Objects.equals(p.name(), property.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static String[] getMutuallyExclusive(String... properties) {
        String[] result = new String[2];
        Property next;

        for (int i = 0; i < properties.length; i++) {
            if (i + 1 >= properties.length) {
                break;
            }
            next = Property.valueOf(properties[i + 1].toUpperCase());

            switch (Property.valueOf(properties[i].toUpperCase())) {
                case EVEN -> {
                    if (next == ODD) {
                        result[i] = Property.valueOf(properties[i].toUpperCase()).name();
                        result[i + 1] = next.name();
                    }
                }
                case ODD -> {
                    if (next == EVEN) {
                        result[i] = Property.valueOf(properties[i].toUpperCase()).name();
                        result[i + 1] = next.name();
                    }
                }
                case DUCK -> {
                    if (next == SPY) {
                        result[i] = Property.valueOf(properties[i].toUpperCase()).name();
                        result[i + 1] = next.name();
                    }
                }
                case SPY -> {
                    if (next == DUCK) {
                        result[i] = Property.valueOf(properties[i].toUpperCase()).name();
                        result[i + 1] = next.name();
                    }
                }
                case SQUARE -> {
                    if (next == SUNNY) {
                        result[i] = Property.valueOf(properties[i].toUpperCase()).name();
                        result[i + 1] = next.name();
                    }
                }
                case SUNNY -> {
                    if (next == SQUARE) {
                        result[i] = Property.valueOf(properties[i].toUpperCase()).name();
                        result[i + 1] = next.name();
                    }
                }
            }
        }
        return result;
    }
}
