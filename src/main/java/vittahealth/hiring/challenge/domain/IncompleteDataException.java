package vittahealth.hiring.challenge.domain;



public class IncompleteDataException  extends Exception {

    private static final String message = "misses the start, end or name fields!";

    public IncompleteDataException() {
        super(message);
    }
}
