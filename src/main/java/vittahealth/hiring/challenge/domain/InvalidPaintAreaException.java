package vittahealth.hiring.challenge.domain;



public class InvalidPaintAreaException extends Exception {

    private static final String message = "invalid área to paint (area is not 1x1)";

    public InvalidPaintAreaException() {
        super(message);
    }
}
