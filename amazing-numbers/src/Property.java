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

        for (String s : properties) {
            for (String property : properties) {
                if (property.equals(s)) {
                    continue;
                }

                next = Property.valueOf(property.toUpperCase());
                switch (Property.valueOf(s.toUpperCase())) {
                    case EVEN -> {
                        if (next == ODD) {
                            result[0] = s.toUpperCase();
                            result[1] = next.name();
                        }
                    }
                    case ODD -> {
                        if (next == EVEN) {
                            result[0] = s.toUpperCase();
                            result[1] = next.name();
                        }
                    }
                    case DUCK -> {
                        if (next == SPY) {
                            result[0] = s.toUpperCase();
                            result[1] = next.name();
                        }
                    }
                    case SPY -> {
                        if (next == DUCK) {
                            result[0] = s.toUpperCase();
                            result[1] = next.name();
                        }
                    }
                    case SQUARE -> {
                        if (next == SUNNY) {
                            result[0] = s.toUpperCase();
                            result[1] = next.name();
                        }
                    }
                    case SUNNY -> {
                        if (next == SQUARE) {
                            result[0] = s.toUpperCase();
                            result[1] = next.name();
                        }
                    }
                }
            }
        }
        return result;
    }
}
