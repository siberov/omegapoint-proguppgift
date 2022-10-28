package omegapoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for dealing with numbers that can be validated in a uniform
 * way. The same number can be represented in several different ways, e.g.
 * 010203-1234 and 200102031234 are the same personal number, and this class
 * gives a uniform way to retrieve different parts of the number in a way that
 * is agnostic to how it was presented.
 */
public class ValidatableNumber {

    private final String y1, y2, m, d, delimiter, c;

    /**
     * Construct a ValidatableNumber from a String. The String should consist of
     * 6 to 8 digits, optionally followed by a dash ("-") or a plus sign ("+"),
     * followed by four digits.
     * @param number the String from which to create the ValidatableNumber
     * @throws IllegalArgumentException if the String does not follow the format
     * laid out above
     */
    public ValidatableNumber(String number) throws IllegalArgumentException {
        Pattern numberPattern = Pattern.compile("\\d{6,8}[\\-\\+]?\\d{4}");
        Matcher matcher = numberPattern.matcher(number);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }
        
        if (number.contains("+")) {
            delimiter = "+";
        } else if (number.contains("-")) {
            delimiter = "-";
        } else {
            delimiter = "";
        }

        number = number.replace(delimiter, "");
        
        if (number.length() == 12) {
            y1 = number.substring(0, 2);
            number = number.substring(2);
        } else {
            y1 = "";
        }

        // By now, number is exactly 10 characters long, so we can index into it
        // directly.
        y2 = number.substring(0, 2);
        m = number.substring(2, 4);
        d = number.substring(4, 6);
        c = number.substring(6, 10);
    }

    /**
     * Check if the ValidatableNumber was specified with twelve digits.
     * @return true if the ValidatableNumber was specified with twelve digits,
     * false otherwise.
     */
    public boolean isTwelveDigits() {
        // If the first two optional digits are present, then the number is
        // specified with twelve digits.
        return y1 != "";
    }

    /**
     * Get the first two digits of the ValidatableNumber if it was specified
     * using twelve digits, and the empty string if not.
     * @return XXxxxxxx-xxxx
     */
    public String getY1() {
        return y1;
    }

    /**
     * Get the first pair of digits of the ten digit representation of the
     * ValidatableNumber
     * @return XXxxxx-xxxx
     */
    public String getY2() {
        return y2;
    }

    /**
     * Get the second pair of digits of the ten digit representation of the
     * ValidatableNumber
     * @return xxXXxx-xxxx
     */
    public String getM() {
        return m;
    }

    /**
     * Get the third pair of digits of the ten digit representation of the
     * ValidatableNumber
     * @return xxxxXX-xxxx
     */
    public String getD() {
        return d;
    }

    /**
     * Get the delimiter, or the empty string if it was not specified
     * @return the delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * Get the final four digits of the ValidatableNumber
     * @return xxxxxx-XXXX
     */
    public String getC() {
        return c;
    }
}