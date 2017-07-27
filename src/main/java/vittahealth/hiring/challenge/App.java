package vittahealth.hiring.challenge;

import static spark.Spark.*;

public class App {

    public static void main(String[] args ) {
        port(Integer.valueOf(System.getenv("PORT")));
        configureWebPages();
        configureHeaders();
        startEndPoints();
    }

    private static void configureWebPages() {
        staticFileLocation("/public");
    }

    private static void configureHeaders() {
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

    private static void startEndPoints() {
        new TerritoryEndPoint().init();
    }
}
