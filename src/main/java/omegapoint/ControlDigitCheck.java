package omegapoint;

/**
 * A ControlDigitCheck verifies that the number has a valid Luhn control digit.
 */
public class ControlDigitCheck extends ValidityCheck {

    /**
     * Verifies that the number has a valid control digit, using Luhn's
     * algorithm.
     * @return true if the control digit is valid, false otherwise.
     */
    @Override
    public boolean check(ValidatableNumber n) {
        // Calculate the control digit according to Luhn's algorithm.
        int[] luhnFactors = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        String numString = n.getY2() + n.getM() + n.getD() + n.getC();
        int[] digits = numString.chars().map(Character::getNumericValue).toArray();
        int sum = 0;
        for (int i = 0; i < luhnFactors.length; i++) {
            int product = digits[i] * luhnFactors[i];

            // Add the sum of the digits, e.g. 14 -> 1 + 4 = 5.
            if (product >= 10) {
                sum += product % 10 + 1;
            } else {
                sum += product;
            }
        }

        // The control digit is the sum subtracted from the next multiple of 10,
        // or 0 if the sum is a multiple of 10.
        int control = (10 - (sum % 10)) % 10;

        return control == Character.getNumericValue(numString.charAt(numString.length() - 1));
    }
}
