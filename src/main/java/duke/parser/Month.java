package duke.parser;

/**
 * Represents the mapping of months of lowercase to capitalized format.
 */
public enum Month {
    JAN("jan", "Jan"),
    FEB("feb", "Feb"),
    MAR("mar", "Mar"),
    APR("apr", "Apr"),
    MAY("may", "May"),
    JUN("jun", "Jun"),
    JUL("jul", "Jul"),
    AUG("aug", "Aug"),
    SEP("sep", "Sep"),
    OCT("oct", "Oct"),
    NOV("nov", "Nov"),
    DEC("dec", "Dec");


    private final String oldInput;
    private final String newInput;

    Month(String oldInput, String newInput) {
        this.oldInput = oldInput;
        this.newInput = newInput;
    }

    public String getOldInput() {
        return oldInput;
    }

    public String getNewInput() {
        return newInput;
    }
}
