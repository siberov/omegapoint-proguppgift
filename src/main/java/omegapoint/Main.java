package omegapoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The entry point of the program. Reads numbers from standard in, one per line,
 * and logs any number that can't be validated.
 * 
 * TODO: Come up with a better name.
 */
public class Main {

    Validator personalNumberValidator;
    Validator cooordinationNumberValidator;
    Validator organisationNumberValidator;
    BufferedReader inputReader;

    public Main() {
        List<ValidityCheck> personalNumberChecks = new ArrayList<>();
        personalNumberChecks.add(new DateCheck());
        personalNumberChecks.add(new ControlDigitCheck());

        personalNumberValidator = new Validator(personalNumberChecks);

        List<ValidityCheck> cooordinationNumberChecks = new ArrayList<>();
        cooordinationNumberChecks.add(new CoordinationDateCheck());
        // There is some unneccesary repetition here, with the instantiation of
        // a new ControlDigitCheck. We could avoid it with e.g. a singleton, but
        // I'm leaving it as is. Good enough.
        cooordinationNumberChecks.add(new ControlDigitCheck());
        cooordinationNumberValidator = new Validator(cooordinationNumberChecks);
        
        List<ValidityCheck> organisationNumberChecks = new ArrayList<>();
        organisationNumberChecks.add(new OrganisationNumberFormatCheck());
        organisationNumberChecks.add(new ControlDigitCheck());
        organisationNumberValidator = new Validator(organisationNumberChecks);
    }

    /**
     * Validate lines from standard in until EOF.
     */
    public void run() {
        inputReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            for (String line = inputReader.readLine(); line != null; line = inputReader.readLine()) {
                if (!personalNumberValidator.canValidate(line) &&
                    !cooordinationNumberValidator.canValidate(line) &&
                    !organisationNumberValidator.canValidate(line)) {
                        System.out.println("Could not validate " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Got IOException: " + e.getMessage());
            System.err.println("Aborting.");
        }
    }

    public static void main( String[] args )
    {
        new Main().run();
    }
}
