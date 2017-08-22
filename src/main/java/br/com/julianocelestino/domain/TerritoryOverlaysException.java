package br.com.julianocelestino.domain;



public class TerritoryOverlaysException extends Exception {

    private static final String message = "this new territory overlays another territory";

    public TerritoryOverlaysException() {
        super(message);
    }

}
