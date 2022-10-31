package omegapoint;

/**
 * Checks that a coordination number is created from a valid date.
 */
public class CoordinationDateCheck extends DateCheck {

    /**
     * Check whether n is a coordination number created from a valid date.
     */
    @Override
    public boolean check(ValidatableNumber n) {
        // A coordination number is created by taking a valid personal identity
        // number and adding 60 to the day. We can therefore validate it by
        // subtracting 60 and verifying that it is a valid personal identity
        // number.
        int dayNumber = Integer.parseInt(n.getD()) - 60;
        if (dayNumber < 1) {
            return false;
        }
        String day = Integer.toString(dayNumber);
        ValidatableNumber pinEquivNumber = new ValidatableNumber(n.getY2() + n.getM() + day + n.getDelimiter() + n.getC());
        return super.check(pinEquivNumber);
    }
}
