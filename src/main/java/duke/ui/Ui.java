package duke.ui;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

/**
 * Represents the base of printing of user's ui on the output.
 *
 * @see CommandUi For output strings regarding commands.
 * @see FileUi For output strings regarding file handling.
 * @see InputUi For taking in user's input and print greetings.
 * @see TaskUi For output strings regarding tasks actions.
 */
public abstract class Ui {
    private static final String BORDER_CORNER = "+";
    private static final String BORDER_HORIZONTAL = "-";
    private static final String BORDER_VERTICAL = "|";
    private static final String BORDER_SPACING = " ";
    private static final int PAD_LENGTH = 6;
    private static final int PAD_SIDES = 2;

    protected final PrintStream out;

    public Ui() {
        this.out = System.out;
    }

    /**
     * Prints out the output text encased with borders.
     *
     * @param outputText Output text to be printed out
     */
    protected void generateTextBorder(String outputText) {
        int stringLen = outputText.length();

        printTopBottomBorder(stringLen);
        printSideBorder(stringLen);
        printOuterTextBorder(outputText, stringLen);
        printSideBorder(stringLen);
        printTopBottomBorder(stringLen);
    }

    /**
     * Prints out the lists of output texts encased with borders.
     *
     * @param outputTexts A list of output text to be printed out
     */
    protected void generateMultiLineTextBorder(String... outputTexts) {
        ArrayList<String> convertedOutputText = (ArrayList<String>) Arrays.stream(outputTexts).collect(toList());
        int stringLen = getMaxOutputLength(convertedOutputText);

        printTopBottomBorder(stringLen);
        printSideBorder(stringLen);
        Arrays.stream(outputTexts).forEach((text) -> printOuterTextBorder(text, stringLen));
        printSideBorder(stringLen);
        printTopBottomBorder(stringLen);
    }

    /**
     * Prints out the list of tasks encased with borders.
     * This is used when list/find command is called.
     *
     * @param outputTexts An ArrayList of tasks to be printed out
     */
    protected void generateMultiLineTextBorder(ArrayList<String> outputTexts) {
        int stringLen = getMaxOutputLength(outputTexts);

        printTopBottomBorder(stringLen);
        printSideBorder(stringLen);
        outputTexts.forEach((text) -> printOuterTextBorder(text, stringLen));
        printSideBorder(stringLen);
        printTopBottomBorder(stringLen);
    }

    /* Creates the top and bottom borders */
    private void printTopBottomBorder(int stringLen) {
        out.println(BORDER_CORNER + BORDER_HORIZONTAL.repeat(stringLen + PAD_LENGTH) + BORDER_CORNER);
    }

    /* Creates the side borders */
    private void printSideBorder(int stringLen) {
        out.println(BORDER_VERTICAL + BORDER_SPACING.repeat(stringLen + PAD_LENGTH) + BORDER_VERTICAL);
    }

    /* Wraps the output text with border string */
    private void printOuterTextBorder(String outputText, int maxLength) {
        int extraPadding = maxLength - outputText.length();
        out.println(BORDER_VERTICAL
                + BORDER_SPACING.repeat(PAD_LENGTH / PAD_SIDES)
                + outputText + BORDER_SPACING.repeat((PAD_LENGTH / PAD_SIDES)
                + extraPadding) + BORDER_VERTICAL);
    }

    /**
     * Returns the length of the longest string in an array list of strings.
     *
     * @param outputTexts An array list of strings.
     * @return length of the longest string.
     */
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
