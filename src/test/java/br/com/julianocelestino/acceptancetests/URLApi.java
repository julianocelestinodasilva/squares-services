package br.com.julianocelestino.acceptancetests;



public class URLApi {

    private static final String  URL_API = "http://localhost:4567";

    static String territories() {
        return URL_API + "/territories";
    }

    static String squares() {
        return URL_API + "/squares";
    }

}
