package omegapoint;

/**
 * A ValidityCheck is a rule that a ValidatableNumber must pass in order to be
 * considered valid. For instance, it could check that it has a valid control
 * digit.
 */
public abstract class ValidityCheck {

    /**
     * Checks that the ValidatableNumber n adheres to the given check.
     * @param n the number to be checked
     * @return true if n adheres to the check
     */
    public abstract boolean check(ValidatableNumber n);
}
