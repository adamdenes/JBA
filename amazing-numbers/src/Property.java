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
    JUMPING("jumping"),
    HAPPY("happy"),
    SAD("sad");

    final String type;

    Property(String type) {
        this.type = type;
    }

    public static boolean isProperty(String property) {
        String tempProperty = property;
        if (property.startsWith("-")) {
            tempProperty = property.replace("-", "");
        }

        for (Property p : values()) {
            if (Objects.equals(p.name(), tempProperty.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    static boolean isFilter(String property) {
        return property.startsWith("-");
    }

    private static void processFilters(boolean isCurrentFilter, boolean isNextFilter, String tempProp, String next, String[] result) {
        if (isCurrentFilter && isNextFilter) {
            tempProp = "-" + tempProp.toUpperCase();
            next = "-" + next;
        } else if (isCurrentFilter) {
            tempProp = "-" + tempProp.toUpperCase();
        } else if (isNextFilter) {
            next = "-" + next;
        }

        result[0] = tempProp.toUpperCase();
        result[1] = next;
    }

    public static String[] getMutuallyExclusive(String... properties) {
        String[] result = new String[2];
        String tempProp;
        String tempProp2;
        Property next;


        for (String s : properties) {
            boolean isCurrentFilter = false;
            boolean isNextFilter = false;
            tempProp = s;
            for (String property : properties) {
                tempProp2 = property;
                if (s.equals(property)) {
                    continue;
                }

                if (isFilter(tempProp)) {
                    tempProp = s.replace("-", "");
                    isCurrentFilter = true;
                }
                if (isFilter(tempProp2)) {
                    tempProp2 = property.replace("-", "");
                    isNextFilter = true;
                }

                next = Property.valueOf(tempProp2.toUpperCase());

                if (tempProp.equals(tempProp2)) {
                    processFilters(isCurrentFilter, isNextFilter, tempProp, next.name(), result);
                    return result;
                }

                switch (Property.valueOf(tempProp.toUpperCase())) {
                    case EVEN -> {
                        if (next == ODD) {
                            processFilters(isCurrentFilter, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    case ODD -> {
                        if (next == EVEN) {
                            processFilters(isCurrentFilter, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    case DUCK -> {
                        if (property.equals("-spy") || isCurrentFilter) {
                            continue;
                        }
                        if (next == SPY) {
                            processFilters(false, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    case SPY -> {
                        if (property.equals("-duck") || isCurrentFilter) {
                            continue;
                        }
                        if (next == DUCK) {
                            processFilters(false, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    case SQUARE -> {
                        if (property.equals("-sunny") || isCurrentFilter) {
                            continue;
                        }
                        if (next == SUNNY) {
                            processFilters(false, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    case SUNNY -> {
                        if (property.equals("-square") || isCurrentFilter) {
                            continue;
                        }
                        if (next == SQUARE) {
                            processFilters(false, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    case HAPPY -> {
                        if (next == SAD) {
                            processFilters(isCurrentFilter, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    case SAD -> {
                        if (next == HAPPY) {
                            processFilters(isCurrentFilter, isNextFilter, tempProp, next.name(), result);
                            return result;
                        }
                    }
                    default -> {
                        if (s.equals(tempProp2) || tempProp.equals(property)) {
                            processFilters(true, true, tempProp, next.name(), result);
                            return result;
                        }
                    }
                }
            }
        }
        return result;
    }
}
