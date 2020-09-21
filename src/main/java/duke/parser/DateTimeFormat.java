package duke.parser;

public enum DateTimeFormat {
    FORMAT_1("d MMM yyyy HHmm"),
    FORMAT_2("dd/M/yyyy HHmm"),
    FORMAT_3("dd-MMM-yyyy HHmm"),

    FORMAT_4("d MMM yyyy HH:mm"),
    FORMAT_5("dd/M/yyyy HH:mm"),
    FORMAT_6("dd-MMM-yyyy HH:mm"),

    FORMAT_7("d MMM yyyy h.mma"),
    FORMAT_8("dd/M/yyyy h.mma"),
    FORMAT_9("dd-MMM-yyyy h.mma"),

    FORMAT_10("d MMM yyyy h mma"),
    FORMAT_11("dd/M/yyyy h mma"),
    FORMAT_12("dd-MMM-yyyy h mma"),

    FORMAT_13("d MMM yyyy ha"),
    FORMAT_14("dd/M/yyyy ha"),
    FORMAT_15("dd-MMM-yyyy ha"),

    FORMAT_16("dd MMM yyyy h:mm a");

    private final String formatStyle;

    DateTimeFormat(String formatStyle) {
        this.formatStyle = formatStyle;
    }

    public String getFormatStyle() {
        return formatStyle;
    }
}
