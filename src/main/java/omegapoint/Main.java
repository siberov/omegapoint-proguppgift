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
    BufferedReader inputReader;

    public Main() {
        List<ValidityCheck> personalNumberChecks = new ArrayList<>();
        personalNumberChecks.add(new DateCheck());
        personalNumberChecks.add(new ControlDigitCheck());

        personalNumberValidator = new Validator(personalNumberChecks);
    }

    /**
     * Validate lines from standard in until EOF.
     */
    public void run() {
        inputReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            for (String line = inputReader.readLine(); line != null; line = inputReader.readLine()) {
                if (!personalNumberValidator.canValidate(line)) {
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
