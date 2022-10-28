package omegapoint;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A DateCheck checks whether the given ValidatableNumber corresponds to a valid
 * date, according to the Gregorian calendar.
 */
public class DateCheck extends ValidityCheck {

    @Override
    public boolean check(ValidatableNumber n) {
        String century;
        if (n.isTwelveDigits()) {
            // If all four digits are specified, we simply use them
            century = n.getY1();
        } else {
            // If the year is not specified, we need to figure out what it is.
            // We do this by looking at today's date, and comparing it to the one
            // specified in the number. If the number would be in the future given
            // today's century, we set the year to be the previous century. Otherwise,
            // it is the current century.
            
            LocalDate today = LocalDate.now();

            // See if n's date is in the future, given the current century
            if (today.getYear() % 100 >= Integer.parseInt(n.getY2()) &&
                today.getMonthValue() >= Integer.parseInt(n.getM()) &&
                today.getDayOfMonth() >= Integer.parseInt(n.getD())) {
                    century = Integer.toString(today.getYear() / 100);
            } else {
                // The person was born in the previous century
                century = Integer.toString(today.getYear() / 100 - 1);
            }

            // If the delimiter is a "+", we need to subtract another 100 years
            if (n.getDelimiter().equals("+")) {
                century = Integer.toString(Integer.parseInt(century) - 1);
            }
        }

        String numberDateString = century + n.getY2() + "-"  + n.getM() + "-" + n.getD();
        try {
            LocalDate.parse(numberDateString);
        } catch (DateTimeParseException e) {
            // The date given is not valid, e.g., April 31
            return false;
        }
        return true;
    }
}
