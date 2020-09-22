package duke.parser;

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


    private String oldInput;
    private String newInput;

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
