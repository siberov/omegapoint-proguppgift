package omegapoint;

/**
 * A class for checking that a ValidatableNumber has a valid organisation number
 * format. Corresponds to a date check for personal identification numbers. 
 */
public class OrganisationNumberFormatCheck extends ValidityCheck {

    /**
     * Check whether the ValidatableNumber is formatted as a organisation
     * number. If it is 12 digits, the first two digits must be 16. The middle
     * pair must be at least 20.
     */
    @Override
    public boolean check(ValidatableNumber n) {
        if (n.isTwelveDigits() && Integer.parseInt(n.getY1()) != 16) {
            return false;
        }
        if (Integer.parseInt(n.getM()) < 20) {
            return false;
        }
        return true;
    }
}
