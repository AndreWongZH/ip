package duke.parser;

public enum DateTimeFormat {
    FORMAT_1("d MMM yyyy HHmm"),
    FORMAT_2("dd/MM/yyyy HHmm"),
    FORMAT_3("dd/M/yyyy HHmm"),
    FORMAT_4("dd-MMM-yyyy HHmm"),
    FORMAT_5("d MMM yyyy HH:mm"),
    FORMAT_6("dd/MM/yyyy HH:mm"),
    FORMAT_7("dd/M/yyyy HH:mm");


    private final String formatStyle;

    DateTimeFormat(String formatStyle) {
        this.formatStyle = formatStyle;
    }

    public String getFormatStyle() {
        return formatStyle;
    }
}
