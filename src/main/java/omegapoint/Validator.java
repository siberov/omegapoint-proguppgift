package omegapoint;

import java.util.List;

/**
 * A Validator contains a collection of ValidityChecks, and can determine
 * whether a given string adheres to all of them or not. 
 */
public class Validator 
{
    private final List<ValidityCheck> validityChecks;

    /**
     * Create a Validator with a list of ValidityChecks.
     * @param validityChecks the checks that this validator will perform. Must
     * not be null.
     */
    public Validator(List<ValidityCheck> validityChecks) {
        if (validityChecks == null) {
            throw new IllegalArgumentException("Can not instantiate Validator with null list");
        }
        this.validityChecks = validityChecks;
    }

    /**
     * Performs validation of the given number, and gives a boolean verdict as
     * to whether it could be validated.
     * @param number the number to be validated.
     * @return true if the number could be validated, false otherwise.
     */
    public boolean canValidate(String number) {
        ValidatableNumber n;
        try {
            n = new ValidatableNumber(number);
        } catch (IllegalArgumentException e) {
            return false;
        }
        for (ValidityCheck vc : validityChecks) {
            if (!vc.check(n)) {
                return false;
            }
        }
        return true;
    }
}
