package duke.ui;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public abstract class Ui {
    private static final String BORDER_CORNER = "+";
    private static final String BORDER_HORIZONTAL = "-";
    private static final String BORDER_VERTICAL = "|";
    private static final String BORDER_SPACING = " ";
    private static final int PAD_LENGTH = 6;

    protected final PrintStream out;

    public Ui() {
        this.out = System.out;
    }

    public void generateTextBorder(String outputText) {
        int stringLen = outputText.length();
        printTopBottomBorder(stringLen);
        printSideBorder(stringLen);
        printOuterTextBorder(outputText, stringLen);
        printSideBorder(stringLen);
        printTopBottomBorder(stringLen);
    }

    public void generateMultiLineTextBorder(String... outputTexts) {
        int stringLen = getMaxOutputLength(outputTexts);
        printTopBottomBorder(stringLen);
        printSideBorder(stringLen);
        Arrays.stream(outputTexts).forEach((text) -> printOuterTextBorder(text, stringLen));
        printSideBorder(stringLen);
        printTopBottomBorder(stringLen);
    }

    public void generateMultiLineTextBorder(ArrayList<String> outputTexts) {
        int stringLen = getMaxOutputLength(outputTexts);
        printTopBottomBorder(stringLen);
        printSideBorder(stringLen);
        outputTexts.forEach((text) -> printOuterTextBorder(text, stringLen));
        printSideBorder(stringLen);
        printTopBottomBorder(stringLen);
    }

    public void printTopBottomBorder(int stringLen) {
        out.println(BORDER_CORNER + BORDER_HORIZONTAL.repeat(stringLen + PAD_LENGTH) + BORDER_CORNER);
    }

    public void printSideBorder(int stringLen) {
        out.println(BORDER_VERTICAL + BORDER_SPACING.repeat(stringLen + PAD_LENGTH) + BORDER_VERTICAL);
    }

    public void printOuterTextBorder(String outputText, int maxLength) {
        int extraPadding = maxLength - outputText.length();
        out.println(BORDER_VERTICAL
                + BORDER_SPACING.repeat(PAD_LENGTH / 2)
                + outputText + BORDER_SPACING.repeat((PAD_LENGTH / 2)
                + extraPadding) + BORDER_VERTICAL);
    }

    private int getMaxOutputLength(String[] outputTexts) {
        int maxLengthText = 0;
        Optional<String> streamData = Arrays.stream(outputTexts)
                .max(Comparator.comparingInt(String::length));
        if (streamData.isPresent()) {
            maxLengthText = streamData.get().length();
        }
        return maxLengthText;
    }

    private int getMaxOutputLength(ArrayList<String> outputTexts) {
        int maxLengthText = 0;
        Optional<String> streamData = outputTexts.stream()
                .max(Comparator.comparingInt(String::length));
        if (streamData.isPresent()) {
            maxLengthText = streamData.get().length();
        }
        return maxLengthText;
    }
}
