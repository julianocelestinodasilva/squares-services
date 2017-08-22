This is a API that exposes the Squares Services.
This app was developed using Java 8 (frameworks : sparkjava v2.5.5, hibernate v5.2.8.Final) and Postgres Data Base.


    - To Build Application and Run Tests :
        This app use Docker container.
        With two docker files (Dockerfile, db/Dockerfile) and a docker-compose file. The first one is the file of the service "squares-service",
        and the second one is the data base service.
	    To build and run the app execute the command "docker-compose up --build" in the root folder.
  
  	The tests will be run in the Maven tests phase (except the tests that are in the package br.com.julianocelestino.acceptancetests);
	To execute the acceptance tests, run JUnit in the package br.com.julianocelestino.acceptancetests;

    - End Points :
        GET  http://localhost:4567/territories (can use the query parameter order=mostPaintedArea/mostProportionalPaintedArea) ;
	    GET  http://localhost:4567/territories/:id (can use the query parameter withpainted=true/false) ;
        POST http://localhost:4567/territories

        GET   http://localhost:4567/squares/:x/:y
        PATCH http://localhost:4567/squares/:x/:y/paint



